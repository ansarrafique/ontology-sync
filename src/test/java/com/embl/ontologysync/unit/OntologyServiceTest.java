package com.embl.ontologysync.unit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.embl.ontologysync.entity.Ontology;
import com.embl.ontologysync.ols.OntologyLookupService;
import com.embl.ontologysync.repository.OntologyRepository;
import com.embl.ontologysync.service.OntologyService;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the OntologyService class.
 *
 * Author: Ansar Rafique
 * Date: October 10, 2023
 */

@ExtendWith(MockitoExtension.class)
public class OntologyServiceTest {

	@InjectMocks
	private OntologyService ontologyService;

	@Mock
	private OntologyLookupService ontologyLookupService;

	@Mock
	private OntologyRepository ontologyRepository;

	/**
	 * Test for handling the scenario when the ontology is successfully found in the database.
	 */
	@Test
	public void testFindByOntologyIdSuccessfullyFoundInDatabase() {
		//Given
		String ontologyId = "ado";
		Ontology expected = new Ontology(ontologyId, "Test Ontology", "Test Description", null, null);

		//Configuring the behaviour of the OntologyRepository mock
		when(ontologyRepository.findByOntologyId(ontologyId)).thenReturn(Optional.of(expected));

		//When
		Optional<Ontology> result = ontologyService.findByOntologyId(ontologyId);

		//Then
		assertEquals(ontologyId, result.get().getOntologyId());
	}
	
	/**
	 * Test for handling the scenario when the ontology is not found in the database as well as in Ontology Lookup Service (OLS)
	 */
	@Test
	public void testFindByOntologyIdNotFoundInDatabaseAndOLS() {
		// Given
		String ontologyId = "abc";

		// Configuring the behavior of the OntologyRepository mock 
		when(ontologyRepository.findByOntologyId(ontologyId)).thenReturn(Optional.empty());

		// Configuring the behavior of the OntologyLookupService mock
		when(ontologyLookupService.fetchOntologyFromOLS(ontologyId)).thenReturn(Optional.empty());

		// When
		Optional<Ontology> result = ontologyService.findByOntologyId(ontologyId);
		
		// Then
		assertFalse(result.isPresent());
	}
}
