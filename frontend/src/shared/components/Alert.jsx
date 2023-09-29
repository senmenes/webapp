function Alert(props) {
  const { children, styleType } = props;

  return <div className={"alert alert-" + styleType}>{children}</div>;
}

export default Alert;
