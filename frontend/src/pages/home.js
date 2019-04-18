import React from 'react';

import Layout from '../components/Layout';
import SongList from '../components/SongList';
import LinkButton from '../components/LinkButton';

const Home = () => (
  <Layout>
    <SongList />
    <LinkButton to="/add-song">Add song</LinkButton>
  </Layout>
);

export default Home;
