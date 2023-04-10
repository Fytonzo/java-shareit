package ru.practicum.shareit.user.storage;

import org.springframework.stereotype.Component;
import ru.practicum.shareit.exception.EmailException;
import ru.practicum.shareit.exception.EntityNotFoundException;
import ru.practicum.shareit.user.model.User;

import java.util.*;

@Component("InMemoryUserStorage")
public class InMemoryUserStorage implements UserStorage {

    private int id = 0;
    private final Map<Integer, User> users = new HashMap<>();

    @Override
    public User saveUser(User user) {
        for (User existingUser : users.values()) {
            if (Objects.equals(existingUser.getEmail(), user.getEmail())) {
                throw new EmailException("Два разных пользователя не могут иметь одинаковый " +
                        "адрес электронной почты!");
            }
        }
        id++;
        user.setId(id);
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        if (users.containsKey(user.getId())) {
            for (User existingUser : users.values()) {
                if ((Objects.equals(existingUser.getEmail(), user.getEmail())) &&
                        (existingUser.getId() != user.getId())) {
                    throw new EmailException("Два разных пользователя не могут иметь одинаковый " +
                            "адрес электронной почты!");
                }
            }
            User updatedUser = users.get(user.getId());
            if (user.getName() != null) {
                updatedUser.setName(user.getName());
            }
            if (user.getEmail() != null) {
                updatedUser.setEmail(user.getEmail());
            }
            users.put(user.getId(), updatedUser);
        } else {
            throw new EntityNotFoundException("Пользователь с id = " + user.getId() + " не найден!");
        }
        return users.get(user.getId());
    }

    @Override
    public User getUserById(int id) {
        if (users.containsKey(id)) {
            return users.get(id);
        } else {
            throw new EntityNotFoundException("Пользователь с id = " + id + " не найден!");
        }
    }

    @Override
    public void deleteUserById(int id) {
        if (users.containsKey(id)) {
            users.remove(id, users.get(id));
        } else {
            throw new EntityNotFoundException("Пользователь с id = " + id + " не найден!");
        }
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<>(users.values());
    }
}
