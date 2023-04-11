package ru.practicum.shareit.user.interfaces;

import ru.practicum.shareit.user.dto.UserDto;

import java.util.List;

public interface UserService {

    UserDto saveUser(UserDto userDto);

    UserDto updateUser(UserDto userDto, int userId);

    UserDto getUserById(int id);

    void deleteUserById(int id);

    List<UserDto> getAllUsers();
}
