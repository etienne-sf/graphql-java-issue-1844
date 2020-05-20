/**
 * 
 */
package com.graphql_java_generator;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.graphql_java_generator.annotation.GraphQLNonScalar;
import com.graphql_java_generator.annotation.GraphQLScalar;
import com.graphql_java_generator.exception.GraphQLRequestPreparationException;

import graphql.language.ArrayValue;
import graphql.language.BooleanValue;
import graphql.language.FloatValue;
import graphql.language.IntValue;
import graphql.language.NullValue;
import graphql.language.StringValue;
import graphql.language.Value;

/**
 * @author etienne-sf
 */
@Component
public class GraphqlUtils {

	/** This singleton is usable in default method, within interfaces */
	public static GraphqlUtils graphqlUtils = new GraphqlUtils();

	Pattern graphqlNamePattern = Pattern.compile("^[_A-Za-z][_0-9A-Za-z]*$");

	/**
	 * maps for all scalers, when they are mandatory. The key is the type name. The value is the class to use in the
	 * java code
	 */
	List<Class<?>> scalars = new ArrayList<>();

	/**
	 * The list of Java keywords. This keyword may not be used as java identifier, within java code (for instance for
	 * class name, field name...).<BR/>
	 * If a GraphQL identifier is one of these keyword, it will be prefixed by {@link #JAVA_KEYWORD_PREFIX} in the
	 * generated code.
	 */
	private List<String> javaKeywords = new ArrayList<>();

	public static Character JAVA_KEYWORD_PREFIX = '_';

	public GraphqlUtils() {
		// Add of all predefined scalars
		scalars.add(String.class);
		scalars.add(int.class);
		scalars.add(Integer.class);
		scalars.add(float.class);
		scalars.add(Float.class);
		scalars.add(boolean.class);
		scalars.add(Boolean.class);

		// List all java reserved keywords.
		javaKeywords.add("abstract");
		javaKeywords.add("assert");
		javaKeywords.add("boolean");
		javaKeywords.add("break");
		javaKeywords.add("byte");
		javaKeywords.add("case");
		javaKeywords.add("catch");
		javaKeywords.add("char");
		javaKeywords.add("class");
		javaKeywords.add("const");
		javaKeywords.add("continue");
		javaKeywords.add("default");
		javaKeywords.add("do");
		javaKeywords.add("double");
		javaKeywords.add("else");
		javaKeywords.add("enum");
		javaKeywords.add("extends");
		javaKeywords.add("final");
		javaKeywords.add("finally");
		javaKeywords.add("float");
		javaKeywords.add("for");
		javaKeywords.add("goto");
		javaKeywords.add("if");
		javaKeywords.add("implements");
		javaKeywords.add("import");
		javaKeywords.add("instanceof");
		javaKeywords.add("int");
		javaKeywords.add("interface");
		javaKeywords.add("long");
		javaKeywords.add("native");
		javaKeywords.add("new");
		javaKeywords.add("package");
		javaKeywords.add("private");
		javaKeywords.add("protected");
		javaKeywords.add("public");
		javaKeywords.add("return");
		javaKeywords.add("short");
		javaKeywords.add("static");
		javaKeywords.add("strictfp");
		javaKeywords.add("super");
		javaKeywords.add("switch");
		javaKeywords.add("synchronized");
		javaKeywords.add("this");
		javaKeywords.add("throw");
		javaKeywords.add("throws");
		javaKeywords.add("transient");
		javaKeywords.add("try");
		javaKeywords.add("void");
		javaKeywords.add("volatile");
		javaKeywords.add("while");
	}

	/**
	 * Convert the given name, to a camel case name. Currenly very simple : it puts the first character in lower case.
	 * 
	 * @return
	 */
	public String getCamelCase(String name) {
		return name.substring(0, 1).toLowerCase() + name.substring(1);
	}

	/**
	 * Convert the given name, which is supposed to be in camel case (for instance: thisIsCamelCase) to a pascal case
	 * string (for instance: ThisIsCamelCase).
	 * 
	 * @return
	 */
	public String getPascalCase(String name) {
		return name.substring(0, 1).toUpperCase() + name.substring(1);
	}

	/**
	 * Transform an {@link Iterable} (which can be a {@link List}), into a {@link List} of items of the same type. It's
	 * usefull to transform the native type from Spring Data repositories (which needs concrete class to map into) into
	 * the list of relevant GraphQL interface
	 * 
	 * @param <I>
	 * @param iterable
	 * @return
	 */
	public <I> List<I> iterableToList(Iterable<I> iterable) {
		List<I> ret = new ArrayList<I>();
		for (I i : iterable) {
			ret.add(i);
		}
		return ret;
	}

