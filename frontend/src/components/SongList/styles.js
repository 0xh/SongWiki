import styled from 'styled-components';

export const ListWrapper = styled.ul`
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  margin-bottom: 15px;
`;

export const SongTile = styled.li`
  display: flex;
  width: 49%;
  height: 60vh;
  margin-bottom: 20px;
  border-radius: 10px;
  background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
    url(https://img.youtube.com/vi/${props => props.songResource}/maxresdefault.jpg);
  background-size: cover;
  cursor: pointer;
`;

export const Description = styled.p`
  align-self: flex-end;
  padding: 0 20px;
  color: white;
  font-size: 2em;
`;
