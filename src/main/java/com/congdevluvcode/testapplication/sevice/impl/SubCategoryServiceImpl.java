package com.congdevluvcode.testapplication.sevice.impl;

import com.congdevluvcode.testapplication.dao.CategoryRepository;
import com.congdevluvcode.testapplication.dao.SubCategoryRepository;
import com.congdevluvcode.testapplication.entity.Category;
import com.congdevluvcode.testapplication.entity.Subcategory;
import com.congdevluvcode.testapplication.exception.ResourceNotFoundException;
import com.congdevluvcode.testapplication.payload.SubCategoryDto;
import com.congdevluvcode.testapplication.sevice.SubCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    private SubCategoryRepository subCategoryRepository;
    private CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    public SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository, CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.subCategoryRepository = subCategoryRepository;
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void delete(Long id) {
        Subcategory subcategory = subCategoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("SubCategory","id",id));
        subCategoryRepository.delete(subcategory);
    }

    @Override
    public SubCategoryDto save(SubCategoryDto subCategoryDto) {
        Subcategory subcategory = subCategoryRepository.save(mapToEntity(subCategoryDto));
        return mapToDto(subcategory);
    }

    @Override
    public List<SubCategoryDto> findAll() {
        List<Subcategory> subcategories = subCategoryRepository.findAll();
        return subcategories.stream().map(subcategory -> mapToDto(subcategory)).collect(Collectors.toList());
    }

    @Override
    public List<SubCategoryDto> findByCateId(Long cateId) {
        Category category = categoryRepository.findById(cateId).orElseThrow(()-> new ResourceNotFoundException("Category","id",cateId));
        List<Subcategory> subcategories = subCategoryRepository.findByCategory_Id(cateId);
        return subcategories.stream().map(subcategory -> mapToDto(subcategory)).collect(Collectors.toList());
    }

    @Override
    public SubCategoryDto findById(Long id) {
        Subcategory subcategory = subCategoryRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("SubCategory","id",id));
        return mapToDto(subcategory);
    }

    //dto
    public SubCategoryDto mapToDto(Subcategory subcategory){
        return modelMapper.map(subcategory,SubCategoryDto.class);
    }

    public Subcategory mapToEntity(SubCategoryDto subCategoryDto){
        return modelMapper.map(subCategoryDto,Subcategory.class);
    }
}
