import styled from 'styled-components';
import { Link } from 'react-router-dom';

const NavStyles = styled.nav`
  display: flex;
  width: 100%;
`;

const LinkList = styled.ul`
  display: flex;
  flex-grow: 1;
  justify-content: flex-end;
  list-style-type: none;
`;

const LinkItem = styled.li`
  margin-left: 15px;
`;

const StyledLink = styled(Link)`
  text-decoration: none;
`;

export { NavStyles, LinkList, LinkItem, StyledLink };
