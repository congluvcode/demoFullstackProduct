package com.congdevluvcode.testapplication.dao;

import com.congdevluvcode.testapplication.entity.Brand;
import com.congdevluvcode.testapplication.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<Brand,Long> {
    List<Brand> findByProducts(Product product);
}
