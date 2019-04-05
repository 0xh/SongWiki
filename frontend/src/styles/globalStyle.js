import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
  body {
    background-color: ${props => props.theme.backgroundColor}
  }

  a, p, h1, h2, h3, h4, h5, h6 {
    color: ${props => props.theme.textColor}
  }
`;

export default GlobalStyle;
