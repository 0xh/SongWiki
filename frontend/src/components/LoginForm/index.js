import React from 'react';
import { Formik, Form, ErrorMessage } from 'formik';

import { Label, Field, Button } from './styles';
import { fetchJWT } from '../../utils/jwt';

const LoginForm = () => {
  const onFormSubmit = async (values, { setSubmitting }) => {
    setSubmitting(true);
    await fetchJWT(values.username, values.password);
    setSubmitting(false);
  };

  return (
    <Formik
      initialValues={{
        username: '',
        password: '',
      }}
      onSubmit={(values, { setSubmitting, setErrors }) =>
        onFormSubmit(values, { setSubmitting, setErrors })
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
  );
};

export default LoginForm;
