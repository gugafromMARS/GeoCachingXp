package gsc.projects.usersmcs.service;

import gsc.projects.usersmcs.dto.UserCreateDto;
import gsc.projects.usersmcs.dto.UserDto;
import gsc.projects.usersmcs.dto.UserUpdateDto;

import java.util.List;

public interface UserService {
    UserDto createUser(UserCreateDto userCreateDto);

    UserDto getUserByEmail(String userEmail);

    List<UserDto> getAllUsers();

    void deleteUserById(Long userId);

    UserDto updateUserById(Long userId, UserUpdateDto userUpdateDto);
}
