import React from 'react';

import Layout from '../components/Layout';
import AddSongForm from '../components/AddSongForm';

const AddSong = ({ history }) => (
  <Layout>
    <h1>Add song</h1>
    <p>Complete the form down below and add your song to the collection!</p>
    <AddSongForm history={history} />
  </Layout>
);

export default AddSong;
