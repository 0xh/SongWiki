import React from 'react';
import { Formik, Field, Form, ErrorMessage } from 'formik';

import Layout from '../components/Layout';

class Register extends React.Component {
  render() {
    const { history } = this.props;
    return (
      <Layout>
        <h1>Register</h1>
        <p>Get an account for SongWiki to enjoy the full experience!</p>

        <Formik
          initialValues={{
            username: '',
            password: '',
            email: '',
            age: 20,
          }}
          onSubmit={(values, { setSubmitting, setErrors }) => {
            setSubmitting(true);

            fetch('/api/accounts', {
              method: 'POST',
              headers: {
                Accept: 'application/json',
                'Content-Type': 'application/json',
              },
              body: JSON.stringify(values),
            })
              .then(response => {
                if (response.ok) {
                  setSubmitting(false);
                  history.push('/');
                } else {
                  throw new Error(response.statusText);
                }
              })
              .catch(error => {
                setSubmitting(false);
                // setErrors(transformMyRestApiErrorsToAnObject(error));
                alert(error);
              });
          }}
          render={({ isSubmitting }) => (
            <Form>
              <label htmlFor="username">Username</label>
              <Field type="text" name="username" />
              <ErrorMessage name="username" component="span" />

              <label htmlFor="password">Password</label>
              <Field type="password" name="password" />
              <ErrorMessage name="password" component="span" />

              <label htmlFor="email">Email</label>
              <Field type="email" name="email" />
              <ErrorMessage name="email" component="span" />

              <label htmlFor="age">Age</label>
              <Field type="number" name="age" />
              <ErrorMessage name="age" component="span" />

              <button type="submit" disabled={isSubmitting}>
                Create account
              </button>
            </Form>
          )}
        />
      </Layout>
    );
  }
}

export default Register;
