package com.urbanclone.urbanclonebackend.repository;

import com.urbanclone.urbanclonebackend.entity.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
}