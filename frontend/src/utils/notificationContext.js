import React from 'react';

export const NotificationContext = React.createContext();
export const NotificationProvider = ({
  children,
  notificationMessage,
  setNotificationMessage,
}) => (
  <NotificationContext.Provider
    value={{ notificationMessage, setNotificationMessage }}
  >
    {children}
  </NotificationContext.Provider>
);
