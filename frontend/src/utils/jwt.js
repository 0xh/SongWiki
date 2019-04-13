import { addJWTToken } from './localStorage';

import fetch from './fetch';

const fetchJWT = (username, password) => {
  fetch('/api/auth', { username, password }, 'POST')
    .then(response => response.headers.get('Authorization'))
    .then(header => header.replace('Bearer ', ''))
    .then(jwt => addJWTToken(jwt));
};

export { fetchJWT };
