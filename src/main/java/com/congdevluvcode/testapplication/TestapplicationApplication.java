package com.congdevluvcode.testapplication;

import com.congdevluvcode.testapplication.dao.BrandRepository;
import com.congdevluvcode.testapplication.dao.CategoryRepository;
import com.congdevluvcode.testapplication.dao.StatusRepository;
import com.congdevluvcode.testapplication.dao.SubCategoryRepository;
import com.congdevluvcode.testapplication.entity.*;
import com.congdevluvcode.testapplication.payload.*;
import com.congdevluvcode.testapplication.sevice.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class TestapplicationApplication {
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(TestapplicationApplication.class, args);
	}
	@Bean
	public CommandLineRunner commandLineRunner(ProductService productService,
											   BrandService brandService,
											   CategoryService categoryService,
											   SubCategoryService subCategoryService,
											   StatusService statusService){
		return runner->{
//			addBrands(brandRepository);
//			addStatus(statusRepository);
			addProduc(productService,brandService,categoryService,subCategoryService,statusService);
		};
	}

	private void addStatus(StatusRepository statusRepository) {
		Status status1 = new Status("Hết hàng");
		Status status2 = new Status("Ngừng kinh doanh");
		statusRepository.save(status1);
		statusRepository.save(status2);
	}

	private void addBrands(BrandRepository brandRepository) {
		Brand brand1 = new Brand("MOI");
		Brand brand2 = new Brand("FPT");
		Brand brand3 = new Brand("RMIT");
		brandRepository.save(brand1);
		brandRepository.save(brand2);
		brandRepository.save(brand3);
	}

	private void addProduc(ProductService productService,
						   BrandService brandService,
						   CategoryService categoryService,
						   SubCategoryService subCategoryService,
						   StatusService statusService) {
		//create new product
		ProductDto product1 = new ProductDto("công","trắng",12,20000,10000,"Chất lượng tốt");
		//create new category and subcategory
		CategoryDto categoryDto = new CategoryDto(0,"XXX","Điện tử");
		SubCategoryDto subCategory1 = new SubCategoryDto(0,"xxx1","Laptop");
		SubCategoryDto subCategory2 = new SubCategoryDto(0,"xxx2","Tulanh");
		SubCategoryDto subCategory3 = new SubCategoryDto(0,"xxx3","DienThoai");
//		subCategory1.addProduct(product1);
		//create new status
		StatusDto status1 = new StatusDto(0L,"Còn hàng");
		StatusDto status2 = new StatusDto(0L,"Hết hàng");
		//creat new brand
		BrandDto brand1 = new BrandDto(0,"3CE");
		BrandDto brand2 = new BrandDto(0,"MOI");

		StatusDto resstatus = statusService.save(status1);
		BrandDto resbrands = brandService.save(brand1);
		List<BrandDto> brandDtos = new ArrayList<>();
		brandDtos.add(resbrands);
		CategoryDto rescategory = categoryService.save(categoryDto);
		subCategory1.setCategoryDto(rescategory);
		SubCategoryDto subCategoryDto = subCategoryService.save(subCategory1);

		product1.setStatus(resstatus);
		product1.setBrands(brandDtos);
		product1.setSubCategory(subCategoryDto);
		System.out.println(product1);
		productService.saveProduct(product1);

		ProductDto product2 = new ProductDto("nhàn","Xanh",18,40000,20000,"Chất lượng tốt");
		product2.setStatus(resstatus);
		product2.setBrands(brandDtos);
		product2.setSubCategory(subCategoryDto);
		productService.saveProduct(product2);

		//
		ProductDto product3 = new ProductDto("trang","hồng",5,60000,30000,"Chất lượng tốt");
		subCategory2.setCategoryDto(rescategory);
		SubCategoryDto ressub2 = subCategoryService.save(subCategory2);
		StatusDto ressta2 = statusService.save(status2);
		BrandDto restban2 = brandService.save(brand2);
		product3.setStatus(ressta2);
		List<BrandDto> brandDtos1 = new ArrayList<>();

		brandDtos1.add(restban2);
		product3.setBrands(brandDtos1);
		product3.setSubCategory(ressub2);
		productService.saveProduct(product3);




//		Status status2 = new Status("Hết hàng");
//		Brand brand2 = new Brand("FPT");
//
//		statusRepository.save(status2);
//		brandRepository.save(brand2);
//		subCategoryRepository.save(subCategory2);
//		product2.setSubcategory(subCategory2);
//		product2.setStatus(status2);
//		product2.addBrand(brand2);
//		productService.saveProduct(product2);


//		Status status3 = new Status("Ngừng kinh doanh");
//		Brand brand3 = new Brand("RMIT");
//		category.addSubCategory(subCategory3);
//		product3.setSubCategory(subCategory3);
//		product3.addBrand(brand3);
//		product3.setStatus(status3);
//		productService.saveProduct(product3);
	}
}
