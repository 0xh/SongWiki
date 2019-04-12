import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import { ThemeProvider } from 'styled-components';
import WebFont from 'webfontloader';

import theme from './styles/theme';

import Home from './pages/home';
import Register from './pages/register';
import TwoFactorAuth from './pages/TwoFactorAuth';
import * as serviceWorker from './serviceWorker';

import 'normalize.css';

WebFont.load({
  google: {
    families: ['Open Sans', 'Lato'],
  },
});

const AppRouter = () => (
  <ThemeProvider theme={theme}>
    <Router>
      <Route exact path="/" component={Home} />
      <Route exact path="/register" component={Register} />
      <Route exact path="/two-factor-auth" component={TwoFactorAuth} />
    </Router>
  </ThemeProvider>
);

ReactDOM.render(<AppRouter />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
