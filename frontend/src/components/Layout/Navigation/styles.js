import styled from 'styled-components';
import { Link } from 'react-router-dom';

const NavStyles = styled.nav`
  display: flex;
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

export { NavStyles, LinkList, LinkItem, StyledLink };