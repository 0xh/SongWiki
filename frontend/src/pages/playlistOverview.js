import React, { useEffect, useState } from 'react';
import jwtDecode from 'jwt-decode';

import { getJWTToken } from '../utils/localStorage';

import Layout from '../components/Layout';
import Playlists from '../components/Playlists';
import StyledLink from '../components/StyledLink';

const PlayListOverview = () => {
  const [username, setUsername] = useState('');
  useEffect(() => {
    const token = getJWTToken();
    const decodedToken = jwtDecode(token);
    setUsername(decodedToken.sub);
  }, []);

  return (
    <Layout>
      <h1>{username}'s playlists</h1>
      <Playlists username={username} />
      <StyledLink to="create-playlist">Create playlist</StyledLink>
    </Layout>
  );
};

export default PlayListOverview;
