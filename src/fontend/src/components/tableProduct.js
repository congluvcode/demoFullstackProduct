import React, { useEffect } from "react";
import { Table, Row, Form, Col, InputGroup } from "react-bootstrap";
import { useState } from "react";
import { fetchAllProducts } from "../service/productService";
import ReactPaginate from "react-paginate";
import Product from "./product";
import AddProduct from "./addProduct";
import { fetchAllStatuses } from "../service/statusService";
import { fetchAllBrands } from "../service/brandService";
import { fetchAllSubcategories } from "../service/subCategorService";
import { fetchAllCategorys } from "../service/categoryService";

const TableProduct = () => {
  const [listProduct, setListProduct] = useState([]);
  const [totalPage, setTotalPage] = useState();
  const [listStatus, setListAStatus] = useState([]);
  const [listSubcategory, setListSubcategory] = useState([]);
  const [listCategory, setListCategory] = useState([]);
  const [listBrand, setListBrand] = useState([]);
  const [isShowModalAddNew, setIsShowModalAddNew] = useState(false);
  const [isShowModalEdit, setIsShowModalEdit] = useState(false);
  const [isShowModalDetail, setIsShowModalDetail] = useState(false);
  const [dataUserEdit, setDataUserEdit] = useState();
  const [statusId, setStatusId] = useState(0);
  const [cateId, setCateId] = useState(0);
  const [brandId, setBrandId] = useState(0);
  const [name, setName] = useState("");

  useEffect(() => {
    getListProduct(0);
    getListStatus();
    getListBrand();
    getListCate();
    getListSubcate();
  }, []);

  const getListProduct = async (page) => {
    let resproduct = await fetchAllProducts(
      page,
      statusId,
      cateId,
      brandId,
      name
    );
    console.log(cateId);
    console.log(statusId);
    console.log(name);
    console.log(cateId);
    if (resproduct && resproduct.content) {
      // console.log(resproduct.content);
      setListProduct(resproduct.content);
      setTotalPage(resproduct.totalPages);
    }
    console.log(statusId);
    //
  };

  const searchProduct = () => {};

  const getListStatus = async () => {
    let res = await fetchAllStatuses();
    if (res) {
      // console.log(res);
      setListAStatus(res);
    }
  };

  const getListBrand = async () => {
    let res = await fetchAllBrands();
    if (res) {
      // console.log(res);
      setListBrand(res);
    }
  };

  const getListSubcate = async () => {
    let res = await fetchAllSubcategories();
    if (res) {
      // console.log(res);
      setListSubcategory(res);
    }
  };

  const getListCate = async () => {
    let res = await fetchAllCategorys();
    if (res) {
      // console.log(res);
      setListCategory(res);
    }
  };

  const handlePageClick = (e) => {
    getListProduct(+e.selected);
    // getListProduct(pageNo);
  };

  const handleUpdateTable = (user) => {
    setListProduct([...listProduct, user]);
  };

  const handleEditTable = (user) => {
    setListProduct(
      listProduct.map((product) => (product.id === user.id ? user : product))
    );
  };

  return (
    <div className="mt-5">
      <Row className="mb-3 py-4">
        <Form.Group as={Col} md="2">
          <Form.Label>First name</Form.Label>
          <Form.Control
            required
            type="text"
            placeholder="First name"
            value={name}
            onChange={(e) => setName(e.target.value)}
          />
        </Form.Group>
        <Form.Group as={Col} md="2">
          <Form.Label>Price</Form.Label>
          <Form.Control required type="text" />
        </Form.Group>
        <Form.Group as={Col} md="2">
          <Form.Label>Brand</Form.Label>
          <Form.Select
            required
            value={brandId}
            onChange={(e) => setBrandId(e.target.value)}
          >
            <option value="0"></option>
            {listBrand &&
              listBrand.map((brand) => (
                <option key={brand.id} value={brand.id}>
                  {brand.brand_name}
                </option>
              ))}
          </Form.Select>
        </Form.Group>
        <Form.Group as={Col} md="2">
          <Form.Label>Category</Form.Label>
          <Form.Select
            required
            value={cateId}
            onChange={(e) => setCateId(e.target.value)}
          >
            <option value="0"></option>
            {listCategory &&
              listCategory.map((category) => (
                <option key={category.id} value={category.id}>
                  {category.cate_name}
                </option>
              ))}
          </Form.Select>
        </Form.Group>
        <Form.Group as={Col} md="2">
          <Form.Label>Status</Form.Label>
          <Form.Select
            value={statusId}
            onChange={(e) => setStatusId(e.target.value)}
          >
            <option value="0"></option>
            {listStatus &&
              listStatus.map((status) => (
                <option key={status.id} value={status.id}>
                  {status.status_name}
                </option>
              ))}
          </Form.Select>
        </Form.Group>
        <Form.Group as={Col} md="2" className="d-flex align-items-end">
          <div
            className="btn-success btn rounded-pill d-inline-flex align-items-center justify-content-center py-2 px-2"
            onClick={() => getListProduct(0)}
          >
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="20"
              height="20"
              fill="currentColor"
              className="bi bi-search align-bottom"
              viewBox="0 0 16 16"
            >
              <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0" />
            </svg>
          </div>
        </Form.Group>
      </Row>
      <div className="d-flex align-items-center justify-content-between">
        <span>List Product:</span>
        <button
          className="btn btn-success"
          onClick={() => setIsShowModalAddNew(true)}
        >
          Add Product
        </button>
      </div>
      <div className="min-height-200">
        <Table className="mt-2" striped bordered hover variant="dark">
          <thead>
            <tr>
              <th>No</th>
              <th>Product Name</th>
              <th>Brand Name</th>
              <th>Subcategory</th>
              <th>Price</th>
              <th>Status</th>
              <th>Function</th>
            </tr>
          </thead>
          <tbody>
            {listProduct &&
              listProduct.length > 0 &&
              listProduct.map((product) => (
                <Product
                  key={product.id}
                  productRender={product}
                  setIsShowModalEdit={() => setIsShowModalEdit(true)}
                  setIsShowModalDetail={() => setIsShowModalDetail(true)}
                  setDataUserEdit={setDataUserEdit}
                />
              ))}
          </tbody>
        </Table>
      </div>

      <ReactPaginate
        breakLabel="..."
        nextLabel="next >"
        onPageChange={handlePageClick}
        pageRangeDisplayed={5}
        pageCount={totalPage}
        previousLabel="< previous"
        renderOnZeroPageCount={null}
        pageClassName="page-item"
        pageLinkClassName="page-link"
        previousClassName="page-item"
        previousLinkClassName="page-link"
        nextClassName="page-item"
        nextLinkClassName="page-link"
        breakClassName="page-item"
        breakLinkClassName="page-link"
        containerClassName="pagination"
        activeClassName="active"
      />

      <AddProduct
        show={isShowModalAddNew}
        handleClose={() => setIsShowModalAddNew(false)}
        statuses={listStatus}
        brandes={listBrand}
        subcategories={listSubcategory}
        handleUpdateTable={handleUpdateTable}
      >
        Add Product
      </AddProduct>

      <AddProduct
        show={isShowModalEdit}
        handleClose={() => setIsShowModalEdit(false)}
        id={dataUserEdit}
        statuses={listStatus}
        brandes={listBrand}
        subcategories={listSubcategory}
        product={dataUserEdit}
        handleEditTable={handleEditTable}
      >
        Edit Product
      </AddProduct>

      <AddProduct
        show={isShowModalDetail}
        handleClose={() => setIsShowModalDetail(false)}
        product={dataUserEdit}
        statuses={listStatus}
        brandes={listBrand}
        subcategories={listSubcategory}
      >
        Detail Product
      </AddProduct>
    </div>
  );
};

export default TableProduct;
