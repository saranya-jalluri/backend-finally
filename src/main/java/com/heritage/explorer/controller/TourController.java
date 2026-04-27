package com.heritage.explorer.controller;

import com.heritage.explorer.model.Tour;
import com.heritage.explorer.repository.TourRepository;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tours")
public class TourController {
  private final TourRepository tours;

  public TourController(TourRepository tours) {
    this.tours = tours;
  }

  @GetMapping
  public List<Tour> all() {
    return tours.findAll();
  }

  @PostMapping
  public Tour create(@RequestBody Tour tour) {
    return tours.save(tour);
  }

  @PutMapping("/{id}")
  public Tour update(@PathVariable Long id, @RequestBody Tour tour) {
    tour.setId(id);
    return tours.save(tour);
  }

  @DeleteMapping("/{id}")
  public void delete(@PathVariable Long id) {
    tours.deleteById(id);
  }
}
