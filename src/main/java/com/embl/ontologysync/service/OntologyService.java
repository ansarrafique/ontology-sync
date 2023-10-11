package com.embl.ontologysync.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.embl.ontologysync.entity.Ontology;
import com.embl.ontologysync.ols.OLSOntology;
import com.embl.ontologysync.ols.OntologyLookupService;
import com.embl.ontologysync.repository.OntologyRepository;
import java.util.Optional;

/**
 * Service class for managing ontology-related operations.
 *
 * Author: Ansar Rafique
 * Date: October 10, 2023
 */
@Service
public class OntologyService {

	private static final Logger logger = LoggerFactory.getLogger(OntologyService.class);

	@Autowired
	private OntologyLookupService ontologyLookupService;

	@Autowired
	private OntologyRepository ontologyRepository;

	/**
	 * Find an ontology by its ID. If the ontology is not found in the local database (MongoDB),
	 * it will be fetched from the Ontology Lookup Service (OLS) and saved in the local database.
	 *
	 * @param ontologyId The unique identifier of the ontology to find.
	 * @return An Optional containing the ontology if found, or empty if not found.
	 */
	public Optional<Ontology> findByOntologyId(String ontologyId){
		Optional<Ontology> ontology = ontologyRepository.findByOntologyId(ontologyId);
		if (ontology.isPresent()) {
			logger.info("Ontology found in the local database for ontologyId: {}", ontologyId);
			return ontology;
		} else {
			logger.info("Ontology is not found in the local database for ontologyId: {}", ontologyId);
			Optional<OLSOntology> fetchedOntology = ontologyLookupService.fetchOntologyFromOLS(ontologyId);
			if (fetchedOntology.isPresent()) {
				Ontology newOntology = ontologyLookupService.mapOLSOntologyToOntology(fetchedOntology.get());
				saveOntologyToLocalDatabase(newOntology);
				return Optional.of(newOntology);
			}
		}
		return Optional.empty();
	}

	/**
	 * Saves an ontology to the local database.
	 *
	 * @param ontology The ontology to save.
	 */
	private void saveOntologyToLocalDatabase(Ontology ontology) {
		if (ontology != null) {
			logger.info("Saving ontology in the local database for ontologyId: {}", ontology.getOntologyId());
			ontologyRepository.save(ontology);
			logger.info("Ontology is successfully saved in the local database for ontologyId: {}", ontology.getOntologyId());
		}
	}
}
