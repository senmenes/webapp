import axios from "axios";

export function getUsers(para) {
  return axios.get("/api/v1/users", { params: para });
}
