import axios from "./customize-axios";

const fetchAllStatuses = () => {
  return axios.get("/statuses");
};

const getStatusById = (id) => {
  return axios.get(`/statuses/${id}`);
};

export { fetchAllStatuses, getStatusById };
