package com.embl.ontologysync.controller;

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.embl.ontologysync.entity.Ontology;
import com.embl.ontologysync.service.OntologyService;

/**
 * Controller class for handling ontology-related HTTP requests.
 *
 * This class provides the endpoint to retrieve ontology information by ontology ID.
 *
 * Author: Ansar Rafique
 * Date: October 10, 2023
 */
@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class OntologyController {

	private static final Logger logger = LoggerFactory.getLogger(OntologyController.class);

	@Autowired
	OntologyService ontologyService;

	/**
	 * Retrieves an ontology by ID.
	 *
	 * @param ontologyId The unique identifier of the ontology.
	 * @return A ResponseEntity containing the ontology if found (HTTP status 200),
	 *         or an error message if the ontology is not found (HTTP status 404).
	 */
	@GetMapping("/ontologies/{ontologyId}")
	public ResponseEntity<Object> getOntology(@PathVariable String ontologyId){
		try {
			logger.info("Received request for ontologyId: {}", ontologyId);
			Optional<Ontology> ontology = ontologyService.findByOntologyId(ontologyId);
			if(ontology.isPresent()) {
				logger.info("Found ontology for ontologyId: {}", ontologyId);
				return new ResponseEntity<>(ontology.get(), HttpStatus.OK);
			}else {
				logger.warn("Ontology not found for ontologyId: {}", ontologyId);
				String errorMessage = "Error: Ontology not found";
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
			}
		}catch(Exception exp) {
			logger.error("An error occurred while processing the request for ontologyId: {}", ontologyId, exp);
			String errorMessage = "An error occurred while processing the request";
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
		}
	}
}
