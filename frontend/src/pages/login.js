import React from 'react';

import Layout from '../components/Layout';
import LoginForm from '../components/LoginForm';

const Login = ({ history }) => (
  <Layout>
    <h1>Log in</h1>
    <p>Log in to SongWiki to enjoy the full experience!</p>

    <LoginForm history={history} />
  </Layout>
);

export default Login;
