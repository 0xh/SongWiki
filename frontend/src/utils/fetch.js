import { getJWTToken } from './localStorage';

const extendedFetch = async (
  url,
  body = null,
  method = 'GET',
  authenticated = false
) => {
  const optionObject = {
    method,
    headers: authenticated
      ? {
          'Content-Type': 'application/json',
          Authorization: `Bearer ${getJWTToken()}`,
        }
      : { 'Content-Type': 'application/json' },
  };

  if (body != null) optionObject.body = JSON.stringify(body);

  return fetch(url, optionObject);
};

export default extendedFetch;
