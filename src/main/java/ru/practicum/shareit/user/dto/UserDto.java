package ru.practicum.shareit.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * UserDto model.
 */
@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class UserDto {
    private int id;
    @NotNull
    private String name;
    @Email
    @NotNull
    private String email;
}
