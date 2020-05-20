/** Generated by the default template from graphql-java-generator */
package org.allGraphQLCases.server;

import com.graphql_java_generator.GraphQLField;
import com.graphql_java_generator.annotation.GraphQLInputParameters;
import com.graphql_java_generator.annotation.GraphQLInterfaceType;
import com.graphql_java_generator.annotation.GraphQLScalar;
import java.util.List;

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
@GraphQLInterfaceType("Commented")
public interface Commented  {

	@GraphQLScalar(fieldName = "nbComments", graphQLTypeName = "Int", javaClass = Integer.class)
	public void setNbComments(Integer nbComments);

	@GraphQLScalar(fieldName = "nbComments", graphQLTypeName = "Int", javaClass = Integer.class)
	public Integer getNbComments();

	@GraphQLScalar(fieldName = "comments", graphQLTypeName = "String", javaClass = String.class)
	public void setComments(List<String> comments);

	@GraphQLScalar(fieldName = "comments", graphQLTypeName = "String", javaClass = String.class)
	public List<String> getComments();
}