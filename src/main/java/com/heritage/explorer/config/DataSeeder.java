package com.heritage.explorer.config;

import com.heritage.explorer.model.Guide;
import com.heritage.explorer.model.Role;
import com.heritage.explorer.model.Tour;
import com.heritage.explorer.model.UserAccount;
import com.heritage.explorer.repository.GuideRepository;
import com.heritage.explorer.repository.TourRepository;
import com.heritage.explorer.repository.UserAccountRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataSeeder {
  @Bean
  CommandLineRunner seed(UserAccountRepository users, TourRepository tours, GuideRepository guides) {
    return args -> {
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

      if (users.count() == 0) {
        saveUser(users, encoder, "Saranya", "admin@heritage.in", "admin123", Role.ADMIN);
        saveUser(users, encoder, "Paranthi", "user@heritage.in", "user123", Role.USER);
        saveUser(users, encoder, "Sweety", "creator@heritage.in", "creator123", Role.CREATOR);
        saveUser(users, encoder, "Sarayu", "guide@heritage.in", "guide123", Role.GUIDE);
      }

      if (tours.count() == 0) {
        tours.save(tour("Taj Mahal", "Agra, Uttar Pradesh", "Mughal Architecture",
            "A marble mausoleum commissioned by Shah Jahan, renowned for pietra dura work, riverfront gardens, and its enduring story of love and craftsmanship.",
            "https://images.unsplash.com/photo-1564507592333-c60657eea523?auto=format&fit=crop&w=1200&q=80",
            "https://www.youtube.com/embed/49HTIoCccDY",
            List.of("White marble inlay", "Charbagh garden", "Yamuna riverfront")));
        tours.save(tour("Hampi", "Vijayanagara, Karnataka", "Temple City",
            "A UNESCO-listed landscape of temple complexes, market streets, stone chariots, and royal enclosures from the Vijayanagara Empire.",
            "https://images.unsplash.com/photo-1620766182966-c6eb5ed2b788?auto=format&fit=crop&w=1200&q=80",
            "https://www.youtube.com/embed/8Z1eMy2FoX4",
            List.of("Virupaksha Temple", "Stone chariot", "Royal enclosure")));
      }

      if (guides.count() == 0) {
        guides.save(guide("Sarayu", "Agra", "Hindi, English", "6 years", "Mughal history", 4.9));
        guides.save(guide("Paranthi", "Hampi", "Kannada, English", "4 years", "Vijayanagara culture", 4.8));
        guides.save(guide("Sweety", "Konark", "Odia, Hindi, English", "5 years", "Temple iconography", 4.7));
      }
    };
  }

  private void saveUser(UserAccountRepository users, BCryptPasswordEncoder encoder, String name, String email, String password, Role role) {
    UserAccount user = new UserAccount();
    user.setName(name);
    user.setEmail(email);
    user.setPasswordHash(encoder.encode(password));
    user.setRole(role);
    users.save(user);
  }

  private Tour tour(String title, String location, String category, String description, String imageUrl, String videoUrl, List<String> highlights) {
    Tour tour = new Tour();
    tour.setTitle(title);
    tour.setLocation(location);
    tour.setCategory(category);
    tour.setDescription(description);
    tour.setImageUrl(imageUrl);
    tour.setVideoUrl(videoUrl);
    tour.setHighlights(highlights);
    tour.setVerified(true);
    return tour;
  }

  private Guide guide(String name, String city, String languages, String experience, String expertise, double rating) {
    Guide guide = new Guide();
    guide.setName(name);
    guide.setCity(city);
    guide.setLanguages(languages);
    guide.setExperience(experience);
    guide.setExpertise(expertise);
    guide.setRating(rating);
    return guide;
  }
}
