package com.congdevluvcode.testapplication.sevice.impl;

import com.congdevluvcode.testapplication.dao.*;
import com.congdevluvcode.testapplication.entity.*;
import com.congdevluvcode.testapplication.exception.ResourceNotFoundException;
import com.congdevluvcode.testapplication.payload.BrandDto;
import com.congdevluvcode.testapplication.payload.ProductDto;
import com.congdevluvcode.testapplication.payload.ProductResponse;
import com.congdevluvcode.testapplication.payload.SubCategoryDto;
import com.congdevluvcode.testapplication.sevice.ProductService;
import com.congdevluvcode.testapplication.sevice.SubCategoryService;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductReponsitory productReponsitory;
    private ModelMapper modelMapper;

    private SubCategoryRepository subCategoryRepository;
    private StatusRepository statusRepository;
    private BrandRepository brandRepository;
    private CategoryRepository categoryRepository;

//    private void configureModelMapper() {
//        // Tạo một Converter để chuyển đổi từ categoryId sang SubCategoryDto
//        Converter<Long, SubCategoryDto> categoryToSubCategoryConverter = context -> {
//            Long subCategoryId = context.getSource();
//            // Gọi repository hoặc service để lấy SubCategoryDto từ categoryId
//            SubCategoryDto subCategoryDto = subCategoryService.findById(subCategoryId);// Lấy SubCategoryDto từ categoryId
//            return subCategoryDto;
//        };
//
//        // Ánh xạ ProductDto sang Product với sử dụng Converter để ánh xạ categoryId thành SubCategoryDto
//        TypeMap<ProductDto, Product> typeMap = modelMapper.createTypeMap(ProductDto.class, Product.class);
//        typeMap.addMappings(mapper -> mapper.using(categoryToSubCategoryConverter)
//                .map(ProductDto::getSubCategoryId, Product::setSubcategory));
//    }


    public ProductServiceImpl(ProductReponsitory productReponsitory, ModelMapper modelMapper, SubCategoryRepository subCategoryRepository, StatusRepository statusRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.productReponsitory = productReponsitory;
        this.modelMapper = modelMapper;
        this.subCategoryRepository = subCategoryRepository;
        this.statusRepository = statusRepository;
        this.brandRepository = brandRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void deleteProduct(long id) {
        Product product = productReponsitory.findById(id).orElseThrow(()->new ResourceNotFoundException("Product","id",id));
        productReponsitory.delete(product);
    }

    @Override
    public ProductDto saveProduct(ProductDto productDTO) {
        Product product = mapToEntity(productDTO);
        Product resproduct = productReponsitory.save(product);
        return mapToDto(resproduct);
    }

    @Override
    public ProductDto updateProduct(Long id, ProductDto productDto) {
        Product newproduct = mapToEntity(productDto);
        System.out.println(newproduct.getProductName());
        newproduct.setId(id);
        return mapToDto(productReponsitory.save(newproduct));
    }

    @Override
    public ProductResponse findAllProducts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        //get page
        Pageable pageable = PageRequest.of(pageNo,pageSize,sort);
        Page<Product> products = productReponsitory.findAll(pageable);

        //get content
        List<Product> listOfProduct = products.getContent();
        List<ProductDto> content = listOfProduct.stream().map((product)-> mapToDto(product)).collect(Collectors.toList());

        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(content);
        productResponse.setLast(products.isLast());
        productResponse.setPageNo(products.getNumber());
        productResponse.setPageSize(products.getSize());
        productResponse.setTotalPages(products.getTotalPages());
        productResponse.setTotalElement(products.getTotalElements());
        return productResponse;
    }

    @Override
    public ProductResponse searchAndPaginateProducts(String productName,Long statusId, Long cateId, Long brandiId, int pageNo, int pageSize, String sortBy, String sortDir) {
        // Xác định các thông tin tìm kiếm
        String statusName = (statusId != 0) ? statusRepository.findById(statusId).orElseThrow(() -> new ResourceNotFoundException("Status","id",statusId)).getStatus_name() : "";
        String cateName = (cateId != 0) ? categoryRepository.findById(cateId).orElseThrow(() -> new ResourceNotFoundException("Cate","id",cateId)).getCate_name() : "";
        String brandName = (brandiId != 0) ? brandRepository.findById(brandiId).orElseThrow(() -> new ResourceNotFoundException("brand","id",brandiId)).getBrand_name() : "";

        // Xây dựng đối tượng Sort
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // Tạo đối tượng Pageable để phân trang
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        // Tìm kiếm và phân trang
        Page<Product> productsPage = productReponsitory.searchAndPaginate(cateName, statusName, brandName,productName, pageable);

        // Lấy danh sách sản phẩm từ trang kết quả
        List<Product> listOfProduct = productsPage.getContent();

        // Chuyển đổi danh sách sản phẩm sang DTO
        List<ProductDto> content = listOfProduct.stream().map((product) -> mapToDto(product)).collect(Collectors.toList());

        // Tạo đối tượng ProductResponse và cài đặt các thuộc tính
        ProductResponse productResponse = new ProductResponse();
        productResponse.setContent(content);
        productResponse.setLast(productsPage.isLast());
        productResponse.setPageNo(productsPage.getNumber());
        productResponse.setPageSize(productsPage.getSize());
        productResponse.setTotalPages(productsPage.getTotalPages());
        productResponse.setTotalElement(productsPage.getTotalElements());

        return productResponse;
    }

    @Override
    public List<ProductDto> searchProducts(Long statusId,Long cateId,Long brandiId) {
        String statusName="";
        String cateName = "";
        String brandName = "";
        if(statusId!=0){
            statusName = statusRepository.findById(statusId).orElseThrow(() -> new ResourceNotFoundException("","",statusId)).getStatus_name();
        }
        if(brandiId!=0){
            brandName = brandRepository.findById(brandiId).orElseThrow(() -> new ResourceNotFoundException("","",statusId)).getBrand_name();
        }
        if(cateId!=0){
            cateName = categoryRepository.findById(cateId).orElseThrow(() -> new ResourceNotFoundException("","",statusId)).getCate_name();
        }
        List<Product> products = productReponsitory.search(cateName,statusName,brandName);
        return products.stream().map(product -> mapToDto(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDto findProductById(long id)
    {
        Product product = productReponsitory.findById(id).orElseThrow(()->new ResourceNotFoundException("Product","id",id));
        return mapToDto(product);
    }

    @Override
    public List<ProductDto> findProductByName(String name) {
        return null;
    }

    //map to dto
    public ProductDto mapToDto(Product product){
        ProductDto productDto = modelMapper.map(product,ProductDto.class);
//        ProductDto productDto= new ProductDto();
//        productDto.setId(product.getId());
//        productDto.setSubCategoryId(product.getSubcategory().getId());
//        productDto.setProductName(product.getProductName());
//        productDto.setStatusId(product.getStatus().getId());
//        productDto.setColor(product.getColor());
//        productDto.setDescription(product.getDescription());
//        productDto.setOrigin_price(product.getOrigin_price());
//        productDto.setSell_price(product.getSell_price());
//        productDto.setQuantity(product.getQuantity());
//        productDto.setBrandIds(product.getBrands().stream().map(brand -> brand.getId()).collect(Collectors.toList()));
        return  productDto;

    }

    public Product mapToEntity(ProductDto productDTO){
        Product product = modelMapper.map(productDTO,Product.class);
        Subcategory subcategory = subCategoryRepository.findById(productDTO.getSubCategory().getId()).orElseThrow(()->new ResourceNotFoundException("Subcategory","id",productDTO.getSubCategory().getId()));
        Status status = statusRepository.findById(productDTO.getStatus().getId()).orElseThrow(()->new ResourceNotFoundException("Status","id",productDTO.getStatus().getId()));
        List<Brand> brands = new ArrayList<>();

        for(BrandDto brandDto : productDTO.getBrands()){
            Brand brand = brandRepository.findById(brandDto.getId()).orElseThrow(()->new ResourceNotFoundException("Brand","id",brandDto.getId()));
            brands.add(brand);
//            product.addBrand(brand);
        }
        product.setBrands(brands);
        product.setSubcategory(subcategory);
        product.setStatus(status);
        return  product;
    }
}
