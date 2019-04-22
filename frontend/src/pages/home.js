import React from 'react';

import Layout from '../components/Layout';
import SongList from '../components/SongList';
import StyledLink from '../components/StyledLink';

const Home = ({ history }) => (
  <Layout>
    <SongList history={history} />
    <StyledLink to="/add-song">Add song</StyledLink>
  </Layout>
);

export default Home;
