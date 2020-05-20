/**
 * 
 */
package org.allGraphQLCases.server.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.allGraphQLCases.server.AllFieldCases;
import org.allGraphQLCases.server.DataFetchersDelegateAllFieldCases;
import org.springframework.stereotype.Component;

/**
 * @author EtienneSF
 *
 */
@Component
public class DataFetchersDelegateAllFieldCasesImpl implements DataFetchersDelegateAllFieldCases {

	@Resource
	DataGenerator generator;

	@Override
	public List<AllFieldCases> batchLoader(List<UUID> keys) {
		return generator.generateInstanceList(AllFieldCases.class, keys.size());
	}

}
