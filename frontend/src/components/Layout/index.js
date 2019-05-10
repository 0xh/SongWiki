import React, { useContext } from 'react';
import Snackbar from '@material-ui/core/Snackbar';

import { NotificationContext } from '../../utils/notificationContext';

import GlobalStyle from '../../styles/globalStyle';

import { SiteWrapper, ContentWrapper } from './styles';

import Navigation from './Navigation';
import Footer from './Footer';

const Layout = ({ children }) => {
  const { notificationMessage, setNotificationMessage } = useContext(
    NotificationContext
  );

  return (
    <SiteWrapper>
      <GlobalStyle />
      <Navigation />
      <ContentWrapper>{children}</ContentWrapper>
      <Footer />
      <Snackbar
        message={notificationMessage}
        autoHideDuration={3400}
        open={notificationMessage !== ''}
        onClose={() => setNotificationMessage('')}
        anchorOrigin={{ vertical: 'bottom', horizontal: 'left' }}
      />
    </SiteWrapper>
  );
};

export default Layout;
