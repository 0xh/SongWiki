import React, { useState } from 'react';

import Layout from '../components/Layout';
import LoginForm from '../components/LoginForm';
import TwoFactorAuth from '../components/TwoFactorAuth';

const Login = ({ history }) => {
  const [loggedInUser, setLoggedInUser] = useState();
  return (
    <Layout>
      <h1>Log in</h1>
      <p>Log in to SongWiki to enjoy the full experience!</p>

      <LoginForm setLoggedInUser={setLoggedInUser} />
      {loggedInUser && <TwoFactorAuth history={history} user={loggedInUser} />}
    </Layout>
  );
};

export default Login;
