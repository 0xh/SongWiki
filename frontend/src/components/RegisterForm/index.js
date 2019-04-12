import React from 'react';
import { Formik, Form, ErrorMessage } from 'formik';

import { Label, Field, Button } from './styles';

const onFormSubmit = (values, { setSubmitting, setErrors }, history) => {
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
        history.push({
          pathname: '/two-factor-auth',
          state: { name: values.username },
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

const RegisterForm = ({ history }) => (
  <Formik
    initialValues={{
      username: '',
      password: '',
      email: '',
      age: 20,
    }}
    onSubmit={(values, { setSubmitting, setErrors }) =>
      onFormSubmit(values, { setSubmitting, setErrors }, history)
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
);

export default RegisterForm;
