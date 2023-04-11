package ru.practicum.shareit.request;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * Item request model
 */
@Data
@Builder(toBuilder = true)
public class ItemRequest {
    private int id;
    private String description;
    private int requester;
    private LocalDateTime created;
}
