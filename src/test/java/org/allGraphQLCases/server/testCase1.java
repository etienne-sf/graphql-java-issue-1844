package org.allGraphQLCases.server;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import graphql.schema.idl.errors.SchemaProblem;

class testCase1 {

	@Test
	void test() throws SchemaProblem, IOException {
		TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(getSchemaContent("testCase1.graphqls"));

		RuntimeWiring runtimeWiring = RuntimeWiring.newRuntimeWiring()
				//
				.build();
		SchemaGenerator schemaGenerator = new SchemaGenerator();
		schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
	}

	private String getSchemaContent(String filename) throws IOException {
		Resource res;
		StringBuffer sdl = new StringBuffer();
		res = new ClassPathResource("/" + filename);
		try (Reader reader = new InputStreamReader(res.getInputStream(), Charset.forName("UTF8"))) {
			sdl.append(FileCopyUtils.copyToString(reader));
		}
		return sdl.toString();
	}

}
