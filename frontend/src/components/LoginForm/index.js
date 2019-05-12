import React from 'react';
import { Formik, Form, ErrorMessage } from 'formik';

import fetch from '../../utils/fetch';

import { Label, Field, Button } from '../../styles/formikStyle';

const LoginForm = ({ setLoggedInUser }) => (
  <Formik
    initialValues={{
      username: '',
      password: '',
    }}
    onSubmit={values => {
      fetch(
        `/api/accounts/check-password`,
        {
          username: values.username,
          password: values.password,
        },
        'POST'
      )
        .then(response => response.json())
        .then(isCorrectLogin => {
          if (isCorrectLogin)
            setLoggedInUser({
              username: values.username,
              password: values.password,
            });
        });
    }}
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
);

export default LoginForm;
