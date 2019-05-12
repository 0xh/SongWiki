import React, { useState } from 'react';

import Layout from '../components/Layout';
import RegisterForm from '../components/RegisterForm';
import TwoFactorAuth from '../components/TwoFactorAuth';

const Register = ({ history }) => {
  const [persistedUser, setPersistedUser] = useState();
  return (
    <Layout>
      <h1>Register</h1>
      <p>Get an account for SongWiki to enjoy the full experience!</p>

      <RegisterForm setPersistedUser={setPersistedUser} />
      {persistedUser && (
        <TwoFactorAuth history={history} user={persistedUser} initialSetup />
      )}
    </Layout>
  );
};

export default Register;
