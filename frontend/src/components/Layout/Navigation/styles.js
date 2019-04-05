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
`;

const HomeLink = styled(Link)`
  text-decoration: none;
`;

const StyledLink = styled(Link)`
  text-decoration: none;
  padding: 7.5px 15px;

  &:hover {
    background-color: ${props => props.theme.primaryColor};
  }
`;

export { NavStyles, LinkList, LinkItem, HomeLink, StyledLink };
