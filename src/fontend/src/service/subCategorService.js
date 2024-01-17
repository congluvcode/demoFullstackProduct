import axios from "./customize-axios";

const fetchAllSubcategories = () => {
  return axios.get("/categories/1/subcategories");
};

const getSubCategoryById = (id) => {
  return axios.get(`/categories/1/subcategories/${id}`);
};

export { fetchAllSubcategories, getSubCategoryById };
