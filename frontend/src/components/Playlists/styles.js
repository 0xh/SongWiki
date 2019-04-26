import styled from 'styled-components';

export const PlaylistContainer = styled.ul`
  display: flex;
  flex-wrap: wrap;
  margin-bottom: 25px;
`;

export const Playlist = styled.li`
  min-width: 80px;
  border-radius: 4px;
  padding: 20px;
  margin-right: 25px;
  box-shadow: 0 1px 4px hsla(0, 0%, 0%, 0.16);
  border-color: hsla(0, 0%, 0%, 0.12);
  cursor: pointer;
`;

export const Heading = styled.h1`
  font-size: 20px;
  margin: 0 0 8px 0;
`;

export const Description = styled.p`
  font-size: 14px;
  color: rgb(102, 102, 102);
  margin: 8px 0 0 0;
`;
