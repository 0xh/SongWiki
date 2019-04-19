import React from 'react';
import { Formik, Form, ErrorMessage } from 'formik';

import fetch from '../../utils/fetch';

import { Label, Field, Button } from '../../styles/formikStyle';

const AddSongForm = ({ history }) => {
  const onFormSubmit = (values, { setSubmitting }) => {
    setSubmitting(true);

    fetch('/api/songs', values, 'POST');

    setSubmitting(false);

    history.push('/');
  };

  return (
    <Formik
      initialValues={{
        name: '',
        resource: '',
      }}
      onSubmit={(values, { setSubmitting, setErrors }) =>
        onFormSubmit(values, { setSubmitting, setErrors })
      }
      render={({ isSubmitting }) => (
        <Form>
          <Label htmlFor="name">Name</Label>
          <Field type="text" name="name" />
          <ErrorMessage name="name" component="span" />

          <Label htmlFor="resource">Resource link</Label>
          <Field type="text" name="resource" />
          <ErrorMessage name="resource" component="span" />

          <Button type="submit" disabled={isSubmitting}>
            Add song
          </Button>
        </Form>
      )}
    />
  );
};

export default AddSongForm;
