package com.heritage.explorer.dto;

import com.heritage.explorer.model.Role;

public record AuthRequest(
    String name,
    String email,
    String password,
    Role role,
    String phone,
    String city,
    String languages,
    String experience,
    String expertise,
    String specialization) {
}
