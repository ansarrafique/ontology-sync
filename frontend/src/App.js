import React from 'react';
import './App.css';
import OntologySearch from './components/OntologySearch';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <h1>Ontology Synchronization Service</h1>
      </header>
      <main>
        <OntologySearch />
      </main>
    </div>
  );
}

export default App;
