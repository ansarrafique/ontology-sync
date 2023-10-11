package com.embl.ontologysync.unit;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.embl.ontologysync.ols.Config;
import com.embl.ontologysync.ols.OLSOntology;
import com.embl.ontologysync.ols.OntologyLookupService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;

/**
 * Unit tests for the OntologyLookupService class.
 *
 * Author: Ansar Rafique
 * Date: October 10, 2023
 */
@ExtendWith(MockitoExtension.class)
public class OntologyLookupServiceTest {
	
	private final String OLS_URL = "https://www.ebi.ac.uk/ols4/api/ontologies/";
	
	@InjectMocks
	private OntologyLookupService ontologyLookupService;
	
	@Mock
	private RestTemplate restTemplate;
	
	/**
	 * Test for handling the scenario when the requested ontology is not found in the database but found in OLS.
	 */
	@Test
	public void testFindByOntologyIdNotFoundInDatabaseButFoundInOLS() {
	    // Given
	    String ontologyId = "abc";

	    OLSOntology olsOntology = new OLSOntology();
	    olsOntology.setOntologyId(ontologyId);
	    Config olsConfig = new Config();
	    olsConfig.setTitle("Test Title");
	    olsConfig.setDescription("Test Description");
	    olsOntology.setConfig(olsConfig);

	    // Create an expected ResponseEntity with HttpStatus.OK
	    ResponseEntity<OLSOntology> expectedResponseEntity = new ResponseEntity<>(olsOntology, HttpStatus.OK);

	    // Configuring the behavior of the RestTemplate exchange method
	    when(restTemplate.exchange(eq(OLS_URL + ontologyId),
	            eq(HttpMethod.GET), any(), eq(OLSOntology.class)))
	            .thenReturn(expectedResponseEntity);

	    // When
	    Optional<OLSOntology> result = ontologyLookupService.fetchOntologyFromOLS(ontologyId);

	    // Then
	    assertTrue(result.isPresent());
	    assertEquals(olsOntology, result.get());
	}
	
	/**
	 * Test for handling the scenario when the requested ontology is not found in the database as well as in the OLS.
	 */
	@Test
	public void testFindByOntologyIdNotFoundInDatabaseAndOLS() {
		//Given
		String ontologyId = "abc";

		OLSOntology olsOntology = new OLSOntology();
		olsOntology.setOntologyId(ontologyId);
		Config olsConfig = new Config();
		olsConfig.setTitle("Test Title");
		olsConfig.setDescription("Test Description");
		olsOntology.setConfig(olsConfig);

		//Create an expected ResponseEntity with HttpStatus.NOT_FOUND
		ResponseEntity<OLSOntology> expectedResponseEntity = new ResponseEntity<>(olsOntology, HttpStatus.NOT_FOUND);

		//Configuring the behaviour of the RestTemplate exchange method
		when(restTemplate.exchange(eq(OLS_URL + ontologyId),
				eq(HttpMethod.GET), any(), eq(OLSOntology.class)))
		.thenReturn(expectedResponseEntity);

		//When
		Optional<OLSOntology> result = ontologyLookupService.fetchOntologyFromOLS(ontologyId);

		//Then
		assertFalse(result.isPresent());
	}

}
