import React, { useState } from 'react';

import fetch from '../../../utils/fetch';

import { Label, Field, Button } from './styles';

const VerificationCode = ({ name, updateVerificationStatus }) => {
  const [verificationCode, setVerificationCode] = useState(0);

  const onVerificationCodeChange = newVerificationCode => {
    const intVerificationCode = parseInt(newVerificationCode);
    setVerificationCode(intVerificationCode);
  };

  const check6DigitCode = () => {
    fetch(`/api/2fa`, { username: name, verificationCode }, 'POST')
      .then(response => response.text())
      .then(isValid =>
        isValid === 'true'
          ? updateVerificationStatus(true)
          : updateVerificationStatus(false)
      );
  };

  return (
    <>
      <Label htmlFor="code">6-digit code</Label>
      <Field
        onChange={e => onVerificationCodeChange(e.target.value)}
        type="text"
        id="code"
      />

      <Button type="button" onClick={() => check6DigitCode()}>
        Confirm code
      </Button>
    </>
  );
};

export default VerificationCode;
