package com.congdevluvcode.testapplication.controller;

import com.congdevluvcode.testapplication.entity.Brand;
import com.congdevluvcode.testapplication.payload.BrandDto;
import com.congdevluvcode.testapplication.payload.ProductResponse;
import com.congdevluvcode.testapplication.sevice.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/brands")
public class BrandController {
    private BrandService brandService;

    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping
    public List<BrandDto> getAll(){
        return brandService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BrandDto> getById(@PathVariable(name = "id") long id){
        BrandDto brandDto =  brandService.findById(id);
        return new ResponseEntity<>(brandDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BrandDto> createBrand(@RequestBody BrandDto brandDto){
        BrandDto brandDto1 = brandService.save(brandDto);
        return new ResponseEntity<>(brandDto1,HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable(name = "id") long id){
        brandService.delete(id);
        return new ResponseEntity<>("Brand entity deleted successfully", HttpStatus.OK);
    }
}
