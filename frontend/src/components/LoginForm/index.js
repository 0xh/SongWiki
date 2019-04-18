import React, { useContext } from 'react';
import { Formik, Form, ErrorMessage } from 'formik';

import { Label, Field, Button } from '../../styles/formikStyle';
import { fetchJWT } from '../../utils/jwt';
import { AuthenticationContext } from '../../utils/authenticationContext';

const LoginForm = ({ history }) => {
  const { setAuthenticated } = useContext(AuthenticationContext);

  const onFormSubmit = (values, { setSubmitting }) => {
    setSubmitting(true);
    fetchJWT(values.username, values.password);
    setAuthenticated(true);
    setSubmitting(false);

    history.push({
      pathname: '/two-factor-auth',
      state: { name: values.username, initialSetup: false },
    });
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
