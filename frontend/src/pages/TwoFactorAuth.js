import React, { useState, useRef, useEffect } from 'react';

import Layout from '../components/Layout';
import QrCode from '../components/TwoFactorAuth/QrCode';
import VerificationCode from '../components/TwoFactorAuth/VerificationCode';

const TwoFactorAuth = ({
  history,
  location: {
    state: { name },
  },
}) => {
  const firstRender = useRef(true);
  const [validVerificationCode, setValidVerificationCode] = useState(null);

  useEffect(() => {
    if (firstRender.current || validVerificationCode == null) {
      firstRender.current = false;
      return;
    }

    if (validVerificationCode) history.push('/');
    else {
      alert('Verification failed, try again');
      setValidVerificationCode(null);
    }
  }, [history, validVerificationCode]);

  return (
    <Layout>
      <h1>Setup Two Factor Authentication</h1>
      <p>
        You can set up two factor authentication for {name} now by scanning this
        QR code with your favourite authenticator app.
      </p>

      <QrCode name={name} />
      <VerificationCode
        name={name}
        updateVerificationStatus={setValidVerificationCode}
      />
    </Layout>
  );
};

export default TwoFactorAuth;
