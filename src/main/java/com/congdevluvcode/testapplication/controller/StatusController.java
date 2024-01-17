package com.congdevluvcode.testapplication.controller;

import com.congdevluvcode.testapplication.payload.BrandDto;
import com.congdevluvcode.testapplication.payload.CategoryDto;
import com.congdevluvcode.testapplication.payload.StatusDto;
import com.congdevluvcode.testapplication.sevice.StatusService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/statuses")
public class StatusController {
    private StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @GetMapping
    public List<StatusDto> getAll(){
        return statusService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StatusDto> getById(@PathVariable Long id){
        StatusDto statusDto = statusService.findById(id);
        return new ResponseEntity<>(statusDto, HttpStatus.OK);
    }

}
