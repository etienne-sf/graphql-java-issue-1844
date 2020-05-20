/** Generated by the default template from graphql-java-generator */
package org.allGraphQLCases.server;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.dataloader.DataLoader;
import org.reactivestreams.Publisher;

import graphql.schema.DataFetchingEnvironment;

import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.util.Date;

/**
 * @author generated by graphql-java-generator
 * @see <a href="https://github.com/graphql-java-generator/graphql-java-generator">https://github.com/graphql-java-generator/graphql-java-generator</a>
 */
public interface DataFetchersDelegateAllFieldCasesInterface {
	
	/**
	 * This method loads the data for AllFieldCasesInterface.comments. 
	 * <BR/>
	 * <BR/>
	 * 
	 * @param dataFetchingEnvironment 
	 *     The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this DataFetcher
	 * @param origin 
	 *    The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the comments attribute
	 *    of the <I>origin</I>, which is an instance of {AllFieldCasesInterface}. It depends on your data modle, but it typically contains 
	 *    the id to use in the query.
	 * @throws NoSuchElementException 
	 *     This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 *     by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 *     whether or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
	public List<String> comments(DataFetchingEnvironment dataFetchingEnvironment, AllFieldCasesInterface origin);

	/**
	 * This method loads the data for AllFieldCasesInterface.booleans. 
	 * <BR/>
	 * <BR/>
	 * 
	 * @param dataFetchingEnvironment 
	 *     The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this DataFetcher
	 * @param origin 
	 *    The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the booleans attribute
	 *    of the <I>origin</I>, which is an instance of {AllFieldCasesInterface}. It depends on your data modle, but it typically contains 
	 *    the id to use in the query.
	 * @throws NoSuchElementException 
	 *     This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 *     by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 *     whether or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
	public List<Boolean> booleans(DataFetchingEnvironment dataFetchingEnvironment, AllFieldCasesInterface origin);

	/**
	 * This method loads the data for AllFieldCasesInterface.aliases. 
	 * <BR/>
	 * <BR/>
	 * 
	 * @param dataFetchingEnvironment 
	 *     The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this DataFetcher
	 * @param origin 
	 *    The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the aliases attribute
	 *    of the <I>origin</I>, which is an instance of {AllFieldCasesInterface}. It depends on your data modle, but it typically contains 
	 *    the id to use in the query.
	 * @throws NoSuchElementException 
	 *     This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 *     by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 *     whether or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
	public List<String> aliases(DataFetchingEnvironment dataFetchingEnvironment, AllFieldCasesInterface origin);

	/**
	 * This method loads the data for AllFieldCasesInterface.planets. 
	 * <BR/>
	 * <BR/>
	 * 
	 * @param dataFetchingEnvironment 
	 *     The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this DataFetcher
	 * @param origin 
	 *    The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the planets attribute
	 *    of the <I>origin</I>, which is an instance of {AllFieldCasesInterface}. It depends on your data modle, but it typically contains 
	 *    the id to use in the query.
	 * @throws NoSuchElementException 
	 *     This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 *     by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 *     whether or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
	public List<String> planets(DataFetchingEnvironment dataFetchingEnvironment, AllFieldCasesInterface origin);

	/**
	 * This method loads the data for AllFieldCasesInterface.friends. 
	 * <BR/>
	 * <BR/>
	 * 
	 * @param dataFetchingEnvironment 
	 *     The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this DataFetcher
	 * @param origin 
	 *    The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the friends attribute
	 *    of the <I>origin</I>, which is an instance of {AllFieldCasesInterface}. It depends on your data modle, but it typically contains 
	 *    the id to use in the query.
	 * @throws NoSuchElementException 
	 *     This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 *     by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 *     whether or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
	public List<Human> friends(DataFetchingEnvironment dataFetchingEnvironment, AllFieldCasesInterface origin);

	/**
	 * This method loads the data for AllFieldCasesInterface.oneWithIdSubType. 
	 * <BR/>
	 * <BR/>
	 * 
	 * @param dataFetchingEnvironment 
	 *     The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this DataFetcher
	 * @param origin 
	 *    The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the oneWithIdSubType attribute
	 *    of the <I>origin</I>, which is an instance of {AllFieldCasesInterface}. It depends on your data modle, but it typically contains 
	 *    the id to use in the query.
	 * @throws NoSuchElementException 
	 *     This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 *     by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 *     whether or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
	public AllFieldCasesWithIdSubtype oneWithIdSubType(DataFetchingEnvironment dataFetchingEnvironment, AllFieldCasesInterface origin);

	/**
	 * This method loads the data for AllFieldCasesInterface.oneWithIdSubType. 
	 * <BR/>
	 * For optimization, this method returns a CompletableFuture. This allows to use 
	 * <A HREF="https://github.com/graphql-java/java-dataloader">graphql-java java-dataloader</A> to highly optimize the
	 * number of requests to the server.<BR/>
	 * The principle is this one: The data loader collects all the data to load, avoid to load several times the same data, 
	 * and allows parallel execution of the queries, if multiple queries are to be run.<BR/>
	 * You can implements this method like this:
	 * <PRE>
	 * @Override
	 * public CompletableFuture<List<Character>> friends(DataFetchingEnvironment environment, DataLoader<UUID, AllFieldCasesWithIdSubtype> dataLoader, Human origin) {
	 *     List<UUID> friendIds = origin.getFriendIds();
	 *     DataLoader<UUID, CharacterImpl> dataLoader = environment.getDataLoader("Character");
	 *     return dataLoader.loadMany(friendIds);
	 * }
	 * </PRE>
	 * <BR/>
	 * 
	 * @param dataFetchingEnvironment 
	 *     The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this DataFetcher
	 * @param dataLoader
	 *            The {@link DataLoader} allows to load several data in one query. It allows to solve the (n+1) queries
	 *            issues, and greatly optimizes the response time.<BR/>
	 *            You'll find more informations here: <A HREF=
	 *            "https://github.com/graphql-java/java-dataloader">https://github.com/graphql-java/java-dataloader</A>
	 * @param origin 
	 *    The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the oneWithIdSubType attribute
	 *    of the <I>origin</I>, which is an instance of {AllFieldCasesInterface}. It depends on your data modle, but it typically contains 
	 *    the id to use in the query.
	 * @throws NoSuchElementException 
	 *     This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 *     by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 *     whether or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
	public CompletableFuture<AllFieldCasesWithIdSubtype> oneWithIdSubType(DataFetchingEnvironment dataFetchingEnvironment, DataLoader<UUID, AllFieldCasesWithIdSubtype> dataLoader, AllFieldCasesInterface origin);

	/**
	 * This method loads the data for AllFieldCasesInterface.listWithIdSubTypes. 
	 * <BR/>
	 * <BR/>
	 * 
	 * @param dataFetchingEnvironment 
	 *     The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this DataFetcher
	 * @param origin 
	 *    The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the listWithIdSubTypes attribute
	 *    of the <I>origin</I>, which is an instance of {AllFieldCasesInterface}. It depends on your data modle, but it typically contains 
	 *    the id to use in the query.
	 * @param nbItems 
	 *     The input parameter sent in the query by the GraphQL consumer, as defined in the GraphQL schema.
	 * @param uppercaseName 
	 *     The input parameter sent in the query by the GraphQL consumer, as defined in the GraphQL schema.
	 * @param textToAppendToTheForname 
	 *     The input parameter sent in the query by the GraphQL consumer, as defined in the GraphQL schema.
	 * @throws NoSuchElementException 
	 *     This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 *     by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 *     whether or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
	public List<AllFieldCasesWithIdSubtype> listWithIdSubTypes(DataFetchingEnvironment dataFetchingEnvironment, AllFieldCasesInterface origin, Integer  nbItems, Boolean  uppercaseName, String  textToAppendToTheForname);

	/**
	 * This method loads the data for AllFieldCasesInterface.oneWithoutIdSubType. 
	 * <BR/>
	 * <BR/>
	 * 
	 * @param dataFetchingEnvironment 
	 *     The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this DataFetcher
	 * @param origin 
	 *    The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the oneWithoutIdSubType attribute
	 *    of the <I>origin</I>, which is an instance of {AllFieldCasesInterface}. It depends on your data modle, but it typically contains 
	 *    the id to use in the query.
	 * @param input 
	 *     The input parameter sent in the query by the GraphQL consumer, as defined in the GraphQL schema.
	 * @throws NoSuchElementException 
	 *     This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 *     by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 *     whether or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
	public AllFieldCasesWithoutIdSubtype oneWithoutIdSubType(DataFetchingEnvironment dataFetchingEnvironment, AllFieldCasesInterface origin, FieldParameterInput  input);

	/**
	 * This method loads the data for AllFieldCasesInterface.listWithoutIdSubTypes. 
	 * <BR/>
	 * <BR/>
	 * 
	 * @param dataFetchingEnvironment 
	 *     The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this DataFetcher
	 * @param origin 
	 *    The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the listWithoutIdSubTypes attribute
	 *    of the <I>origin</I>, which is an instance of {AllFieldCasesInterface}. It depends on your data modle, but it typically contains 
	 *    the id to use in the query.
	 * @param nbItems 
	 *     The input parameter sent in the query by the GraphQL consumer, as defined in the GraphQL schema.
	 * @param input 
	 *     The input parameter sent in the query by the GraphQL consumer, as defined in the GraphQL schema.
	 * @param textToAppendToTheForname 
	 *     The input parameter sent in the query by the GraphQL consumer, as defined in the GraphQL schema.
	 * @throws NoSuchElementException 
	 *     This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 *     by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 *     whether or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
	public List<AllFieldCasesWithoutIdSubtype> listWithoutIdSubTypes(DataFetchingEnvironment dataFetchingEnvironment, AllFieldCasesInterface origin, Integer  nbItems, FieldParameterInput  input, String  textToAppendToTheForname);

	/**
	 * This method loads a list of ${dataFetcher.field.name}, based on the list of id to be fetched. This method is used by
	 * <A HREF="https://github.com/graphql-java/java-dataloader">graphql-java java-dataloader</A> to highly optimize the
	 * number of requests to the server, when recursing down through the object associations.<BR/>
	 * You can find more information on this page:
	 * <A HREF="https://www.graphql-java.com/documentation/master/batching/">graphql-java batching</A>
	 * 
	 * @param keys
	 *            A list of ID's id
	 * @return A list of IDs
	 */
	public List<AllFieldCasesInterface> batchLoader(List<UUID> keys);

}
