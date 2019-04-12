import styled from 'styled-components';

const Label = styled.label`
  display: block;
  font-size: 0.875rem;
  margin-bottom: 0.5rem;
  font-weight: bold;
`;

const Field = styled.input`
  border: 0 solid #dae1e7;
  border-width: 1px;
  appearance: none;
  padding: 0.5rem 0.75rem;
  margin-bottom: 1rem;
  box-shadow: 0 2px 4px 0 rgba(0, 0, 0, 0.1);
  border-radius: 0.25rem;
`;

const Button = styled.button`
  display: block;
  padding: 0.5rem 1rem;
  border: 0 solid #dae1e7;
  background-color: ${props => props.theme.primaryColor};
  color: white;
  border-radius: 0.25rem;
  font-size: 1rem;
  cursor: pointer;

  &:hover {
    background-color: ${props => props.theme.primaryColorDark};
  }
`;

export { Label, Field, Button };
