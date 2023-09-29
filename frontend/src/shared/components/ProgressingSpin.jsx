function ProgressingSpin(props) {
  const { sm } = props;
  return (
    <span
      className={sm ? "spinner-border spinner-border-sm" : "spinner-border"}
      area-aria-hidden="true"
    ></span>
  );
}

export default ProgressingSpin;
