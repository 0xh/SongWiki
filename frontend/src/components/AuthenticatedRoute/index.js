import React from 'react';
import { Route } from 'react-router-dom';

import { AuthenticationConsumer } from '../../utils/authenticationContext';

import Login from '../../pages/login';

const AuthenticatedRoute = ({ component: Component, exact, path }) => (
  <AuthenticationConsumer>
    {({ isAuthenticated }) => (
      <Route
        exact={exact}
        path={path}
        render={props =>
          isAuthenticated ? <Component {...props} /> : <Login />
        }
      />
    )}
  </AuthenticationConsumer>
);

export default AuthenticatedRoute;
