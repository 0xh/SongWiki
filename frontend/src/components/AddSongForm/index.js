import React from 'react';
import { Formik, Form, ErrorMessage } from 'formik';

import fetch from '../../utils/fetch';
import { parseVideoId } from '../../utils/youtube';

import { Label, Field, Button } from '../../styles/formikStyle';

const AddSongForm = ({ history }) => {
  const onFormSubmit = (values, { setSubmitting }) => {
    setSubmitting(true);

    const resource = parseVideoId(values.resource);
    fetch('/api/songs', { name: values.name, resource }, 'POST');

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

          <Label htmlFor="resource">YouTube link (URL)</Label>
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
