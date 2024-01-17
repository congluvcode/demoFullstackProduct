package com.congdevluvcode.testapplication.controller;

import com.congdevluvcode.testapplication.entity.Category;
import com.congdevluvcode.testapplication.payload.CategoryDto;
import com.congdevluvcode.testapplication.payload.SubCategoryDto;
import com.congdevluvcode.testapplication.sevice.CategoryService;
import com.congdevluvcode.testapplication.sevice.SubCategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/categories")
public class SubCategoryController {
    private SubCategoryService subCategoryService;

    public SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("/{cateId}/subcategories")
    public List<SubCategoryDto> getByCate(@PathVariable(name = "cateId")long id){
        return subCategoryService.findByCateId(id);
    }

    @GetMapping("/{cateId}/subcategories/{id}")
    public ResponseEntity<SubCategoryDto> getById(@PathVariable(name = "cateId") Long cateid,
                                               @PathVariable(name = "id")Long id){
        SubCategoryDto subCategoryDto = subCategoryService.findById(id);
        return new ResponseEntity<>(subCategoryDto, HttpStatus.OK);
    }
}
