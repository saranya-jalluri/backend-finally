package com.heritage.explorer.repository;

import com.heritage.explorer.model.Tour;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<Tour, Long> {
}
