package com.congdevluvcode.testapplication.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "brand",uniqueConstraints = {@UniqueConstraint(columnNames = "brand_name")})
public class Brand {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ìd")
    private long id;

    @Column(name = "brand_name")
    private String brand_name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "product_brand",
                joinColumns = @JoinColumn(name = "brand_id"),
                inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    public Brand(String brand_name) {
        this.brand_name = brand_name;
    }

    @Override
    public String toString() {
        return brand_name;
    }
}
