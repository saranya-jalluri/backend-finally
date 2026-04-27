package com.heritage.explorer.controller;

import com.heritage.explorer.model.Guide;
import com.heritage.explorer.repository.GuideRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/guides")
public class GuideController {
  private final GuideRepository guides;

  public GuideController(GuideRepository guides) {
    this.guides = guides;
  }

  @GetMapping
  public List<Guide> all() {
    return guides.findAll();
  }
}
