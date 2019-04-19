import React from 'react';
import YouTube from 'react-youtube';

import Layout from '../components/Layout';

const Song = ({
  location: {
    state: { song },
  },
}) => (
  <Layout>
    <h1>{song.name}</h1>
    <YouTube videoId={song.resource} />
  </Layout>
);

export default Song;
