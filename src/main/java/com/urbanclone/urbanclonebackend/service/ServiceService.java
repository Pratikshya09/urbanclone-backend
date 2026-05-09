package com.urbanclone.urbanclonebackend.service;

import com.urbanclone.urbanclonebackend.entity.ServiceEntity;
import com.urbanclone.urbanclonebackend.repository.ServiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    public ServiceEntity create(ServiceEntity service) {
        return serviceRepository.save(service);
    }

    public List<ServiceEntity> getAll() {
        return serviceRepository.findAll();
    }
}