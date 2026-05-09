package com.urbanclone.urbanclonebackend.repository;

import com.urbanclone.urbanclonebackend.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByProfessionalIdAndBookingDateAndBookingTime(
            Long professionalId,
            LocalDate bookingDate,
            LocalTime bookingTime
    );

    List<Booking> findByProfessionalIdAndBookingDate(
            Long professionalId,
            LocalDate bookingDate
    );
}