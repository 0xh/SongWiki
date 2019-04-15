import React from 'react';
import { Formik, Form, ErrorMessage } from 'formik';

import { fetchJWT } from '../../utils/jwt';
import fetch from '../../utils/fetch';

import { Label, Field, Button } from '../../styles/formikStyle';
import { AuthenticationConsumer } from '../../utils/authenticationContext';

const RegisterForm = ({ history }) => {
  const onFormSubmit = (
    values,
    { setSubmitting, setErrors },
    setAuthenticated
  ) => {
    setSubmitting(true);

    fetch('/api/accounts', values, 'POST')
      .then(response => {
        if (response.ok) {
          fetchJWT(values.username, values.password);
          setSubmitting(false);
          setAuthenticated(true);
          history.push({
            pathname: '/two-factor-auth',
            state: { name: values.username, initialSetup: true },
          });
        } else {
          throw new Error(response.statusText);
        }
      })
      .catch(error => {
        setSubmitting(false);
        // setErrors(transformMyRestApiErrorsToAnObject(error));
        alert(error);
      });
  };

  return (
    <AuthenticationConsumer>
      {({ setAuthenticated }) => (
        <Formik
          initialValues={{
            username: '',
            password: '',
            email: '',
            age: 20,
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

              <Label htmlFor="email">Email</Label>
              <Field type="email" name="email" />
              <ErrorMessage name="email" component="span" />

              <Label htmlFor="age">Age</Label>
              <Field type="number" name="age" />
              <ErrorMessage name="age" component="span" />

              <Button type="submit" disabled={isSubmitting}>
                Create account
              </Button>
            </Form>
          )}
        />
      )}
    </AuthenticationConsumer>
  );
};

export default RegisterForm;
