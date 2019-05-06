import styled from 'styled-components';

export const StyledButton = styled.button`
  text-decoration: none;
  padding: 0.5rem 1rem;
  border: 0 solid #dae1e7;
  border-radius: 0.25rem;
  font-size: 1rem;
  cursor: pointer;
  color: white;
  background-color: ${props => props.color};

  &:hover {
    background-color: ${props => props.hoverColor};
  }
`;
