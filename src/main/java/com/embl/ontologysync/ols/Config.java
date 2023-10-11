package com.embl.ontologysync.ols;

import java.util.List;

/**
 * Represents the configuration of an ontology in Ontology Lookup Service (OLS).
 *
 * Author: Ansar Rafique
 * Date: October 10, 2023
 */
public class Config {
	private String id;
	private String versionIri;
	private String namespace;
	private String preferredPrefix;
	private String homepage;
	private String version;
	private String mailingList;
	private String tracker;
	private String fileLocation;
	private boolean oboSlims;
	private String labelProperty;
	private List<String> definitionProperties;
	private List<String> synonymProperties;
	private String title;
	private String description;

	public Config() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getVersionIri() {
		return versionIri;
	}

	public void setVersionIri(String versionIri) {
		this.versionIri = versionIri;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getPreferredPrefix() {
		return preferredPrefix;
	}

	public void setPreferredPrefix(String preferredPrefix) {
		this.preferredPrefix = preferredPrefix;
	}

	public String getHomepage() {
		return homepage;
	}

	public void setHomepage(String homepage) {
		this.homepage = homepage;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMailingList() {
		return mailingList;
	}

	public void setMailingList(String mailingList) {
		this.mailingList = mailingList;
	}

	public String getTracker() {
		return tracker;
	}

	public void setTracker(String tracker) {
		this.tracker = tracker;
	}

	public String getFileLocation() {
		return fileLocation;
	}

	public void setFileLocation(String fileLocation) {
		this.fileLocation = fileLocation;
	}

	public boolean isOboSlims() {
		return oboSlims;
	}

	public void setOboSlims(boolean oboSlims) {
		this.oboSlims = oboSlims;
	}

	public String getLabelProperty() {
		return labelProperty;
	}

	public void setLabelProperty(String labelProperty) {
		this.labelProperty = labelProperty;
	}

	public List<String> getDefinitionProperties() {
		return definitionProperties;
	}

	public void setDefinitionProperties(List<String> definitionProperties) {
		this.definitionProperties = definitionProperties;
	}

	public List<String> getSynonymProperties() {
		return synonymProperties;
	}

	public void setSynonymProperties(List<String> synonymProperties) {
		this.synonymProperties = synonymProperties;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
