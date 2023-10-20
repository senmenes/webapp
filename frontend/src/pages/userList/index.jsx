import { useParams } from "react-router-dom";
import { useEffect, useState } from "react";
import axios from "axios";
import { getUsers } from "./userRequest";

function UserList() {
  const [page, setPage] = useState(0);
  const [userList, setUserList] = useState([]);
  const [size, setSize] = useState(3);
  const [totalPage, setTotalPage] = useState(1);
  const [menuItems, setMenuItems] = useState([]);
  const [apiProgress, setApiProgress] = useState(false);

  let c = page * size;

  useEffect(() => {
    //setUserList([]);
    setApiProgress(true);
    getUsers({ page, size })
      .then((response) => {
        setUserList(response.data.userList);
        setTotalPage(response.data.totalPages);
        setCount(page * size);
      })
      .catch((axiosError) => {})
      .finally(() => {
        setApiProgress(false);
      });
  }, [, size, page]);

  const handleChange = (selected) => {
    setSize(selected.target.value);
    console.log(size);
  };

  useEffect(() => {
    let menuItemsTemp = [];
    for (var i = 1; i <= totalPage; i++) {
      menuItemsTemp.push(i);
    }
    setMenuItems(menuItemsTemp);
  }, [, totalPage, size, page]);

  return (
    <>
      <div>
        {" "}
        Users Per Page
        <select
          className="input-padding-y-sm"
          aria-label="Default select example"
          value={size}
          onChange={handleChange}
        >
          <option value="1">1</option>
          <option value="2">2</option>
          <option value="3">3</option>
          <option value="4">4</option>
          <option value="5">5</option>
        </select>
      </div>
      <table className="table">
        <thead>
          <tr>
            <th scope="col">#</th>
            <th scope="col">ID</th>
            <th scope="col">Username</th>
            <th scope="col">Email</th>
          </tr>
        </thead>
        {!apiProgress && (
          <tbody>
            {userList.map((user, i) => (
              <tr key={i}>
                <th scope="row">{++c}</th>
                <td>{user.id}</td>
                <td>{user.username}</td>
                <td>{user.email}</td>
              </tr>
            ))}
          </tbody>
        )}
      </table>
      <nav aria-label="...">
        <ul className="pagination">
          <li className={page - 1 >= 0 ? "page-item" : "page-item disabled"}>
            <a
              className="page-link"
              onClick={() => setPage(page - 1)}
              tabIndex="-1"
              aria-disabled="true"
            >
              Previous
            </a>
          </li>
          {menuItems.map((pageNum) => (
            <li
              className={pageNum - 1 == page ? "page-item active" : "page-item"}
            >
              <a className="page-link" onClick={() => setPage(pageNum - 1)}>
                {pageNum}
              </a>
            </li>
          ))}
          <li
            className={
              page + 1 < totalPage ? "page-item" : "page-item disabled"
            }
          >
            <a className="page-link" onClick={() => setPage(page + 1)}>
              Next
            </a>
          </li>
        </ul>
      </nav>
    </>
  );
}

export default UserList;
