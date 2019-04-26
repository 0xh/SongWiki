import React, { useEffect, useState } from 'react';
import Layout from '../components/Layout';

import fetch from '../utils/fetch';

const Playlist = ({
  match: {
    params: { playlistId },
  },
}) => {
  const [songs, setSongs] = useState([]);
  const [playlistName, setPlaylistName] = useState('Playlist');

  useEffect(() => {
    fetch(`/api/songs/playlist/${playlistId}`)
      .then(response => response.json())
      .then(fetchedSongs => {
        setSongs(fetchedSongs);
        // Parse the name of the returned playlist
        // As the songs of one specific playlist are fetched the names are all equal
        // The first items are chosen to be parsed
        setPlaylistName(fetchedSongs[0].playlists[0].name);
      });
  }, [playlistId]);

  return (
    <Layout>
      <h1>Playlist '{playlistName}'</h1>
      {songs.map((song, index) => (
        <p key={index}>{song.name}</p>
      ))}
    </Layout>
  );
};

export default Playlist;
