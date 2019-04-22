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

const LogoLink = styled(Link)`
  text-decoration: none;
`;

const Wrapper = styled.div`
  display: flex;
  align-items: center;
`;

export { NavStyles, LinkList, LogoLink, Wrapper };
