package com.urbanclone.urbanclonebackend.controller;

import com.urbanclone.urbanclonebackend.entity.ServiceEntity;
import com.urbanclone.urbanclonebackend.service.ServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/services")
public class ServiceController {

    @Autowired
    private ServiceService serviceService;

    @PostMapping
    public ServiceEntity create(@RequestBody ServiceEntity service) {
        return serviceService.create(service);
    }

    @GetMapping
    public List<ServiceEntity> getAll() {
        return serviceService.getAll();
    }
}