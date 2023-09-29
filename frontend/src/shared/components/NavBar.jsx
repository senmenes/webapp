import { Link } from "react-router-dom";

function NavBar() {
  return (
    <nav className="navbar navbar-expand bg-light shadow-sm">
      <div className="container-fluid">
        <Link className="navbar-brand" to="/">
          <img
            src="https://upload.wikimedia.org/wikipedia/commons/thumb/archive/d/db/20230704114027%21Threads_%28app%29.png/120px-Threads_%28app%29.png"
            width={60}
          ></img>
          WebApp
        </Link>
        <ul className="navbar-nav">
          <li className="nav-item active">
            <Link className="nav-link" to="/">
              Home
            </Link>
          </li>
          <li className="nav-item">
            <Link className="nav-link" to="/signup">
              Sign Up
            </Link>
          </li>
        </ul>
      </div>
    </nav>
  );
}

export default NavBar;
