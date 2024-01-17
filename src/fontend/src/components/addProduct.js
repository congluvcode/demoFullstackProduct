import React, { useEffect, useState } from "react";
import { Modal, Button } from "react-bootstrap";
import Product from "./product";
import { saveProducts, updateProducts } from "../service/productService";
const AddProduct = ({
  show,
  handleClose,
  subcategories,
  brandes,
  statuses,
  children,
  product,
  handleUpdateTable,
  handleEditTable,
}) => {
  const [productEdit, setProductEdit] = useState({
    id: "",
    productName: "",
    color: "",
    quantity: "",
    sell_price: "",
    origin_price: "",
    description: "",
    status: {
      id: "",
      status_name: "",
    },
    brands: [
      {
        id: "",
        brand_name: "",
      },
    ],
    subCategory: {
      id: "",
      sub_cate_name: "",
    },
  });

  const {
    id,
    productName,
    color,
    quantity,
    sell_price,
    origin_price,
    description,
    status,
    brands,
    subCategory,
  } = productEdit;

  useEffect(() => {
    if (product) {
      setProductEdit(product);
    }
    console.log(product);
  }, [product]);

  const handleInputChange = (e) => {
    setProductEdit({
      ...productEdit,
      [e.target.name]: e.target.value,
    });
  };

  const handleStatusChange = (e) => {
    const { name, value } = e.target;

    setProductEdit((productEdit) => ({
      ...productEdit,
      status: {
        ...productEdit.status,
        [name]: value,
      },
    }));
  };

  const handleSubcateChange = (e) => {
    const { name, value } = e.target;

    setProductEdit((productEdit) => ({
      ...productEdit,
      subCategory: {
        ...productEdit.subCategory,
        [name]: value,
      },
    }));
  };

  const handleBrandChange = (e, index) => {
    const { name, value } = e.target;

    setProductEdit((productEdit) => {
      const newBrands = [...productEdit.brands];
      newBrands[index] = {
        ...newBrands[index],
        [name]: value,
      };

      return {
        ...productEdit,
        brands: newBrands,
      };
    });
  };

  const saveProduct = (e) => {
    e.preventDefault();
    // console.log(productEdit);
    const dataPromise = saveProducts(productEdit);
    dataPromise.then((data) => {
      console.log(data);
      handleUpdateTable(data);
    });
    setProductEdit({
      productName: "",
      color: "",
      quantity: "",
      sell_price: "",
      origin_price: "",
      description: "",
      status: {
        id: "",
        status_name: "",
      },
      brands: [
        {
          id: "",
          brand_name: "",
        },
      ],
      subCategory: {
        id: "",
        sub_cate_name: "",
      },
    });
    handleClose();
  };

  const editProduct = (e) => {
    e.preventDefault();
    // console.log(productEdit);
    const dataPromise = updateProducts(id, productEdit);
    dataPromise.then((data) => {
      console.log(data);
      handleEditTable(data);
    });
    handleClose();
  };
  return (
    <Modal show={show} onHide={handleClose}>
      <Modal.Header closeButton>
        <Modal.Title>{children}</Modal.Title>
      </Modal.Header>
      <Modal.Body>
        <div className="row text-end mb-2 align-items-center">
          <div className="col-4">
            <label className="col-form-label">Name</label>
          </div>
          <div className="col-8">
            <input
              className="form-control"
              // aria-describedby="passwordHelpInline"
              name="productName"
              value={productName}
              onChange={(e) => handleInputChange(e)}
            />
          </div>
        </div>
        <div className="row text-end mb-2 align-items-center">
          <div className="col-4">
            <label className="col-form-label">Color</label>
          </div>
          <div className="col-8">
            <input
              className="form-control"
              aria-describedby="passwordHelpInline"
              name="color"
              value={color}
              onChange={(e) => handleInputChange(e)}
            />
          </div>
        </div>
        <div className="row text-end mb-2 align-items-center">
          <div className="col-4">
            <label className="col-form-label">Quantity</label>
          </div>
          <div className="col-8">
            <input
              className="form-control"
              aria-describedby="passwordHelpInline"
              name="quantity"
              value={quantity}
              onChange={(e) => handleInputChange(e)}
            />
          </div>
        </div>
        <div className="row text-end mb-2 align-items-center">
          <div className="col-4">
            <label className="col-form-label">Sell price</label>
          </div>
          <div className="col-8">
            <input
              className="form-control"
              aria-describedby="passwordHelpInline"
              name="sell_price"
              value={sell_price}
              onChange={(e) => handleInputChange(e)}
            />
          </div>
        </div>
        <div className="row text-end mb-2 align-items-center">
          <div className="col-4">
            <label className="col-form-label">Origin price</label>
          </div>
          <div className="col-8">
            <input
              className="form-control"
              aria-describedby="passwordHelpInline"
              name="origin_price"
              value={origin_price}
              onChange={(e) => handleInputChange(e)}
            />
          </div>
        </div>
        <div className="row text-end mb-2 align-items-center">
          <div className="col-4">
            <label className="col-form-label">Brands Name</label>
          </div>
          <div className="col-8">
            <select
              className="form-control"
              aria-describedby="passwordHelpInline"
              name="id"
              value={brands[0].id}
              onChange={(e) => handleBrandChange(e, 0)}
            >
              <option value=""></option>
              {brandes &&
                brandes.map((bra) => (
                  <option key={bra.id} value={bra.id}>
                    {bra.brand_name}
                  </option>
                ))}
            </select>
          </div>
        </div>
        <div className="row text-end mb-2 align-items-center">
          <div className="col-4 text-end">
            <label className="col-form-label">Subcategory</label>
          </div>
          <div className="col-8">
            <select
              className="form-control"
              aria-describedby="passwordHelpInline"
              name="id"
              value={subCategory.id}
              onChange={(e) => handleSubcateChange(e)}
            >
              <option value=""></option>

              {subcategories &&
                subcategories.map((subcate) => (
                  <option key={subcate.id} value={subcate.id}>
                    {subcate.sub_cate_name}
                  </option>
                ))}
            </select>
          </div>
        </div>
        <div className="row text-end mb-2 align-items-center">
          <div className="col-4">
            <label className="col-form-label">Status</label>
          </div>
          <div className="col-8">
            <select
              className="form-control"
              aria-describedby="passwordHelpInline"
              name="id"
              value={status.id}
              onChange={(e) => handleStatusChange(e)}
            >
              <option value=""></option>
              {statuses &&
                statuses.map((sta) => (
                  <option key={sta.id} value={sta.id}>
                    {sta.status_name}
                  </option>
                ))}
            </select>
          </div>
        </div>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Close
        </Button>
        {children == "Add Product" && (
          <Button variant="primary" onClick={(e) => saveProduct(e)}>
            Save
          </Button>
        )}
        {children == "Edit Product" && (
          <Button variant="warning" onClick={(e) => editProduct(e)}>
            Update
          </Button>
        )}
      </Modal.Footer>
    </Modal>
  );
};

export default AddProduct;
