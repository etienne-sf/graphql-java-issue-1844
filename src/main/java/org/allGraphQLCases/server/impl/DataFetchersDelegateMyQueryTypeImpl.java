package org.allGraphQLCases.server.impl;

import javax.annotation.Resource;

import org.allGraphQLCases.server.AllFieldCases;
import org.allGraphQLCases.server.DataFetchersDelegateMyQueryType;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.stereotype.Component;

import graphql.schema.DataFetchingEnvironment;

/**
 * @author EtienneSF
 *
 */
@Component
public class DataFetchersDelegateMyQueryTypeImpl implements DataFetchersDelegateMyQueryType {

	@Resource
	DataGenerator generator;

	Mapper mapper = new DozerBeanMapper();

	@Override
	public AllFieldCases allFieldCases(DataFetchingEnvironment dataFetchingEnvironment) {
		return generator.generateInstance(AllFieldCases.class);
	}
}
