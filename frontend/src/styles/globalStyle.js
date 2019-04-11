import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
  body {
    background-color: ${props => props.theme.backgroundColor}
  }

  a, p, label, h1, h2, h3, h4, h5, h6 {
    color: ${props => props.theme.textColor};
  }

  h1, h2, h3, h4, h5, h6 {
    font-family: 'Open Sans', Arial, sans-serif;
  }

  a, p, label {
    font-family: 'Lato', Arial, sans-serif;
  }
`;

export default GlobalStyle;
