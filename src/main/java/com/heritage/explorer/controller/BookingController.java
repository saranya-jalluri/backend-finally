package com.heritage.explorer.controller;

import com.heritage.explorer.dto.BookingStatusRequest;
import com.heritage.explorer.model.Booking;
import com.heritage.explorer.repository.BookingRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
  private final BookingRepository bookings;

  public BookingController(BookingRepository bookings) {
    this.bookings = bookings;
  }

  @GetMapping
  public List<Booking> all(
      @RequestParam(required = false) String userEmail,
      @RequestParam(required = false) Long guideId,
      @RequestParam(required = false) String guideName) {
    if (userEmail != null) {
      return bookings.findByUserEmail(userEmail);
    }
    if (guideId != null) {
      return bookings.findByGuideId(guideId);
    }
    if (guideName != null) {
      return bookings.findByGuideName(guideName);
    }
    return bookings.findAll();
  }

  @PostMapping
  public Booking create(@RequestBody Booking booking) {
    return bookings.save(booking);
  }

  @PutMapping("/{id}")
  public Booking updateStatus(@PathVariable Long id, @RequestBody BookingStatusRequest request) {
    return bookings.findById(id).map(booking -> {
      if (request.status() != null) {
        booking.setStatus(request.status());
      }
      booking.setReply(request.reply());
      return bookings.save(booking);
    }).orElseThrow();
  }
}
