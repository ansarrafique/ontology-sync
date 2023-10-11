package com.embl.ontologysync.ols;

/**
 * A simple POJO class, which represents an ontology in Ontology Lookup Service (OLS).
 * This class is used to store information about an ontology retrieved from OLS.
 *
 * Author: Ansar Rafique
 * Date: October 10, 2023
 */
public class OLSOntology {
	private String ontologyId;
	private Config config;

	public OLSOntology() {
	}

	/**
	 * Parameterized constructor to initialize an OLSOntology instance.
	 * @param ontologyId The unique identifier for the ontology.
	 * @param config The configuration of the ontology.
	 */
	public OLSOntology(String ontologyId, Config config) {
		this.ontologyId = ontologyId;
		this.config = config;
	}

	/**
	 * Get the unique identifier for the ontology.
	 *
	 * @return The ontology identifier.
	 */
	public String getOntologyId() {
		return ontologyId;
	}

	/**
	 * Set the unique identifier for the ontology.
	 *
	 * @param ontologyId The ontology identifier to set.
	 */
	public void setOntologyId(String ontologyId) {
		this.ontologyId = ontologyId;
	}

	/**
	 * Get the configuration of the ontology.
	 *
	 * @return The ontology configuration.
	 */
	public Config getConfig() {
		return config;
	}

	/**
	 * Set the configuration of the ontology.
	 *
	 * @param config The ontology configuration to set.
	 */
	public void setConfig(Config config) {
		this.config = config;
	}
}

