package com.heritage.explorer.repository;

import com.heritage.explorer.model.Booking;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
  List<Booking> findByUserEmail(String userEmail);
  List<Booking> findByGuideId(Long guideId);
  List<Booking> findByGuideName(String guideName);
}
