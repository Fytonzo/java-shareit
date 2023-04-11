package ru.practicum.shareit.booking;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.booking.status.Status;

import java.time.LocalDateTime;

/**
 * Booking model.
 */
@Data
@Builder(toBuilder = true)
public class Booking {
    private int id;
    private LocalDateTime start;
    private LocalDateTime end;
    private int itemId;
    private int booker;
    private Status status;
}
