import React from 'react';

import { StyledLink } from './styles';

const LinkButton = props => {
  const { children } = props;
  return <StyledLink {...props}>{children}</StyledLink>;
};

export default LinkButton;
