import React from 'react';
import './App.css';
import axios from "axios";


export class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      dimension: 10,
      particlesNo: 100,
      maxIterations: 500,
      c1: 2,
      c2: 2,
      inertia: 0.4,
      foundMin: "",
      realMin: "",
      absoluteError: "",
      usedIterations: "",
      arguments: ""
    }
    this.updateInput = this.updateInput.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
    this.GET_URL = "http://localhost:8080/api/pso/ackley?";

  }

  getInitialState() {
    return {
      dimension: 10,
      particlesNo: 100,
      maxIterations: 500,
      c1: 2,
      c2: 2,
      inertia: 0.4
    }
  }


  showOutput = (data) => {
    let args = "";
    for (let i = 0; i < data.arguments.length; ++i)
      args += "x(" + (i + 1) + "): " + data.arguments[i] + "\n";
    this.setState({
      ...this.state,
      foundMin: parseFloat(data.functionMinimum).toFixed(6),
      realMin: parseFloat(data.realMinimum).toFixed(6),
      absoluteError: parseFloat(data.absoluteError).toFixed(6),
      usedIterations: data.iterations,
      arguments: args
    });
  }

  updateInput(event) {
    this.setState({
      ...this.state,
      [event.target.id]: event.target.value
    });
  }
  handleSubmit = () => {
    // eslint-disable-next-line
    var particlesNo = this.state.particlesNo != "" ? this.state.particlesNo : this.getInitialState().particlesNo;
    // eslint-disable-next-line
    var maxIterations = this.state.maxIterations != "" ? this.state.maxIterations : this.getInitialState().maxIterations;
    // eslint-disable-next-line
    var dimension = this.state.dimension != "" ? this.state.dimension : this.getInitialState().dimension;
    // eslint-disable-next-line
    var c1 = this.state.c1 != "" ? this.state.c1 : this.getInitialState().c1;
    // eslint-disable-next-line
    var c2 = this.state.c2 != "" ? this.state.c2 : this.getInitialState().c2;
    // eslint-disable-next-line
    var inertia = this.state.inertia != "" ? this.state.inertia : this.getInitialState().inertia;
    var URL = this.GET_URL + "particlesNo=" + particlesNo + "&maxIterations=" + maxIterations + "&maxVelocity=1.0&inertia=" + inertia + "&c1=" + c1 + "&c2=" + c2 + "&dimension=" + dimension;
    axios.get(URL, {
      headers: {
        'Access-Control-Allow-Origin': '*'
      }
    }
    ).then(response => this.showOutput(response.data));

  }

  render() {
    return (
      <React.Fragment>
        <div id="title">
          Function
      </div>
        <div id="flex-container">
          <span id="input">
            <div id="category-title">Algorithm parameters</div>

            <label>Problem dimension:</label>
            <input type="number" id="dimension" min="1" placeholder={this.getInitialState().dimension} onChange={this.updateInput}></input>

            <label>Particles number:</label>
            <input type="number" id="particlesNo" min="0" placeholder={this.getInitialState().particlesNo} onChange={this.updateInput}></input>

            <label>Maximum iterations:</label>
            <input type="number" id="maxIterations" min="0" placeholder={this.getInitialState().maxIterations} onChange={this.updateInput}></input>

            <label>Coefficient c1:</label>
            <input type="number" id="c1" step="0.1" placeholder={this.getInitialState().c1} onChange={this.updateInput}></input>

            <label>Coefficient c2:</label>
            <input type="number" id="c2" step="0.1" placeholder={this.getInitialState().c2} onChange={this.updateInput}></input>

            <label>Inertia:</label>
            <input type="number" id="inertia" step="0.1" placeholder={this.getInitialState().inertia} onChange={this.updateInput}></input>

            <button onClick={this.handleSubmit}>Submit</button>

          </span>
          <span id="output">
            <div id="category-title">
              Algorithm output</div>
            <label>Found minimum:</label>
            <input type="number" readOnly value={this.state.foundMin}></input>

            <label>Real minimum:</label>
            <input type="number" readOnly value={this.state.realMin}></input>

            <label>Absolute error:</label>
            <input type="number" readOnly value={this.state.absoluteError}></input>

            <label>Iterrations:</label>
            <input type="number" readOnly value={this.state.usedIterations}></input>

            <label>Arguments:</label>
            <textarea readOnly value={this.state.arguments}></textarea>


          </span>
        </div>
      </React.Fragment>
    );
  }
}

export default App;
