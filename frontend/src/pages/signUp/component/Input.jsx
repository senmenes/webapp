export function Input(props) {
  const { id, label, errorMessage, onChange, pass } = props;

  return (
    <div className="mb-2">
      <label htmlFor={id} className="form-label">
        {label}
      </label>
      <input
        id={id}
        className={errorMessage ? "form-control is-invalid" : "form-control"}
        onChange={onChange}
        type={pass}
      />
      {errorMessage && <div className="invalid-feedback">{errorMessage}</div>}
    </div>
  );
}
