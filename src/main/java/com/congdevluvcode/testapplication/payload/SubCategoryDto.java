package com.congdevluvcode.testapplication.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubCategoryDto {
    private long id;
    private String sub_cate_code;
    private String sub_cate_name;
    private CategoryDto categoryDto;

    public SubCategoryDto(long id, String sub_cate_code, String sub_cate_name) {
        this.id = id;
        this.sub_cate_code = sub_cate_code;
        this.sub_cate_name = sub_cate_name;
    }
}
