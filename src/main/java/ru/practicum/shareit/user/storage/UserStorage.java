package ru.practicum.shareit.user.storage;

import ru.practicum.shareit.user.model.User;

import java.util.List;

public interface UserStorage {

    User saveUser(User user);

    User updateUser(User user);

    User getUserById(int id);

    void deleteUserById(int id);

    List<User> getAllUsers();

}
