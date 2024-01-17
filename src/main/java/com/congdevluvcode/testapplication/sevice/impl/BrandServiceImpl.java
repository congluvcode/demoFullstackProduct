package com.congdevluvcode.testapplication.sevice.impl;

import com.congdevluvcode.testapplication.dao.BrandRepository;
import com.congdevluvcode.testapplication.entity.Brand;
import com.congdevluvcode.testapplication.exception.ResourceNotFoundException;
import com.congdevluvcode.testapplication.payload.BrandDto;
import com.congdevluvcode.testapplication.sevice.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {
    private BrandRepository brandRepository;
    private ModelMapper modelMapper;

    public BrandServiceImpl(BrandRepository brandRepository, ModelMapper modelMapper) {
        this.brandRepository = brandRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void delete(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Brand","id",id));
        brandRepository.delete(brand);
    }

    @Override
    public BrandDto save(BrandDto brandDto) {
        Brand brand = brandRepository.save(mapToEntity(brandDto));
        return mapToDto(brand);
    }

    @Override
    public List<BrandDto> findAll() {
        List<Brand> brands = brandRepository.findAll();
        return brands.stream().map(brand -> mapToDto(brand)).collect(Collectors.toList());
    }

    @Override
    public BrandDto findById(Long id) {
        Brand brand = brandRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Brand","id",id));
        return mapToDto(brand);
    }

    // map to dto
    public BrandDto mapToDto(Brand brand){
        BrandDto brandDto = modelMapper.map(brand,BrandDto.class);
        return  brandDto;
    }

    public Brand mapToEntity(BrandDto brandDto){
        Brand brand = modelMapper.map(brandDto,Brand.class);
        return brand;
    }
}
