import React, { useState, useEffect } from 'react';
import { Formik, Form, ErrorMessage } from 'formik';
import jwtDecode from 'jwt-decode';

import fetch from '../../utils/fetch';
import { getJWTToken } from '../../utils/localStorage';

import { Label, Field, Button, SongOption } from '../../styles/formikStyle';

const CreatePlaylistForm = ({ history }) => {
  const [availableSongs, setAvailableSongs] = useState([]);

  useEffect(() => {
    fetch('/api/songs')
      .then(response => response.json())
      .then(songsJson => setAvailableSongs(songsJson));
  }, []);

  const onFormSubmit = (values, { setSubmitting }) => {
    setSubmitting(true);

    const username = jwtDecode(getJWTToken()).sub;

    // Filter over the given selected IDs and find the full song object
    const songInfo = availableSongs.filter(song =>
      values.songs.includes(song.songId)
    );

    // Remove the IDs from the Formik values before spreading
    delete values.songs;

    const necessaryInfo = {
      ...values,
      account: { username },
      songs: songInfo,
    };

    fetch('/api/playlists', necessaryInfo, 'POST').then(() => {
      setSubmitting(false);
      history.push('/playlists');
    });
  };

  const selectedSongs = [];

  return (
    <Formik
      initialValues={{
        name: '',
        description: '',
        songs: selectedSongs,
      }}
      onSubmit={(values, { setSubmitting, setErrors }) =>
        onFormSubmit(values, { setSubmitting, setErrors })
      }
      render={({ isSubmitting, setFieldValue }) => (
        <Form>
          <Label htmlFor="name">Name</Label>
          <Field type="text" name="name" />
          <ErrorMessage name="name" component="span" />

          <Label htmlFor="description">Description</Label>
          <Field type="text" name="description" />
          <ErrorMessage name="description" component="span" />

          <Label htmlFor="songs">Songs</Label>
          <Field
            multiple
            component="select"
            name="songs"
            // Set Formik 'songs' value to the full song object filtered by the selected value (ID).
            // The first item in the returned array is used as ID's are unique.
            onChange={evt =>
              setFieldValue(
                'songs',
                [].slice
                  .call(evt.target.selectedOptions)
                  .map(option => parseInt(option.value))
              )
            }
          >
            {availableSongs.map(song => (
              <SongOption value={song.songId}>{song.name}</SongOption>
            ))}
          </Field>
          <ErrorMessage name="songs" component="span" />

          <Button type="submit" disabled={isSubmitting}>
            Create playlist
          </Button>
        </Form>
      )}
    />
  );
};

export default CreatePlaylistForm;