	/**
	 * Transform an {@link Iterable} (which can be a {@link List}) of a concrete class, into a {@link List} of the I
	 * interface or superclass. It's usefull to transform the native type from Spring Data repositories (which needs
	 * concrete class to map into) into the list of relevant GraphQL interface
	 * 
	 * @param <I>
	 * @param iterable
	 * @return
	 */
	public <I> List<I> iterableConcreteClassToListInterface(Iterable<? extends I> iterable) {
		List<I> ret = new ArrayList<I>();
		for (I i : iterable) {
			ret.add(i);
		}
		return ret;
	}

	/**
	 * Transform an {@link Optional}, as returned by Spring Data repositories, into a standard Java, which is null if
	 * there is no value.
	 * 
	 * @param optional
	 * @return
	 */
	public <T> T optionalToObject(Optional<T> optional) {
		return optional.isPresent() ? optional.get() : null;
	}

	/**
	 * Retrieves the class of the fieldName field of the owningClass class.
	 * 
	 * @param owningClass
	 * @param fieldName
	 * @param returnIsMandatory
	 *            If true, a {@link GraphQLRequestPreparationException} is thrown if the field is not found.
	 * @return The class of the field. Or null of the field doesn't exist, and returnIdMandatory is false
	 * @throws GraphQLRequestPreparationException
	 */
	public Class<?> getFieldType(Class<?> owningClass, String fieldName, boolean returnIsMandatory)
			throws GraphQLRequestPreparationException {
		if (owningClass.isInterface()) {
			// We try to get the class of this getter of the field
			try {
				Method method = owningClass.getDeclaredMethod("get" + graphqlUtils.getPascalCase(fieldName));

				// We must manage the type erasure for list. So we use the GraphQL annotations to retrieve types.
				GraphQLNonScalar graphQLNonScalar = method.getAnnotation(GraphQLNonScalar.class);
				GraphQLScalar graphQLScalar = method.getAnnotation(GraphQLScalar.class);

				if (graphQLNonScalar != null)
					return graphQLNonScalar.javaClass();
				else if (graphQLScalar != null)
					return graphQLScalar.javaClass();
				else
					throw new GraphQLRequestPreparationException("Error while looking for the getter for the field '"
							+ fieldName + "' in the interface '" + owningClass.getName()
							+ "': this method should have one of these annotations: GraphQLNonScalar or GraphQLScalar ");
			} catch (NoSuchMethodException e) {
				// Hum, the field doesn't exist.
				if (!returnIsMandatory)
					return null;
				else
					throw new GraphQLRequestPreparationException("Error while looking for the getter for the field '"
							+ fieldName + "' in the class '" + owningClass.getName() + "'", e);
			} catch (SecurityException e) {
				throw new GraphQLRequestPreparationException("Error while looking for the getter for the field '"
						+ fieldName + "' in the class '" + owningClass.getName() + "'", e);
			}
		} else {
			// We try to get the class of this field
			try {
				Field field = owningClass.getDeclaredField(graphqlUtils.getJavaName(fieldName));

				// We must manage the type erasure for list. So we use the GraphQL annotations to retrieve types.
				GraphQLNonScalar graphQLNonScalar = field.getAnnotation(GraphQLNonScalar.class);
				GraphQLScalar graphQLScalar = field.getAnnotation(GraphQLScalar.class);

				if (graphQLNonScalar != null)
					return graphQLNonScalar.javaClass();
				else if (graphQLScalar != null)
					return graphQLScalar.javaClass();
				else
					throw new GraphQLRequestPreparationException("Error while looking for the the field <" + fieldName
							+ "> in the class '" + owningClass.getName()
							+ "': this field should have one of these annotations: GraphQLNonScalar or GraphQLScalar ");
			} catch (NoSuchFieldException e) {
				// Hum, the field doesn't exist.
				if (!returnIsMandatory)
					return null;
				else
					throw new GraphQLRequestPreparationException("Error while looking for the the field <" + fieldName
							+ "> in the class '" + owningClass.getName() + "'", e);
			} catch (SecurityException e) {
				throw new GraphQLRequestPreparationException("Error while looking for the the field <" + fieldName
						+ "> in the class '" + owningClass.getName() + "'", e);
			}
		}
	}

