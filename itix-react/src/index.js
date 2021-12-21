import React, {Component} from 'react';
import ReactDOM from 'react-dom';
import Teams from './components/teams';

import './index.css';

class Itix extends Component {

  state = {
    teams: [],
  };

  componentDidMount() {
    const contracts = 'http://jsonplaceholder.typicode.com/users';
    const wsMatches = 'http://localhost:4567/getMatches';

    fetch(wsMatches)
    .then(res => res.json())
    .then((data) => {
      this.setState({teams: data});
    })
    .catch(console.log);
  }

  render() {
    return (
        <Teams teams={this.state.teams}/>
    );
  }
}

export default Itix;

ReactDOM.render(<Itix/>, document.getElementById('root'));

