package com.urbanclone.urbanclonebackend.controller;

import com.urbanclone.urbanclonebackend.entity.Professional;
import com.urbanclone.urbanclonebackend.service.ProfessionalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professionals")
public class ProfessionalController {

    @Autowired
    private ProfessionalService professionalService;

    @PostMapping
    public Professional createProfessional(@RequestBody Professional professional) {
        return professionalService.createProfessional(professional);
    }

    @GetMapping
    public List<Professional> getAll() {
        return professionalService.getAllProfessionals();
    }

}