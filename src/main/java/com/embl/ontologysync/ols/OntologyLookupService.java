package com.embl.ontologysync.ols;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.embl.ontologysync.entity.Ontology;

/**
 * Service class for interacting with the Ontology Lookup Service (OLS).
 *
 * This class provides methods to fetch ontology information from OLS and map it to local ontology entities.
 *
 * Author: Ansar Rafique
 * Date: October 10, 2023
 */
@Service
public class OntologyLookupService {

	private static final Logger logger = LoggerFactory.getLogger(OntologyLookupService.class);
	
	private final String OLS_URL = "https://www.ebi.ac.uk/ols4/api/ontologies/";

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Fetches an ontology from OLS by ontology ID.
	 *
	 * @param ontologyId The unique identifier of the ontology to fetch.
	 * @return An Optional containing the fetched ontology or empty if not found.
	 */
	public Optional<OLSOntology> fetchOntologyFromOLS(String ontologyId) {
		logger.info("Fetching ontology from Ontology Lookup Service (OLS) for ontologyId: {}", ontologyId);
		String olsUrlOntology = OLS_URL + ontologyId;
		try {
			ResponseEntity<OLSOntology> response = restTemplate.exchange(
					olsUrlOntology,
					HttpMethod.GET,
					null,
					OLSOntology.class
					);

			if (response.getStatusCode().is2xxSuccessful() && response.getBody() != null) {
				logger.info("Fetched ontology from OLS for ontologyId: {}", ontologyId);
				return Optional.of(response.getBody());
			}
		} catch (RestClientException e) {
			logger.error("An error occurred while fetching ontology from Ontology Lookup Service (OLS) for ontologyId: {} with the exception message: {}", ontologyId, e.getMessage());
		}
		return Optional.empty();
	}

	/**
	 * Maps an OLSOntology response to an Ontology entity.
	 *
	 * @param olsOntologyResponse The response from OLS.
	 * @return The mapped Ontology entity.
	 */
	public Ontology mapOLSOntologyToOntology(OLSOntology olsOntologyResponse) {
		Ontology newOntology = new Ontology();
		newOntology.setOntologyId(olsOntologyResponse.getOntologyId());
		Config config = olsOntologyResponse.getConfig();
		if (config != null) {
			newOntology.setTitle(config.getTitle());
			newOntology.setDescription(config.getDescription());
			newOntology.setDefinitionProperties(config.getDefinitionProperties());
			newOntology.setSynonymProperties(config.getSynonymProperties());
		}
		return newOntology;
	}

}
