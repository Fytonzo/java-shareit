package ru.practicum.shareit.user.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * User model.
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class User {
    private int id;
    private String name;
    private String email;
}
