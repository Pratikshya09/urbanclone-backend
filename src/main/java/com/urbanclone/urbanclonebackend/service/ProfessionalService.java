package com.urbanclone.urbanclonebackend.service;

import com.urbanclone.urbanclonebackend.entity.Professional;
import com.urbanclone.urbanclonebackend.entity.ServiceEntity;
import com.urbanclone.urbanclonebackend.repository.ProfessionalRepository;
import com.urbanclone.urbanclonebackend.repository.ServiceRepository;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProfessionalService {

    private final ProfessionalRepository professionalRepository;
    private final ServiceRepository serviceRepository;

    public ProfessionalService(
            ProfessionalRepository professionalRepository,
            ServiceRepository serviceRepository
    ) {
        this.professionalRepository = professionalRepository;
        this.serviceRepository = serviceRepository;
    }

    public Professional createProfessional(Professional professional) {

        Long serviceId = professional.getService().getId();

        ServiceEntity service = serviceRepository.findById(serviceId)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        professional.setService(service);

        return professionalRepository.save(professional);
    }

    public List<Professional> getAllProfessionals() {
        return professionalRepository.findAll();
    }
}