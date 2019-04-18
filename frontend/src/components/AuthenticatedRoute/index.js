import React, { useContext } from 'react';
import { Route } from 'react-router-dom';

import { AuthenticationContext } from '../../utils/authenticationContext';

import Login from '../../pages/login';

const AuthenticatedRoute = ({ component: Component, exact, path }) => {
  const { isAuthenticated } = useContext(AuthenticationContext);

  return (
    <Route
      exact={exact}
      path={path}
      render={props => (isAuthenticated ? <Component {...props} /> : <Login />)}
    />
  );
};

export default AuthenticatedRoute;
