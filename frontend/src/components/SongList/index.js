import React, { useEffect, useState } from 'react';

import fetch from '../../utils/fetch';

import { ListWrapper, SongTile, Description } from './styles';

const SongList = () => {
  const [songList, setSongList] = useState([]);
  useEffect(() => {
    fetch('/api/songs')
      .then(response => response.json())
      .then(json => setSongList(json));
  });

  return songList.length > 0 ? (
    <ListWrapper>
      {songList.map((song, index) => (
        <SongTile key={index} random={index}>
          <Description>{song.name}</Description>
        </SongTile>
      ))}
    </ListWrapper>
  ) : (
    <p>Couldn't find any songs :(</p>
  );
};

export default SongList;
