import styled from 'styled-components';
import { Link } from 'react-router-dom';

export const StyledLink = styled(Link)`
  text-decoration: none;
  padding: 0.5rem 1rem;
  border: 0 solid #dae1e7;
  background-color: ${props => props.theme.primaryColor};
  color: white;
  border-radius: 0.25rem;
  font-size: 1rem;
  cursor: pointer;

  &:hover {
    background-color: ${props => props.theme.primaryColorDark};
  }
`;
