import React, { useEffect, useState } from 'react';

import fetch from '../../utils/fetch';

import { PlaylistContainer, Playlist, Heading, Description } from './styles';

const Playlists = ({ username }) => {
  const [playlists, setPlaylists] = useState([]);

  useEffect(() => {
    if (username !== '') {
      fetch(`/api/playlists/account/${username}`)
        .then(response => response.json())
        .then(playlistJson => setPlaylists(playlistJson));
    }
  }, [username]);

  return (
    <PlaylistContainer>
      {playlists.length > 0 ? (
        playlists.map((playlist, index) => (
          <Playlist key={index}>
            <Heading>{playlist.name}</Heading>
            <Description>{playlist.description}</Description>
          </Playlist>
        ))
      ) : (
        <p>No playlists yet</p>
      )}
    </PlaylistContainer>
  );
};

export default Playlists;
