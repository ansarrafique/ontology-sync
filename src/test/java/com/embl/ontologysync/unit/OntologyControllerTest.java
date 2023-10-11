package com.embl.ontologysync.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.embl.ontologysync.controller.OntologyController;
import com.embl.ontologysync.entity.Ontology;
import com.embl.ontologysync.service.OntologyService;

/**
 * Unit tests for the OntologyController class.
 *
 * Author: Ansar Rafique
 * Date: October 10, 2023
 */
@ExtendWith(MockitoExtension.class)
public class OntologyControllerTest {

	@InjectMocks
	private OntologyController ontologyController;

	@Mock
	private OntologyService ontologyService;

	/**
	 * Test for handling the scenario when the ontology is successfully found.
	 */
	@Test
	public void testFindByOntologyIdSuccessfullyFound() {
		//Given
		String ontologyId = "ado";
		Ontology sampleOntology = new Ontology(ontologyId, "Title", "Description", null, null);
		Optional<Ontology> expected = Optional.of(sampleOntology);

		//Configuring the behaviour of the OntologyService mock
		when(ontologyService.findByOntologyId(ontologyId)).thenReturn(expected);

		//When
		ResponseEntity<Object> response = ontologyController.getOntology(ontologyId);

		//Then
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expected.get(), response.getBody());
	}

	/**
     * Test for handling the scenario when the requested ontology is not found.
     */
	@Test
	public void testFindByOntologyIdNotFound() {
		//Given
		String ontologyId = "abc";
		Optional<Ontology> expected = Optional.empty();

		//Configuring the behaviour of the OntologyService mock
		when(ontologyService.findByOntologyId(ontologyId)).thenReturn(expected);

		//When
		ResponseEntity<Object> response = ontologyController.getOntology(ontologyId);

		//Then
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
		assertEquals("Error: Ontology not found", response.getBody());
	}
}
