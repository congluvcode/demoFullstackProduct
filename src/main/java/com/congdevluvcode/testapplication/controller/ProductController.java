package com.congdevluvcode.testapplication.controller;


import com.congdevluvcode.testapplication.payload.ProductDto;
import com.congdevluvcode.testapplication.payload.ProductResponse;
import com.congdevluvcode.testapplication.sevice.BrandService;
import com.congdevluvcode.testapplication.sevice.ProductService;
import com.congdevluvcode.testapplication.sevice.StatusService;
import com.congdevluvcode.testapplication.sevice.SubCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/products")
public class ProductController {
    private ProductService productService;
    private StatusService statusService;
    private SubCategoryService subCategoryService;
    private BrandService brandService;

    public ProductController(ProductService productService, StatusService statusService, SubCategoryService subCategoryService, BrandService brandService) {
        this.productService = productService;
        this.statusService = statusService;
        this.subCategoryService = subCategoryService;
        this.brandService = brandService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable long id){
        ProductDto productDto = productService.findProductById(id);
        return ResponseEntity.ok(productDto);
    }

    @GetMapping("/search")
    public List<ProductDto> searchProducts(
            @RequestParam(value = "brandId",defaultValue = "0",required = false) Long brandId,
            @RequestParam(value = "statusId",defaultValue = "0",required = false) Long statusId,
            @RequestParam(value = "categoryId",defaultValue = "0",required = false) Long cateId
    ){
        return productService.searchProducts(statusId,cateId,brandId);
    }

    @GetMapping
    public ProductResponse getAllProducts(
            @RequestParam(value = "pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(value = "pageSize",defaultValue = "3",required = false) int pageSize,
            @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
            @RequestParam(value = "sortDir",defaultValue = "asc",required = false) String sortDir,
            @RequestParam(value = "brandId",defaultValue = "0",required = false) Long brandId,
            @RequestParam(value = "statusId",defaultValue = "0",required = false) Long statusId,
            @RequestParam(value = "categoryId",defaultValue = "0",required = false) Long cateId,
            @RequestParam(value = "productName",defaultValue = "",required = false) String productName
    ){
        return productService.searchAndPaginateProducts(productName,statusId,cateId,brandId,pageNo,pageSize,sortBy,sortDir);
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        ProductDto productDto1 = productService.saveProduct(productDto);
        return new ResponseEntity<>(productDto1, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,@RequestBody ProductDto productDto){
        ProductDto res = productService.updateProduct(id,productDto);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product deleted successfully",HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable Long id){
        ProductDto res = productService.findProductById(id);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
