import axios from "./customize-axios";

const fetchAllProducts = (page, statusId, categoryId, brandId, productName) => {
  let res = axios.get(
    `/products?pageNo=${page}&&statusId=${statusId}&&categoryId=${categoryId}&&brandId=${brandId}&&productName=${productName}`
  );
  return res;
};

const saveProducts = (product) => {
  let res = axios.post("/products", product);
  return res;
};

const updateProducts = (id, product) => {
  let res = axios.put(`/products/${id}`, product);
  return res;
};

export { fetchAllProducts, saveProducts, updateProducts };
