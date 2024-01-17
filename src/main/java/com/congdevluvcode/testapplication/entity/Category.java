package com.congdevluvcode.testapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "cate_code")
    private String cate_code;

    @Column(name = "cate_name")
    private String cate_name;

    @OneToMany(mappedBy = "category",fetch = FetchType.LAZY,cascade = CascadeType.REMOVE)
    private List<Subcategory> subCategories;

    public Category(String cate_code, String cate_name) {
        this.cate_code = cate_code;
        this.cate_name = cate_name;
    }

    public void addSubCategory(Subcategory subCategory){
        if(subCategories==null){
            subCategories= new ArrayList<>();
        }
        subCategories.add(subCategory);
        subCategory.setCategory(this);
    }
}
