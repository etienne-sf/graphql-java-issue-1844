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
public interface DataFetchersDelegateCharacter {
	
	/**
	 * This method loads the data for Character.friends. 
	 * <BR/>
	 * <BR/>
	 * 
	 * @param dataFetchingEnvironment 
	 *     The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this DataFetcher
	 * @param origin 
	 *    The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the friends attribute
	 *    of the <I>origin</I>, which is an instance of {Character}. It depends on your data modle, but it typically contains 
	 *    the id to use in the query.
	 * @throws NoSuchElementException 
	 *     This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 *     by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 *     whether or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
	public List<Character> friends(DataFetchingEnvironment dataFetchingEnvironment, Character origin);

	/**
	 * This method loads the data for Character.appearsIn. 
	 * <BR/>
	 * <BR/>
	 * 
	 * @param dataFetchingEnvironment 
	 *     The GraphQL {@link DataFetchingEnvironment}. It gives you access to the full GraphQL context for this DataFetcher
	 * @param origin 
	 *    The object from which the field is fetch. In other word: the aim of this data fetcher is to fetch the appearsIn attribute
	 *    of the <I>origin</I>, which is an instance of {Character}. It depends on your data modle, but it typically contains 
	 *    the id to use in the query.
	 * @throws NoSuchElementException 
	 *     This method may return a {@link NoSuchElementException} exception. In this case, the exception is trapped 
	 *     by the calling method, and the return is consider as null. This allows to use the {@link Optional#get()} method directly, without caring of 
	 *     whether or not there is a value. The generated code will take care of the {@link NoSuchElementException} exception. 
	 */
	public List<Episode> appearsIn(DataFetchingEnvironment dataFetchingEnvironment, Character origin);

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
	public List<Character> batchLoader(List<UUID> keys);

}
