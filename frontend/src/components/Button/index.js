import React from 'react';

import { StyledButton } from './styles';

const Button = ({ children, color, hoverColor, onClick, to = '' }) => (
  <StyledButton to={to} color={color} hoverColor={hoverColor} onClick={onClick}>
    {children}
  </StyledButton>
);

export default Button;
