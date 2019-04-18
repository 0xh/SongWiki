import React, { useContext } from 'react';
import { Notification } from 'react-notification';

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
      <Notification
        isActive={notificationMessage !== ''}
        message={notificationMessage}
        dismissAfter={3400}
        onDismiss={() => setNotificationMessage('')}
      />
    </SiteWrapper>
  );
};

export default Layout;
