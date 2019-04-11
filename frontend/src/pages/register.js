import React from 'react';

import Layout from '../components/Layout';
import RegisterForm from '../components/RegisterForm';

const Register = () => (
  <Layout>
    <h1>Register</h1>
    <p>Get an account for SongWiki to enjoy the full experience!</p>

    <RegisterForm />
  </Layout>
);

export default Register;
