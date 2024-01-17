package com.congdevluvcode.testapplication.sevice;

import com.congdevluvcode.testapplication.payload.CategoryDto;
import com.congdevluvcode.testapplication.payload.SubCategoryDto;

import java.util.List;

public interface SubCategoryService {
    void delete(Long id);
    SubCategoryDto save(SubCategoryDto subCategoryDto);
    List<SubCategoryDto> findAll();
    List<SubCategoryDto> findByCateId(Long cateId);
    SubCategoryDto findById(Long id);

}
