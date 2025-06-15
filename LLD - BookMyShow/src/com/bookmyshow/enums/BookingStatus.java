package com.bookmyshow.enums;

import lombok.Getter;

@Getter
public enum BookingStatus {
    PENDING,
    CONFIRMED,
    CANCELLED,
    COMPLETED;
}
