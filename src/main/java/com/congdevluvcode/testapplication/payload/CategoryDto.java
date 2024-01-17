package com.congdevluvcode.testapplication.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private long id;
    private String cate_code;
    private String cate_name;
}
