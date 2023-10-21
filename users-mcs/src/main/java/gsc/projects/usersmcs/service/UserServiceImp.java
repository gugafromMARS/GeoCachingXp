package gsc.projects.usersmcs.service;


import gsc.projects.usersmcs.converter.UserConverter;
import gsc.projects.usersmcs.dto.UserCreateDto;
import gsc.projects.usersmcs.dto.UserDto;
import gsc.projects.usersmcs.dto.UserLevelDto;
import gsc.projects.usersmcs.dto.UserUpdateDto;
import gsc.projects.usersmcs.model.User;
import gsc.projects.usersmcs.repository.UserRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImp implements UserService {

    private final UserRepository userRepository;

    private final UserConverter userConverter;

    private final APIUserLevel apiUserLevel;

    @Override
    public UserDto createUser(UserCreateDto userCreateDto) {
        User existingUser = userRepository.findByEmail(userCreateDto.getEmail());

        if(existingUser != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already found");
        }

        User newUser = userConverter.fromCreateDto(userCreateDto);
        userRepository.save(newUser);
        return userConverter.toDto(newUser);
    }


    @Override
    public UserDto getUserByEmail(String userEmail) {
          User existingUser = userRepository.findByEmail(userEmail);
          if(existingUser == null){
              throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
          }
          return userConverter.toDto(existingUser);
    }


    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> userConverter.toDto(user))
                .toList();
    }


    @Override
    public void deleteUserById(Long userId) {
        User existingUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        userRepository.delete(existingUser);
    }


    @Override
    public UserDto updateUserById(Long userId, UserUpdateDto userUpdateDto) {
           User existingUser = userRepository.findById(userId)
                   .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
           existingUser.setEmail(userUpdateDto.getEmail());
           userRepository.save(existingUser);
           return userConverter.toDto(existingUser);
    }

    @Override
    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultLevel")
    public UserLevelDto getUserLevel(String userEmail) {
       return apiUserLevel.getLevel(userEmail);
    }

    public UserLevelDto getDefaultLevel(String userEmail, Exception exception){
        User user = userRepository.findByEmail(userEmail);
        if(user == null){
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        UserDto userDto = userConverter.toDto(user);
        return new UserLevelDto(userEmail, 1);
    }
}
