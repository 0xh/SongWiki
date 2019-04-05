import React from 'react';

import { NavStyles, LinkList, LinkItem, StyledLink } from './styles';

const Navigation = () => (
  <NavStyles>
    <h1>
      <StyledLink to="/">SongWiki</StyledLink>
    </h1>
    <LinkList>
      <LinkItem>
        <StyledLink to="/login">Login</StyledLink>
      </LinkItem>
      <LinkItem>
        <StyledLink to="/register">Register</StyledLink>
      </LinkItem>
    </LinkList>
  </NavStyles>
);

export default Navigation;
