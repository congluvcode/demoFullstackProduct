package com.congdevluvcode.testapplication.sevice;

import com.congdevluvcode.testapplication.entity.Brand;
import com.congdevluvcode.testapplication.entity.Product;
import com.congdevluvcode.testapplication.payload.BrandDto;

import java.util.List;

public interface BrandService {
    void delete(Long id);
    BrandDto save(BrandDto brandDto);
    List<BrandDto> findAll();
    BrandDto findById(Long id);
}
