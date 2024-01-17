package com.congdevluvcode.testapplication.sevice;

import com.congdevluvcode.testapplication.entity.Brand;
import com.congdevluvcode.testapplication.entity.Category;
import com.congdevluvcode.testapplication.entity.Product;
import com.congdevluvcode.testapplication.entity.Status;
import com.congdevluvcode.testapplication.payload.ProductDto;
import com.congdevluvcode.testapplication.payload.ProductResponse;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ProductService {
    void deleteProduct(long id);
    ProductDto saveProduct(ProductDto product);
    ProductDto updateProduct(Long id,ProductDto product);
    ProductResponse findAllProducts(int pageNo, int pageSize, String sortBy, String sortDir);
    public ProductResponse searchAndPaginateProducts(
            String productName,Long statusId, Long cateId, Long brandiId,
            int pageNo, int pageSize, String sortBy, String sortDir);
    List<ProductDto> searchProducts(Long statusId,Long cateId,Long brandiId);
    ProductDto findProductById(long id);
    List<ProductDto> findProductByName(String name);
}
