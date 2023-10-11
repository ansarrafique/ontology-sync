package com.embl.ontologysync.repository;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.embl.ontologysync.entity.Ontology;

/**
 * Repository interface for managing ontology data in the MongoDB database.
 *
 * Author: Ansar Rafique
 * Date: October 10, 2023
 */
public interface OntologyRepository extends MongoRepository<Ontology, String> {
	/**
	 * Find an ontology by ID.
	 *
	 * @param ontologyId The unique identifier of the ontology to find.
	 * @return An Optional containing the ontology if found, or empty if not found.
	 */
	public Optional<Ontology> findByOntologyId(String ontologyId);
}
