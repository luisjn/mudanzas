import React, { Component } from "react";

class Form extends Component {
  constructor(props) {
    super(props);
    this.state = {
      file: null
    }
    this.dniRef = React.createRef();
    this.makeMove = this.makeMove.bind(this);
    this.onChangeFile = this.onChangeFile.bind(this)
  }

  makeMove(e) {
    e.preventDefault();

    const dni = this.dniRef.current.value;

    this.props.makeMove(dni, this.state.file);

  }

  onChangeFile(e) {
    this.setState(
      {
        file: e.target.files[0]
      }
    )
  }

  render() {
    return (
      <div>
        <form onSubmit={this.makeMove}>
          <div>
            <label htmlFor="dni">Cédula del transportador</label>
            <input type="text" required ref={this.dniRef} id="dni" />
          </div>
          <div>
            <label htmlFor="task">Tareas</label>
            <input type="file" onChange={this.onChangeFile} id="task" />
          </div>
          <button type="submit" className="btn btn-primary">Procesar</button>
        </form>
      </div>
    )
  }
}

export default Form;