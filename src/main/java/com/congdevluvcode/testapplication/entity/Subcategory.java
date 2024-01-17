package com.congdevluvcode.testapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sub_category")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Subcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "sub_cate_code")
    private String sub_cate_code;

    @Column(name = "sub_cate_name")
    private String sub_cate_name;

    @ManyToOne()
    @JoinColumn(name = "cate_id")
    private Category category;

    @OneToMany(mappedBy = "subcategory",cascade = CascadeType.REMOVE)
    private List<Product> products;

    public Subcategory(String sub_cate_code, String sub_cate_name) {
        this.sub_cate_code = sub_cate_code;
        this.sub_cate_name = sub_cate_name;
    }

    @Override
    public String toString() {
        return sub_cate_name;
    }

    public void addProduct(Product product){
        if(products==null){
            products= new ArrayList<>();
        }
        products.add(product);
        product.setSubcategory(this);
    }
}
