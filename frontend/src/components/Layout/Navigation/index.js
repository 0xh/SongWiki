import React from 'react';
import { Link } from 'react-router-dom';

const Navigation = () => (
  <nav>
    <h1>
      <Link to="/">SongWiki</Link>
    </h1>
    <ul>
      <li>
        <Link to="/login">Login</Link>
      </li>
      <li>
        <Link to="/register">Register</Link>
      </li>
    </ul>
  </nav>
);

export default Navigation;
