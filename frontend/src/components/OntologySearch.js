import React, { useState } from 'react';
import axiosInstance from '../http-ontologies';
import './OntologySearch.css';

function OntologySearch() {
  const [ontologyId, setOntologyId] = useState('');
  const [ontologyData, setOntologyData] = useState(null);
  const [errorMessage, setErrorMessage] = useState(null);

  const handleSearch = async () => {
    try {
      const response = await axiosInstance.get(`/api/ontologies/${ontologyId}`);
      setOntologyData(response.data);
      setErrorMessage(null);
    } catch (error) {
      console.error('Error searching for ontology:', error);
      if (error.response && error.response.data) {
        setErrorMessage(error.response.data);
      } else {
        setErrorMessage('An error occurred while searching for the ontology');
      }
      setOntologyData(null);
    }
  };

  return (
    <div>
      <h2>Ontology Search</h2>
      <div>
        <label>Ontology ID:</label>
        <input
          type="text"
          value={ontologyId}
          onChange={(e) => setOntologyId(e.target.value)}
        />
        <button onClick={handleSearch}>Search</button>
      </div>
      {errorMessage && (
        <div className="error-message">
          <p>{errorMessage}</p>
        </div>
      )}
      {ontologyData && (
        <div className="ontology-details">
          <h3>Ontology Details</h3>
          <div className="grid-container">
            <div className="grid-item">
              <strong>Ontology ID:</strong>
            </div>
            <div className="grid-item">{ontologyData.ontologyId}</div>
            <div className="grid-item">
              <strong>Title:</strong>
            </div>
            <div className="grid-item">{ontologyData.title}</div>
            <div className="grid-item">
              <strong>Description:</strong>
            </div>
            <div className="grid-item">{ontologyData.description}</div>
            <div className="grid-item">
              <strong>Definition Properties:</strong>
            </div>
            <div className="grid-item">
              {ontologyData.definitionProperties.join(', ')}
            </div>
            <div className="grid-item">
              <strong>Synonym Properties:</strong>
            </div>
            <div className="grid-item">
              {ontologyData.synonymProperties.join(', ')}
            </div>
          </div>
        </div>
      )}
    </div>
  );
}

export default OntologySearch;