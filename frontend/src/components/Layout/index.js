import React from 'react';

import GlobalStyle from '../../styles/globalStyle';

import { SiteWrapper, ContentWrapper } from './styles';

import Navigation from './Navigation';
import Footer from './Footer';

const Layout = ({ children }) => (
  <SiteWrapper>
    <GlobalStyle />
    <Navigation />
    <ContentWrapper>{children}</ContentWrapper>
    <Footer />
  </SiteWrapper>
);

export default Layout;