	/**
	 * This method returns a GraphQL input object, as defined in the GraphQL schema, from the Map that has been read
	 * from the JSON object sent to the server.
	 * 
	 * @param <T>
	 *            The class expected to be returned
	 * @param map
	 *            The map, read from the JSON in the GraphQL request. Only the part of the map, related to the expected
	 *            class is sent.
	 * @param t
	 *            An empty instance of the expected type. This instance's fields will be set by this method, from the
	 *            value in the map
	 * @return An instance of the expected class. If the map is null, null is returned. Of the map is empty, anew
	 *         instance is returned, with all its fields are left empty
	 */
	public <T> T getInputObject(Map<String, Object> map, Class<T> clazz) {
		if (map == null) {
			return null;
		} else {
			T t;
			Field field;

			try {
				t = clazz.getConstructor().newInstance();
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
					| InvocationTargetException | NoSuchMethodException | SecurityException e) {
				throw new RuntimeException("Error while creating a new instance of  '" + clazz.getName() + " class", e);
			}

			for (String key : map.keySet()) {
				try {
					field = clazz.getDeclaredField(key);
				} catch (NoSuchFieldException | SecurityException e) {
					throw new RuntimeException(
							"Error while reading '" + key + "' field for the " + clazz.getName() + " class", e);
				}

				Method setter = getSetter(clazz, field);

				GraphQLScalar graphQLScalar = field.getAnnotation(GraphQLScalar.class);
				GraphQLNonScalar graphQLNonScalar = field.getAnnotation(GraphQLNonScalar.class);

				if (graphQLScalar != null) {
					// We have a Scalar, here. Let's look at all known scalars
					if (graphQLScalar.javaClass() == UUID.class) {
						invokeMethod(setter, t, UUID.fromString((String) map.get(key)));
					} else {
						invokeMethod(setter, t, map.get(key));
					}
				} else if (graphQLNonScalar != null) {
					// We got a non scalar field. So we expect a map, which content will map to the fields of the target
					// field.
					if (!(map.get(key) instanceof Map<?, ?>)) {
						throw new RuntimeException(
								"The value for the field '" + clazz.getName() + "." + key + " should be a map");
					}
					@SuppressWarnings("unchecked")
					Map<String, Object> subMap = (Map<String, Object>) map.get(key);
					invokeMethod(setter, t, getInputObject(subMap, graphQLNonScalar.javaClass()));
				} else {
					throw new RuntimeException("Internal error: the field '" + clazz.getName() + "." + key
							+ "' should have one of these annotations: GraphQLScalar or GraphQLScalar");
				}
			}
			return t;
		}
	}

	/**
	 * This method returns a list of instances of the given class, from a list of {@link Map}. This is used on
	 * server-side, to map the input read from the JSON into the InputType that have been declared in the GraphQL
	 * schema.
	 * 
	 * @param <T>
	 * @param list
	 * @param clazz
	 * @return
	 */
	public <T> List<T> getListInputObjects(List<Map<String, Object>> list, Class<T> clazz) {
		List<T> ret = new ArrayList<>(list.size());

		for (Map<String, Object> map : list) {
			ret.add(getInputObject(map, clazz));
		}

		return ret;
	}

	/**
	 * Returns a {@link Field} from the given class.
	 * 
	 * @param owningClass
	 *            The class that should contain this field. If the class's name finishes by Response, as an empty
	 *            XxxResponse class is created for each Query/Mutation/Subscription (to be compatible with previsous
	 *            version), then this method also looks in the owningClass's superclass.
	 * @param fieldName
	 *            The name of the searched field
	 * @param mustFindField
	 *            If true and the field is not found, a {@link GraphQLRequestPreparationException} is thrown.<BR/>
	 *            If false an the field is not found, the method returns null
	 * @return
	 * @throws GraphQLRequestPreparationException
	 */
	public Field getDeclaredField(Class<?> owningClass, String fieldName, boolean mustFindField)
			throws GraphQLRequestPreparationException {

		try {
			return owningClass.getDeclaredField(fieldName);
		} catch (NoSuchFieldException | SecurityException e1) {
			// If the classname finishes by "Response", we take a look at the superclass, as the XxxResponse classes are
			// built as just inheriting from the query/mutation/subscription class
			if (owningClass.getSimpleName().endsWith("Response")) {
				try {
					return owningClass.getSuperclass().getDeclaredField(fieldName);
				} catch (NoSuchFieldException | SecurityException e2) {
					if (mustFindField)
						throw new GraphQLRequestPreparationException("Could not find fied '" + fieldName + "' in "
								+ owningClass.getName() + ", nor in " + owningClass.getSuperclass().getName(), e1);
				}
			}

			if (mustFindField)
				throw new GraphQLRequestPreparationException(
						"Could not find fied '" + fieldName + "' in " + owningClass.getName(), e1);
		}
		return null;
	}

