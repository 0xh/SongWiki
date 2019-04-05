import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter as Router, Route } from 'react-router-dom';
import { ThemeProvider } from 'styled-components';

import theme from './styles/theme';

import Home from './pages/home';
import * as serviceWorker from './serviceWorker';

const AppRouter = () => (
  <ThemeProvider theme={theme}>
    <Router>
      <Route exact path="/" component={Home} />
    </Router>
  </ThemeProvider>
);

ReactDOM.render(<AppRouter />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
