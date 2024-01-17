package com.congdevluvcode.testapplication.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class ErrorDetail {
    private Date timestamp;
    private String messages;
    private String details;
}
