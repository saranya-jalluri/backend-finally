package com.heritage.explorer.dto;

import com.heritage.explorer.model.BookingStatus;

public record BookingStatusRequest(BookingStatus status, String reply) {
}
