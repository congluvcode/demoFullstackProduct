package com.congdevluvcode.testapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "color")
    private String color;

    @Column(name = "quantity")
    private long quantity;

    @Column(name = "sell_price")
    private double sell_price;

    @Column(name = "origin_price")
    private double origin_price;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "status_id")
    private Status status;

    @ManyToMany()
    @JoinTable(name = "product_brand",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "brand_id"))
    private List<Brand> brands;

    @ManyToOne()
    @JoinColumn(name = "subcate_id")
    private Subcategory subcategory;

    public Product(String product_name, String color, long quantity, double sell_price, double origin_price, String description) {
        this.productName = product_name;
        this.color = color;
        this.quantity = quantity;
        this.sell_price = sell_price;
        this.origin_price = origin_price;
        this.description = description;
    }

    public void addBrand(Brand termBrand){
        if(brands==null){
            brands = new ArrayList<>();
        }
        brands.add(termBrand);
    }
}
