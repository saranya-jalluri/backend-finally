package com.heritage.explorer.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

@Entity
public class Booking {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String userName;
  private String userEmail;
  private Long guideId;
  private String guideName;
  private String tourTitle;
  private String monument;
  private String date;

  @Lob
  private String message;

  @Lob
  private String reply;

  @Enumerated(EnumType.STRING)
  private BookingStatus status = BookingStatus.Pending;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserEmail() {
    return userEmail;
  }

  public void setUserEmail(String userEmail) {
    this.userEmail = userEmail;
  }

  public Long getGuideId() {
    return guideId;
  }

  public void setGuideId(Long guideId) {
    this.guideId = guideId;
  }

  public String getGuideName() {
    return guideName;
  }

  public void setGuideName(String guideName) {
    this.guideName = guideName;
  }

  public String getTourTitle() {
    return tourTitle;
  }

  public void setTourTitle(String tourTitle) {
    this.tourTitle = tourTitle;
  }

  public String getMonument() {
    return monument;
  }

  public void setMonument(String monument) {
    this.monument = monument;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getReply() {
    return reply;
  }

  public void setReply(String reply) {
    this.reply = reply;
  }

  public BookingStatus getStatus() {
    return status;
  }

  public void setStatus(BookingStatus status) {
    this.status = status;
  }
}
