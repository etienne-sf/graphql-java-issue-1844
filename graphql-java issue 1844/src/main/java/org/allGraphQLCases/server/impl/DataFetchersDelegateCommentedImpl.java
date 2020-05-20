/**
 * 
 */
package org.allGraphQLCases.server.impl;

import java.util.List;

import javax.annotation.Resource;

import org.allGraphQLCases.server.Commented;
import org.allGraphQLCases.server.DataFetchersDelegateCommented;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;

/**
 * @author EtienneSF
 *
 */
@Component
public class DataFetchersDelegateCommentedImpl implements DataFetchersDelegateCommented {

	@Resource
	DataGenerator generator;

	@Override
	public List<String> comments(DataFetchingEnvironment dataFetchingEnvironment, Commented source) {
		return generator.generateInstanceList(String.class, 10);
	}

}
