import React, { useContext } from 'react';

import { NavStyles, LinkList, LogoLink, Wrapper } from './styles';

import StyledLink from '../../StyledLink';

import { removeJWTToken } from '../../../utils/localStorage';
import { AuthenticationContext } from '../../../utils/authenticationContext';

const logOut = setAuthenticated => {
  removeJWTToken();
  setAuthenticated(false);
};

const Navigation = () => {
  const { isAuthenticated, setAuthenticated } = useContext(
    AuthenticationContext
  );
  return (
    <NavStyles>
      <h1>
        <LogoLink to="/">SongWiki</LogoLink>
      </h1>
      {isAuthenticated ? (
        <Wrapper>
          <StyledLink forNavigation to="/playlists">
            Playlists
          </StyledLink>
          <StyledLink forNavigation onClick={() => logOut(setAuthenticated)}>
            Logout
          </StyledLink>
        </Wrapper>
      ) : (
        <LinkList>
          <li>
            <StyledLink forNavigation to="/login">
              Login
            </StyledLink>
          </li>
          <li>
            <StyledLink forNavigation hasBorder to="/register">
              Register
            </StyledLink>
          </li>
        </LinkList>
      )}
    </NavStyles>
  );
};

export default Navigation;
