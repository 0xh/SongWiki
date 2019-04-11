import styled from 'styled-components';

const SiteWrapper = styled.div`
  display: flex;
  min-height: 100vh;
  flex-direction: column;
`;

const ContentWrapper = styled.main`
  flex: 1;
  width: 100%;
  justify-self: space-between;
  margin: 0 auto;
  max-width: ${props => props.theme.maxWidth};
`;

export { SiteWrapper, ContentWrapper };
