import React from 'react';

import {
  NavStyles,
  LinkList,
  LinkItem,
  StyledLink,
  StyledButton,
} from './styles';

import { removeJWTToken } from '../../../utils/localStorage';

import { AuthenticationConsumer } from '../../../utils/authenticationContext';

const logOut = setAuthenticated => {
  removeJWTToken();
  setAuthenticated(false);
};

const Navigation = () => (
  <AuthenticationConsumer>
    {({ isAuthenticated, setAuthenticated }) => (
      <NavStyles>
        <h1>
          <StyledLink to="/">SongWiki</StyledLink>
        </h1>
        {isAuthenticated ? (
          <StyledButton onClick={() => logOut(setAuthenticated)}>
            Logout
          </StyledButton>
        ) : (
          <LinkList>
            <LinkItem>
              <StyledLink to="/login">Login</StyledLink>
            </LinkItem>
            <LinkItem hasBorder>
              <StyledLink to="/register">Register</StyledLink>
            </LinkItem>
          </LinkList>
        )}
      </NavStyles>
    )}
  </AuthenticationConsumer>
);

export default Navigation;
