const getJWTToken = () => localStorage.getItem('jwt');

const addJWTToken = token => localStorage.setItem('jwt', token);

const removeJWTToken = () => localStorage.removeItem('jwt');

export { getJWTToken, addJWTToken, removeJWTToken };
