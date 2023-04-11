package ru.practicum.shareit.user;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.user.dto.UserDto;
import ru.practicum.shareit.user.interfaces.UserService;
import ru.practicum.shareit.user.interfaces.UserStorage;

import java.util.ArrayList;
import java.util.List;

/**
 * User service implementation.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserStorage userStorage;


    public UserServiceImpl(@Qualifier("InMemoryUserStorage") UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        return UserMapper.userToDto(userStorage.saveUser(UserMapper.dtoToUser(userDto)));
    }

    @Override
    public UserDto updateUser(UserDto userDto, int userId) {
        userDto.setId(userId);
        userStorage.updateUser(UserMapper.dtoToUser(userDto));
        return UserMapper.userToDto(userStorage.getUserById(userId));
    }

    @Override
    public UserDto getUserById(int id) {
        return UserMapper.userToDto(userStorage.getUserById(id));
    }

    @Override
    public void deleteUserById(int id) {
        userStorage.deleteUserById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<UserDto> result = new ArrayList<>();
        for (User user : userStorage.getAllUsers()) {
            result.add(UserMapper.userToDto(user));
        }
        return result;
    }
}
