import React, { useEffect, useState } from 'react';

import fetch from '../../utils/fetch';

import { ListWrapper, SongTile, Description } from './styles';

const SongList = ({ history }) => {
  const [songList, setSongList] = useState([]);
  useEffect(() => {
    fetch('/api/songs')
      .then(response => response.json())
      .then(json => setSongList(json));
  }, []);

  return songList.length > 0 ? (
    <ListWrapper>
      {songList.map((song, index) => (
        <SongTile
          key={index}
          random={index}
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
