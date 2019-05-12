import React from 'react';
import { Formik, Form, ErrorMessage } from 'formik';

import fetch from '../../utils/fetch';

import { Label, Field, Button } from '../../styles/formikStyle';

const RegisterForm = ({ setPersistedUser }) => {
  const onFormSubmit = (values, { setSubmitting, setErrors }) => {
    setSubmitting(true);

    fetch('/api/accounts', values, 'POST')
      .then(response => {
        if (response.ok) {
          setSubmitting(false);
          setPersistedUser({
            username: values.username,
            password: values.password,
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
            Create account
          </Button>
        </Form>
      )}
    />
  );
};

export default RegisterForm;