	/**
	 * Retrieves the setter for the given field on the given field
	 * 
	 * @param <T>
	 * @param t
	 * @param field
	 * @return
	 */
	public <T> Method getSetter(Class<T> clazz, Field field) {
		String setterMethodName = "set" + getPascalCase(field.getName());
		try {
			return clazz.getDeclaredMethod(setterMethodName, field.getType());
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(
					"The setter '" + setterMethodName + "' is missing in " + clazz.getName() + " class", e);
		} catch (SecurityException e) {
			throw new RuntimeException(
					"Error while accessing to the setter '" + setterMethodName + "' in " + clazz.getName() + " class",
					e);
		}
	}

	/**
	 * Retrieves the getter for the given field on the given field
	 * 
	 * @param <T>
	 * @param t
	 * @param field
	 * @return
	 */
	public <T> Method getGetter(Class<T> clazz, Field field) {
		String setterMethodName = "get" + getPascalCase(field.getName());
		try {
			Method method = clazz.getDeclaredMethod(setterMethodName);

			// The return type must be the same as the field's class
			if (field.getType() != method.getReturnType()) {
				throw new RuntimeException("The getter '" + setterMethodName + "' and the field '" + field.getName()
						+ "' of the class " + clazz.getName() + " should be of the same type");
			}

			return method;
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(
					"The getter '" + setterMethodName + "' is missing in " + clazz.getName() + " class", e);
		} catch (SecurityException e) {
			throw new RuntimeException(
					"Error while accessing to the getter '" + setterMethodName + "' in " + clazz.getName() + " class",
					e);
		}
	}

