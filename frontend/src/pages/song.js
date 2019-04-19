import React from 'react';
import YouTube from 'react-youtube';

import Layout from '../components/Layout';

const parseVideoId = resource => resource.split('v=')[1];

const Song = ({
  location: {
    state: { song },
  },
}) => (
  <Layout>
    <h1>{song.name}</h1>
    <YouTube videoId={parseVideoId(song.resource)} />
  </Layout>
);

export default Song;
