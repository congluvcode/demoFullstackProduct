package com.congdevluvcode.testapplication.dao;

import com.congdevluvcode.testapplication.entity.Brand;
import com.congdevluvcode.testapplication.entity.Category;
import com.congdevluvcode.testapplication.entity.Product;
import com.congdevluvcode.testapplication.entity.Status;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductReponsitory extends JpaRepository<Product,Long> {
    @Query("SELECT p FROM Product p  join FETCH p.brands br join FETCH p.status sta join FETCH p.subcategory sub  WHERE sub.category.cate_name LIKE %?1% and sta.status_name LIKE %?2% and br.brand_name LIKE %?3%")
    public List<Product> search(String cateName,String staName,String brandName);

    @Query("SELECT p FROM Product p join FETCH p.brands br join FETCH p.status sta join FETCH p.subcategory sub join FETCH sub.category cate " +
            "WHERE (cate.cate_name like %:cateName%) " +
            "AND ( sta.status_name like %:statusName%) " +
            "AND (br.brand_name like %:brandName%)" +
            "AND (p.productName like %:productName%)")
    Page<Product> searchAndPaginate(
            @Param("cateName") String cateName,
            @Param("statusName") String statusName,
            @Param("brandName") String brandName,
            @Param("productName") String productName,
            Pageable pageable);

    @Query("SELECT p FROM Product p  join FETCH p.brands br join FETCH p.status sta join FETCH p.subcategory sub")
    public List<Product> search1();


}
