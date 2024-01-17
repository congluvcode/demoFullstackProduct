package com.congdevluvcode.testapplication.sevice;

import com.congdevluvcode.testapplication.payload.CategoryDto;
import com.congdevluvcode.testapplication.payload.StatusDto;

import java.util.List;

public interface StatusService {
    void delete(Long id);
    StatusDto save(StatusDto statusDto);
    List<StatusDto> findAll();
    StatusDto findById(Long id);
}
