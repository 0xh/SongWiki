import React, { useEffect, useState } from 'react';

import Layout from '../components/Layout';
import Songlist from '../components/SongList';
import Button from '../components/Button';

import theme from '../styles/theme';

import fetch from '../utils/fetch';

const Playlist = ({
  match: {
    params: { playlistId },
  },
  history,
}) => {
  const [playlist, setPlaylist] = useState({});

  useEffect(() => {
    fetch(`/api/playlists/${playlistId}`)
      .then(response => response.json())
      .then(fetchedPlaylist => {
        setPlaylist(fetchedPlaylist);
      });
  }, [playlistId]);

  const removePlaylist = () => {
    fetch(`/api/playlists/${playlist.playlistId}`, null, 'DELETE', true).then(
      () => history.push('/playlists')
    );
  };

  return (
    <Layout>
      <h1>Playlist '{playlist.name}'</h1>
      <Songlist history={history} providedSongs={playlist.songs} />
      <Button
        color={theme.dangerColor}
        hoverColor={theme.dangerColorDark}
        onClick={removePlaylist}
      >
        Remove playlist
      </Button>
    </Layout>
  );
};

export default Playlist;
