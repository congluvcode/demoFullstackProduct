package com.congdevluvcode.testapplication.sevice;

import com.congdevluvcode.testapplication.payload.BrandDto;
import com.congdevluvcode.testapplication.payload.CategoryDto;

import java.util.List;

public interface CategoryService {
    void delete(Long id);
    CategoryDto save(CategoryDto categoryDto);
    List<CategoryDto> findAll();
    CategoryDto findById(Long id);
}
