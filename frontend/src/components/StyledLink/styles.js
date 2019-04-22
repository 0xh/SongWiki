import styled from 'styled-components';
import { Link } from 'react-router-dom';

export const StyledLink = styled(Link)`
  text-decoration: none;
  padding: 0.5rem 1rem;
  border: 0 solid #dae1e7;
  border-radius: 0.25rem;
  font-size: 1rem;
  cursor: pointer;

  ${({ forNavigation, theme }) =>
    !forNavigation
      ? `
        background-color: ${theme.primaryColor};
        color: white;
      `
      : `
        margin-left: 15px;
      `};

  ${({ hasBorder, theme }) =>
    hasBorder &&
    `
    border: 1.5px solid ${theme.primaryColor};
  `};

  &:hover {
    ${({ forNavigation, theme }) =>
      forNavigation
        ? `
          background-color: ${theme.primaryColor};
          color: white;
        `
        : `background-color: ${theme.primaryColorDark};`}
  }
`;
