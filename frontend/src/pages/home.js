import React, { Component } from 'react';

import Layout from '../components/Layout';

class Home extends Component {
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
      <Layout>
        {allAccounts.map((account, index) => (
          <div key={index}>
            <h1>{account.username}</h1>
            <p>{account.email}</p>
            <p>{account.age}</p>
          </div>
        ))}
      </Layout>
    );
  }
}

export default Home;
