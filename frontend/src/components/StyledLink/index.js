import React from 'react';

import { StyledLink as Link } from './styles';

const StyledLink = ({ children, ...props }) => (
  <Link {...props}>{children}</Link>
);

export default StyledLink;
