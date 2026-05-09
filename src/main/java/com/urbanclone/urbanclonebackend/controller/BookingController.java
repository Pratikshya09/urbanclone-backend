package com.urbanclone.urbanclonebackend.controller;

import com.urbanclone.urbanclonebackend.entity.Booking;
import com.urbanclone.urbanclonebackend.service.BookingService;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    private final BookingService bookingService;

    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        return bookingService.createBooking(booking);
    }

    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    @GetMapping("/available-slots")
    public List<LocalTime> getAvailableSlots(
            @RequestParam Long professionalId,
            @RequestParam LocalDate date
    ) {
        return bookingService.getAvailableSlots(professionalId, date);
    }

    @PutMapping("/{id}/status")
    public Booking updateStatus(
            @PathVariable Long id,
            @RequestParam String status
    ) {
        return bookingService.updateStatus(id, status);
    }
}