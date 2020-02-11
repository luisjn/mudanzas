import React, { Component } from 'react';
import axios from 'axios';

import Form from './components/Form';

class App extends Component{

  makeMove = (dni, file) => {

    const formData = new FormData();
    formData.append('file', file);

    const config = {
      headers: {
          'content-type': 'multipart/form-data'
      }
    }
    axios.post(`http://localhost:8080/mudanza/${dni}`, formData, config)
    .then(response => {
      console.log(response);
      
    })
    .catch(error => {
      alert(error.response.data);
    })
  }

  render(){
    return(
      <div>
        <Form makeMove={this.makeMove}/>
      </div>
    );
  }
}

export default App;