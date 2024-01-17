package com.congdevluvcode.testapplication.controller;

import com.congdevluvcode.testapplication.payload.BrandDto;
import com.congdevluvcode.testapplication.payload.CategoryDto;
import com.congdevluvcode.testapplication.sevice.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDto> getAll(){
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getById(@PathVariable Long id){
        CategoryDto categoryDto = categoryService.findById(id);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }
}
