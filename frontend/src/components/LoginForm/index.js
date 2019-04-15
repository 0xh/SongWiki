import React from 'react';
import { Formik, Form, ErrorMessage } from 'formik';

import { Label, Field, Button } from '../../styles/formikStyle';
import { fetchJWT } from '../../utils/jwt';
import { AuthenticationConsumer } from '../../utils/authenticationContext';

const LoginForm = ({ history }) => {
  const onFormSubmit = (values, { setSubmitting }, setAuthenticated) => {
    setSubmitting(true);
    fetchJWT(values.username, values.password);
    setAuthenticated(true);
    setSubmitting(false);

    history.push('/');
  };

  return (
    <AuthenticationConsumer>
      {({ setAuthenticated }) => (
        <Formik
          initialValues={{
            username: '',
            password: '',
          }}
          onSubmit={(values, { setSubmitting, setErrors }) =>
            onFormSubmit(values, { setSubmitting, setErrors }, setAuthenticated)
          }
          render={({ isSubmitting }) => (
            <Form>
              <Label htmlFor="username">Username</Label>
              <Field type="text" name="username" />
              <ErrorMessage name="username" component="span" />

              <Label htmlFor="password">Password</Label>
              <Field type="password" name="password" />
              <ErrorMessage name="password" component="span" />

              <Button type="submit" disabled={isSubmitting}>
                Log in
              </Button>
            </Form>
          )}
        />
      )}
    </AuthenticationConsumer>
  );
};

export default LoginForm;
