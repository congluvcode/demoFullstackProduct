package com.congdevluvcode.testapplication.sevice.impl;

import com.congdevluvcode.testapplication.dao.CategoryRepository;
import com.congdevluvcode.testapplication.entity.Category;
import com.congdevluvcode.testapplication.exception.ResourceNotFoundException;
import com.congdevluvcode.testapplication.payload.CategoryDto;
import com.congdevluvcode.testapplication.sevice.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void delete(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
        categoryRepository.delete(category);
    }

    @Override
    public CategoryDto save(CategoryDto categoryDto) {
        Category category = categoryRepository.save(mapToEntity(categoryDto));
        return mapToDto(category);
    }

    @Override
    public List<CategoryDto> findAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> mapToDto(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto findById(Long id) {
        Category category = categoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Category","id",id));
        return mapToDto(category);
    }

    //map to dto
    public CategoryDto mapToDto(Category category){
        CategoryDto categoryDto = modelMapper.map(category,CategoryDto.class);
        return categoryDto;
    }

    public Category mapToEntity(CategoryDto categoryDto){
        Category category = modelMapper.map(categoryDto,Category.class);
        return category;
    }
}
