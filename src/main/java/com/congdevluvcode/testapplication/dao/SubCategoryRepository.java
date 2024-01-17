package com.congdevluvcode.testapplication.dao;

import com.congdevluvcode.testapplication.entity.Category;
import com.congdevluvcode.testapplication.entity.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubCategoryRepository extends JpaRepository<Subcategory,Long> {
    public List<Subcategory> findByCategory_Id(Long cateId);
}