	/**
	 * Invoke the getter for the given field name, on the given object. All check exceptions are hidden in a
	 * {@link RuntimeException}
	 * 
	 * @param object
	 * @param fieldName
	 * @return the field's value for the given object
	 * @throws RuntimeException
	 *             If any exception occurs
	 */
	public Object invokeGetter(Object object, String fieldName) {
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			Method getter = getGetter(object.getClass(), field);
			return getter.invoke(object);
		} catch (NoSuchFieldException | SecurityException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new RuntimeException("Error while invoking to the getter for the field '" + fieldName
					+ "' in the class " + object.getClass().getName() + " class", e);
		}
	}

	/**
	 * Invoke the setter for the given field, on the given object. All check exceptions are hidden in a
	 * {@link RuntimeException}
	 *
	 * @param object
	 * @param field
	 * @param value
	 * @throws RuntimeException
	 *             If any exception occurs
	 */
	public void invokeSetter(Object object, Field field, Object value) {
		try {
			Method setter = getSetter(object.getClass(), field);
			setter.invoke(object, value);
		} catch (SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException("Error while invoking to the setter for the field '" + field.getName()
					+ "' in the class " + object.getClass().getName() + " class", e);
		}
	}

	/**
	 * Invoke the setter for the {@link Field} of the given name, on the given object. All check exceptions are hidden
	 * in a {@link RuntimeException}
	 *
	 * @param object
	 * @param fieldName
	 * @param value
	 * @throws RuntimeException
	 *             If any exception occurs
	 */
	public void invokeSetter(Object object, String fieldName, Object value) {
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			invokeSetter(object, field, value);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException e) {
			throw new RuntimeException("Error while invoking to the setter for the field '" + fieldName
					+ "' in the class " + object.getClass().getName() + " class", e);
		}
	}

	/**
	 * Retrieves the asked method, from its name, class and parameters. This method hides the exception that could be
	 * thrown, into a {@link RuntimeException}
	 * 
	 * @param <T>
	 * @param t
	 * @param field
	 * @return
	 * @throws RuntimeException
	 *             When an exception occurs while getting the method
	 */
	public Method getMethod(String methodName, Class<?> clazz, Class<?>... parameterTypes) {
		try {
			return clazz.getDeclaredMethod(methodName, parameterTypes);
		} catch (NoSuchMethodException e) {
			throw new RuntimeException(
					"Could not get the method '" + methodName + "' in the " + clazz.getName() + " class", e);
		}
	}

	/**
	 * Calls the 'methodName' method on the given object.
	 * 
	 * @param methodName
	 *            The name of the method. This method should have no parameter
	 * @param object
	 *            The given node, on which the 'methodName' method is to be called
	 * @return
	 */
	public Object invokeMethod(String methodName, Object object) {
		try {
			Method getType = object.getClass().getDeclaredMethod(methodName);
			return getType.invoke(object);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException
				| SecurityException e) {
			throw new RuntimeException("Error when trying to execute '" + methodName + "' on '"
					+ object.getClass().getName() + "': " + e.getMessage(), e);
		}
	}

	/**
	 * Invoke the given setter on the given object, with the given value. This method hides the exception that could be
	 * thrown, into a {@link RuntimeException}
	 * 
	 * @param method
	 * @param o
	 * @param value
	 * @throws RuntimeException
	 *             When an exception occurs while accessing the setter
	 */
	public Object invokeMethod(Method method, Object o, Object... args) {
		try {
			return method.invoke(o, args);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException("Error when executing the method '" + method.getName() + "' is missing in "
					+ o.getClass().getName() + " class", e);
		}
	}

	/**
	 * Returns a valid java identifier for the given name.
	 * 
	 * @param name
	 * @return If name is a default java keyword (so it is not a valid java identifier), then the return prefixed by a
	 *         {@link #JAVA_KEYWORD_PREFIX}. Otherwise (which is generally the case), the name is valid, and returned as
	 *         is the given name
	 */
	public String getJavaName(String name) {
		return isJavaReservedWords(name) ? JAVA_KEYWORD_PREFIX + name : name;
	}

	/**
	 * Returns true if name is a reserved java keyword
	 * 
	 * @param name
	 * @return
	 */
	public boolean isJavaReservedWords(String name) {
		return javaKeywords.contains(name);
	}

	/**
	 * Extract the simple name for a class (without the package name), from its full class name (with the package name)
	 * 
	 * @param classFullName
	 *            The full class name, for instance java.util.Date
	 * @return The simple class name (in the above sample: Date)
	 */
	public String getClassSimpleName(String classFullName) {
		int lstPointPosition = classFullName.lastIndexOf('.');
		return classFullName.substring(lstPointPosition + 1);
	}

	/**
	 * Extract the package name for a class, from its full class name (with the package name)
	 * 
	 * @param classFullName
	 *            The full class name, for instance java.util.Date
	 * @return The simple class name (in the above sample: java.util)
	 */
	public String getPackageName(String classFullName) {
		int lstPointPosition = classFullName.lastIndexOf('.');
		return classFullName.substring(0, lstPointPosition);
	}

	/**
	 * Concatenate a non limited number of lists into a stream.
	 * 
	 * @param <T>
	 * @param clazz
	 *            The T class
	 * @param parallelStreams
	 *            true if the returned stream should be a parallel one
	 * @param lists
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Stream<T> concatStreams(Class<T> clazz, boolean parallelStreams, List<?>... lists) {
		if (lists.length == 0) {
			// Let's return an empty stream
			return new ArrayList<T>().stream();
		} else {
			Stream<T> ret = (Stream<T>) lists[0].stream();
			for (int i = 1; i < lists.length; i += 1) {
				ret = (Stream<T>) Stream.concat(ret, lists[i].stream());
			}
			return parallelStreams ? ret.parallel() : ret;
		}
	}

	/**
	 * Get the internal value for a {@link Value} stored in the graphql-java AST.
	 * 
	 * @param value
	 *            The value for which we need to extract the real value
	 * @param graphqlTypeName
	 *            The type name for this value, as defined in the GraphQL schema. This is used when it's an object
	 *            value, to create an instance of the correct java class.
	 * @param action
	 *            The action that is executing, to generated an explicit error message. It can be for instance "Reading
	 *            directive directiveName".
	 * @return
	 */
	Object getValue(Value<?> value, String graphqlTypeName, String action) {
		if (value instanceof StringValue) {
			return ((StringValue) value).getValue();
		} else if (value instanceof BooleanValue) {
			return ((BooleanValue) value).isValue();
		} else if (value instanceof IntValue) {
			return ((IntValue) value).getValue();
		} else if (value instanceof FloatValue) {
			return ((FloatValue) value).getValue();
		} else if (value instanceof graphql.language.EnumValue) {
			// For enums, we can't retrieve an instance of the enum value, as the enum class has not been created yet.
			// So we just return the label of the enum, as a String.
			return ((graphql.language.EnumValue) value).getName();
		} else if (value instanceof NullValue) {
			return null;
		} else if (value instanceof ArrayValue) {
			@SuppressWarnings("rawtypes")
			List<Value> list = ((ArrayValue) value).getValues();
			Object[] ret = new Object[list.size()];
			for (int i = 0; i < list.size(); i += 1) {
				ret[i] = getValue(list.get(i), graphqlTypeName, action + ": ArrayValue(" + i + ")");
			}
			return ret;
			// } else if (value instanceof ObjectValue) {
			// return null;
		} else {
			throw new RuntimeException(
					"Value of type " + value.getClass().getName() + " is not managed (" + action + ")");
		}
	}
}