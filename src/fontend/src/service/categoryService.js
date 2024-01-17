import axios from "./customize-axios";

const fetchAllCategorys = () => {
  return axios.get("/categories");
};

const getCategoryById = (id) => {
  return axios.get(`/categories/${id}`);
};

export { fetchAllCategorys, getCategoryById };
