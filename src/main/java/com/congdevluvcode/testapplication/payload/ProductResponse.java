package com.congdevluvcode.testapplication.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {
    private List<ProductDto> content;
    private int pageNo;
    private int pageSize;
    private int totalPages;
    private Long totalElement;
    private boolean last;
}
