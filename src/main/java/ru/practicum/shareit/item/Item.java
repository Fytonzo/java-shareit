package ru.practicum.shareit.item;

import lombok.Builder;
import lombok.Data;
import ru.practicum.shareit.request.ItemRequest;
import ru.practicum.shareit.user.User;

/**
 * Item model.
 */
@Data
@Builder(toBuilder = true)
public class Item {
    private int id;
    private User owner;
    private String name;
    private String description;
    private Boolean available;
    private ItemRequest request;
}
