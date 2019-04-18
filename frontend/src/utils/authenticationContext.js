import React from 'react';

export const AuthenticationContext = React.createContext();
export const AuthenticationProvider = ({
  children,
  isAuthenticated,
  setAuthenticated,
}) => (
  <AuthenticationContext.Provider value={{ isAuthenticated, setAuthenticated }}>
    {children}
  </AuthenticationContext.Provider>
);
