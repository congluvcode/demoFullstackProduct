import axios from "./customize-axios";

const fetchAllBrands = () => {
  return axios.get("/brands");
};

const getBrandById = (id) => {
  return axios.get(`/brands/${id}`);
};

export { fetchAllBrands, getBrandById };
