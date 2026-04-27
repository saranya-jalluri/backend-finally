package com.heritage.explorer.controller;

import com.heritage.explorer.dto.UserResponse;
import com.heritage.explorer.model.Role;
import com.heritage.explorer.repository.UserAccountRepository;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/users")
public class UserController {
  private final UserAccountRepository users;

  public UserController(UserAccountRepository users) {
    this.users = users;
  }

  @GetMapping
  public List<UserResponse> all() {
    return users.findAll().stream().map(UserResponse::from).toList();
  }

  @PatchMapping("/{id}/toggle-status")
  public UserResponse toggleStatus(@PathVariable Long id) {
    return users.findById(id).map(user -> {
      if (user.getRole() == Role.ADMIN) {
        throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Admin accounts cannot be suspended");
      }
      user.setStatus("Active".equals(user.getStatus()) ? "Suspended" : "Active");
      return UserResponse.from(users.save(user));
    }).orElseThrow();
  }
}
