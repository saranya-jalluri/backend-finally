package com.heritage.explorer.controller;

import com.heritage.explorer.dto.AuthRequest;
import com.heritage.explorer.dto.UserResponse;
import com.heritage.explorer.service.AuthService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/register")
  public UserResponse register(@RequestBody AuthRequest request) {
    return authService.register(request);
  }

  @PostMapping("/login")
  public UserResponse login(@RequestBody AuthRequest request) {
    return authService.login(request);
  }
}
