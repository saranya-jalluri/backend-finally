package com.heritage.explorer.dto;

import com.heritage.explorer.model.Role;
import com.heritage.explorer.model.UserAccount;

public record UserResponse(
    Long id,
    String name,
    String email,
    Role role,
    String status,
    String phone,
    String city,
    String languages,
    String experience,
    String expertise,
    String specialization) {
  public static UserResponse from(UserAccount user) {
    return new UserResponse(
        user.getId(),
        user.getName(),
        user.getEmail(),
        user.getRole(),
        user.getStatus(),
        user.getPhone(),
        user.getCity(),
        user.getLanguages(),
        user.getExperience(),
        user.getExpertise(),
        user.getSpecialization());
  }
}
