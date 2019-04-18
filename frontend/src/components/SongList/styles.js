import styled from 'styled-components';

export const ListWrapper = styled.ul`
  display: flex;
  justify-content: space-between;
`;

export const SongTile = styled.li`
  display: flex;
  width: 49%;
  height: 60vh;
  border-radius: 10px;
  background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)),
    url(https://source.unsplash.com/collection/403065/500x500/?sig=${props => props.random});
  background-size: cover;
  cursor: pointer;
`;

export const Description = styled.p`
  align-self: flex-end;
  padding: 0 20px;
  color: white;
  font-size: 2em;
`;
