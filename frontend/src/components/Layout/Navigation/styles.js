import styled from 'styled-components';
import { Link } from 'react-router-dom';

const NavStyles = styled.nav`
  display: flex;
  width: 100%;
  justify-content: space-between;
  margin: 0 auto;
  max-width: ${props => props.theme.maxWidth};
`;

const LinkList = styled.ul`
  display: flex;
  flex-grow: 1;
  justify-content: flex-end;
  align-items: center;
  list-style-type: none;
`;

const LinkItem = styled.li`
  margin-left: 15px;
  padding: 7.5px 15px;
  border-radius: 5px;

  ${({ hasBorder, theme }) =>
    hasBorder &&
    `
    border: 1.5px solid ${theme.primaryColor};
  `};

  &:hover {
    background-color: ${props => props.theme.primaryColor};
  }
`;

const StyledLink = styled(Link)`
  text-decoration: none;
`;

const StyledButton = styled.button`
  align-self: center;
  border-radius: 5px;
  padding: 7.5px 15px;
  height: 100%;
  background-color: transparent;
  border: 0;
  cursor: pointer;

  &:hover {
    background-color: ${props => props.theme.primaryColor};
    color: white;
  }
`;

export { NavStyles, LinkList, LinkItem, StyledLink, StyledButton };
