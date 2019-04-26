import React, { useEffect, useState } from 'react';

import fetch from '../../utils/fetch';

import { ListWrapper, SongTile, Description } from './styles';

const SongList = ({ history, providedSongs }) => {
  const [songList, setSongList] = useState([]);

  useEffect(() => {
    if (providedSongs == null)
      fetch('/api/songs')
        .then(response => response.json())
        .then(json => setSongList(json));
    else setSongList(providedSongs);
  }, [providedSongs]);

  return songList.length > 0 ? (
    <ListWrapper>
      {songList.map((song, index) => (
        <SongTile
          key={index}
          songResource={song.resource}
          onClick={() => history.push({ pathname: '/song', state: { song } })}
        >
          <Description>{song.name}</Description>
        </SongTile>
      ))}
    </ListWrapper>
  ) : (
    <p>Couldn't find any songs :(</p>
  );
};

export default SongList;
