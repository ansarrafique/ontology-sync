package com.embl.ontologysync.integration;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * Integration tests for OntologyController.
 *
 * Author: Ansar Rafique
 * Date: October 10, 2023
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OntologyControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	/**
	 * Test for successfully retrieving an ontology by ID.
	 *
	 * @throws Exception if the test encounters an error.
	 */
	@Test
	public void testGetOntologySuccess() throws Exception {
		String ontologyId = "ado";

		mockMvc.perform(MockMvcRequestBuilders.get("/api/ontologies/{ontologyId}", ontologyId)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.ontologyId").value(ontologyId));
	}

	/**
	 * Test for handling the scenario when the requested ontology is not found.
	 *
	 * @throws Exception if the test encounters an error.
	 */
	@Test
	public void testGetOntologyNotFound() throws Exception {
		String ontologyId = "abc";

		mockMvc.perform(MockMvcRequestBuilders.get("/api/ontologies/{ontologyId}", ontologyId)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isNotFound())
		.andExpect(MockMvcResultMatchers.content().string("Error: Ontology not found"));
	}
}
