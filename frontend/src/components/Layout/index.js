import React from 'react';

import Navigation from './Navigation';
import Footer from './Footer';

const Layout = ({ children }) => (
  <>
    <Navigation />
    <main>{children}</main>
    <Footer />
  </>
);

export default Layout;
