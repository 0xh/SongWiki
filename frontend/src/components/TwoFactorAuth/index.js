import React, { useState, useRef, useEffect, useContext } from 'react';

import { AuthenticationContext } from '../../utils/authenticationContext';
import { fetchJWT } from '../../utils/jwt';

import VerificationCode from './VerificationCode';
import QrCode from './QrCode';

const TwoFactorAuth = ({ history, user, initialSetup }) => {
  const firstRender = useRef(true);
  const [validVerificationCode, setValidVerificationCode] = useState(null);
  const { setAuthenticated } = useContext(AuthenticationContext);

  useEffect(() => {
    if (firstRender.current || validVerificationCode == null) {
      firstRender.current = false;
      return;
    }

    const { username, password } = user;

    if (!validVerificationCode) {
      alert('Verification failed, try again');
      setValidVerificationCode(null);
    } else {
      setAuthenticated(true);
      fetchJWT(username, password);
      history.push('/');
    }
  }, [history, setAuthenticated, user, validVerificationCode]);

  return (
    <>
      <h2>{initialSetup && 'Setup'} Two Factor Authentication</h2>
      {initialSetup ? (
        <p>
          You can set up two factor authentication for {user.username} now by
          scanning this QR code with your favourite authenticator app.
        </p>
      ) : (
        <p>Use the authenticator code provided by your app to authenticate</p>
      )}

      {initialSetup && <QrCode name={user.username} />}
      <VerificationCode
        name={user.username}
        updateVerificationStatus={setValidVerificationCode}
      />
    </>
  );
};

export default TwoFactorAuth;
