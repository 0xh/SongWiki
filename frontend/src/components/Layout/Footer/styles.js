import styled from 'styled-components';

const FooterStyles = styled.footer`
  display: flex;
  width: 100%;
  justify-content: center;
  margin: 0 auto;
  max-width: ${props => props.theme.maxWidth};
`;

export { FooterStyles };
