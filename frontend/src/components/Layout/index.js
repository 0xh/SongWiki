import React from 'react';

import GlobalStyle from '../../styles/globalStyle';

import { ContentWrapper } from './styles';

import Navigation from './Navigation';
import Footer from './Footer';

const Layout = ({ children }) => (
  <>
    <GlobalStyle />
    <Navigation />
    <ContentWrapper>{children}</ContentWrapper>
    <Footer />
  </>
);

export default Layout;
