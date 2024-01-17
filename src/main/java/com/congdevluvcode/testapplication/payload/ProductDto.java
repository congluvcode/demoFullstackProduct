package com.congdevluvcode.testapplication.payload;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private long id;
    private String productName;
    private String color;
    private long quantity;
    private double sell_price;
    private double origin_price;
    private String description;
    private StatusDto status;
    private List<BrandDto> brands;
    private SubCategoryDto subCategory;

    public ProductDto(String product_name, String color, long quantity, double sell_price, double origin_price, String description) {
        this.productName = product_name;
        this.color = color;
        this.quantity = quantity;
        this.sell_price = sell_price;
        this.origin_price = origin_price;
        this.description = description;
    }
}
