import { addJWTToken } from './localStorage';

const fetchJWT = (username, password) => {
  fetch('/api/auth', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify({ username, password }),
  })
    .then(response => response.headers.get('Authorization'))
    .then(header => header.replace('Bearer ', ''))
    .then(jwt => addJWTToken(jwt));
};

export { fetchJWT };
