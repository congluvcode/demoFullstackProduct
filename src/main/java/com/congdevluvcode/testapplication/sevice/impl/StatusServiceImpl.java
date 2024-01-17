package com.congdevluvcode.testapplication.sevice.impl;

import com.congdevluvcode.testapplication.dao.StatusRepository;
import com.congdevluvcode.testapplication.entity.Status;
import com.congdevluvcode.testapplication.exception.ResourceNotFoundException;
import com.congdevluvcode.testapplication.payload.StatusDto;
import com.congdevluvcode.testapplication.sevice.StatusService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatusServiceImpl implements StatusService {
    private StatusRepository statusRepository;
    private ModelMapper modelMapper;

    public StatusServiceImpl(StatusRepository statusRepository, ModelMapper modelMapper) {
        this.statusRepository = statusRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void delete(Long id) {
        Status status = statusRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stats","id",id));
        statusRepository.delete(status);
    }

    @Override
    public StatusDto save(StatusDto statusDto) {
        Status status =statusRepository.save(mapToEntity(statusDto));
        return mapToDto(status);
    }

    @Override
    public List<StatusDto> findAll() {
        List<Status> statusList = statusRepository.findAll();
        return statusList.stream().map(status -> mapToDto(status)).collect(Collectors.toList());
    }

    @Override
    public StatusDto findById(Long id) {
        Status status = statusRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Stats","id",id));
        return mapToDto(status);
    }

    //dto
    public StatusDto mapToDto(Status status){
        StatusDto statusDto = modelMapper.map(status,StatusDto.class);
        return statusDto;
    }

    public Status mapToEntity(StatusDto statusDto){
        return modelMapper.map(statusDto,Status.class);
    }
}
