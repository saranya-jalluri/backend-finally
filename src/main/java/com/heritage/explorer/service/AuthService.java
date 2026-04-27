package com.heritage.explorer.service;

import com.heritage.explorer.dto.AuthRequest;
import com.heritage.explorer.dto.UserResponse;
import com.heritage.explorer.model.Guide;
import com.heritage.explorer.model.Role;
import com.heritage.explorer.model.UserAccount;
import com.heritage.explorer.repository.GuideRepository;
import com.heritage.explorer.repository.UserAccountRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class AuthService {
  private final UserAccountRepository users;
  private final GuideRepository guides;
  private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

  public AuthService(UserAccountRepository users, GuideRepository guides) {
    this.users = users;
    this.guides = guides;
  }

  public UserResponse register(AuthRequest request) {
    users.findByEmail(request.email()).ifPresent(existing -> {
      throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already registered");
    });

    UserAccount user = new UserAccount();
    user.setName(request.name());
    user.setEmail(request.email());
    user.setPasswordHash(encoder.encode(request.password()));
    user.setRole(request.role() == null ? Role.USER : request.role());
    user.setPhone(request.phone());
    user.setCity(request.city());
    user.setLanguages(request.languages());
    user.setExperience(request.experience());
    user.setExpertise(request.expertise());
    user.setSpecialization(request.specialization());
    UserAccount saved = users.save(user);

    if (saved.getRole() == Role.GUIDE) {
      Guide guide = new Guide();
      guide.setName(saved.getName());
      guide.setCity(saved.getCity() == null || saved.getCity().isBlank() ? "India" : saved.getCity());
      guide.setLanguages(saved.getLanguages() == null || saved.getLanguages().isBlank() ? "Hindi, English" : saved.getLanguages());
      guide.setExperience(saved.getExperience() == null || saved.getExperience().isBlank() ? "1 year" : saved.getExperience());
      guide.setExpertise(saved.getExpertise() == null || saved.getExpertise().isBlank() ? "Indian heritage" : saved.getExpertise());
      guide.setRating(4.5);
      guides.save(guide);
    }

    return UserResponse.from(saved);
  }

  public UserResponse login(AuthRequest request) {
    UserAccount user = users.findByEmail(request.email())
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials"));
    if (!encoder.matches(request.password(), user.getPasswordHash())) {
      throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid credentials");
    }
    return UserResponse.from(user);
  }
}
