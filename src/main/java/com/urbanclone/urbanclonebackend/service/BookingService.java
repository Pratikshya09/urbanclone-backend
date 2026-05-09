package com.urbanclone.urbanclonebackend.service;

import com.urbanclone.urbanclonebackend.entity.Booking;
import com.urbanclone.urbanclonebackend.entity.Professional;
import com.urbanclone.urbanclonebackend.entity.User;
import com.urbanclone.urbanclonebackend.enums.BookingStatus;
import com.urbanclone.urbanclonebackend.repository.BookingRepository;
import com.urbanclone.urbanclonebackend.repository.ProfessionalRepository;
import com.urbanclone.urbanclonebackend.repository.UserRepository;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.time.LocalTime;
import java.util.ArrayList;

@Component
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ProfessionalRepository professionalRepository;

    public BookingService(
            BookingRepository bookingRepository,
            UserRepository userRepository,
            ProfessionalRepository professionalRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.professionalRepository = professionalRepository;
    }

    public Booking createBooking(Booking booking) {

        Long userId = booking.getUser().getId();
        Long professionalId = booking.getProfessional().getId();

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Professional professional = professionalRepository.findById(professionalId)
                .orElseThrow(() -> new RuntimeException("Professional not found"));

        boolean alreadyBooked =
                bookingRepository.existsByProfessionalIdAndBookingDateAndBookingTime(
                        professionalId,
                        booking.getBookingDate(),
                        booking.getBookingTime()
                );

        if (alreadyBooked) {
            throw new RuntimeException("Professional already booked for this slot");
        }

        booking.setUser(user);
        booking.setProfessional(professional);

        booking.setStatus(BookingStatus.PENDING);
        return bookingRepository.save(booking);
    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public List<LocalTime> getAvailableSlots(Long professionalId, LocalDate date) {

        List<LocalTime> allSlots = new ArrayList<>();

        allSlots.add(LocalTime.of(9, 0));
        allSlots.add(LocalTime.of(10, 0));
        allSlots.add(LocalTime.of(11, 0));
        allSlots.add(LocalTime.of(12, 0));
        allSlots.add(LocalTime.of(13, 0));
        allSlots.add(LocalTime.of(14, 0));
        allSlots.add(LocalTime.of(15, 0));
        allSlots.add(LocalTime.of(16, 0));

        List<Booking> bookings =
                bookingRepository.findByProfessionalIdAndBookingDate(
                        professionalId,
                        date
                );

        List<LocalTime> bookedSlots = bookings.stream()
                .map(Booking::getBookingTime)
                .toList();

        allSlots.removeAll(bookedSlots);

        return allSlots;
    }

    public Booking updateStatus(Long id, String status) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Booking not found"));

        booking.setStatus(BookingStatus.valueOf(status));

        return bookingRepository.save(booking);
    }
}