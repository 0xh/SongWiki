import React from 'react';
import { Formik, Form, ErrorMessage } from 'formik';

import fetch from '../utils/fetch';

import Layout from '../components/Layout';
import { Label, Field, Button } from '../styles/formikStyle';

const onFormSubmit = (values, { setSubmitting, setErrors }) => {
  setSubmitting(true);
  fetch('/api/accounts', values, 'PUT', true).then(response => {
    setSubmitting(false);
    if (response.ok()) {
      // TODO: implement successful update flow
    } else {
      // TODO: implement unsuccessful update flow
      // setErrors();
    }
  });
};

const User = () => (
  <Layout>
    <h1>You personal information</h1>
    <p>Make sure to keep your personal information up-to-date!</p>

    <Formik
      initialValues={{
        username: '',
        password: '',
        email: '',
        age: 20,
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

          <Label htmlFor="email">Email</Label>
          <Field type="email" name="email" />
          <ErrorMessage name="email" component="span" />

          <Label htmlFor="age">Age</Label>
          <Field type="number" name="age" />
          <ErrorMessage name="age" component="span" />

          <Button type="submit" disabled={isSubmitting}>
            Update account information
          </Button>
        </Form>
      )}
    />
  </Layout>
);

export default User;
