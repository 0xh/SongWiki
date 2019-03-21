import React, { Component } from 'react';

class App extends Component {
  state = {
    allAccounts: [],
  };

  componentDidMount() {
    fetch('/api/accounts')
      .then(response => response.json())
      .then(data => this.setState({ allAccounts: data }));
  }

  render() {
    const { allAccounts } = this.state;
    return (
      <>
        {allAccounts.map((account, index) => (
          <div key={index}>
            <h1>{account.username}</h1>
            <p>{account.email}</p>
            <p>{account.age}</p>
          </div>
        ))}
      </>
    );
  }
}

export default App;
