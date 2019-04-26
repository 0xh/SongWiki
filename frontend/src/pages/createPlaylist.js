import React from 'react';

import Layout from '../components/Layout';
import CreatePlaylistForm from '../components/CreatePlaylistForm';

const CreatePlaylist = ({ history }) => (
  <Layout>
    <h1>Create new playlist</h1>
    <CreatePlaylistForm history={history} />
  </Layout>
);

export default CreatePlaylist;
