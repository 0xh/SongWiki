import { createGlobalStyle } from 'styled-components';

const GlobalStyle = createGlobalStyle`
  body {
    background-color: ${props => props.theme.backgroundColor}
  }

  a, p, label, h1, h2, h3, h4, h5, h6 {
    color: ${props => props.theme.textColor};
  }

  h1, h2, h3, h4, h5, h6, button, input {
    font-family: 'Open Sans', Arial, sans-serif !important;
  }

  a, p, label {
    font-family: 'Lato', Arial, sans-serif;
  }

  ul {
    font-family: 'Lato', Arial, sans-serif;
    list-style-type: none;
    padding: 0;
  }
`;

export default GlobalStyle;
