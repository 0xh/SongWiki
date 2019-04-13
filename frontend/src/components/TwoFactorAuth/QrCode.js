import React, { useState, useEffect } from 'react';

import fetch from '../../utils/fetch';

const QrCode = ({ name }) => {
  const [qrUrl, setQrUrl] = useState('');

  useEffect(() => {
    fetch(`/api/2fa/${name}`)
      .then(response => response.text())
      .then(qrURL => setQrUrl(qrURL));
  }, [name]);

  return <img src={qrUrl} alt="Two Factor Authentication qr code" />;
};

export default QrCode;
