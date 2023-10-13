package gsc.projects.usersmcs.service;

import gsc.projects.usersmcs.converter.UserConverter;
import gsc.projects.usersmcs.dto.UserCreateDto;
import gsc.projects.usersmcs.dto.UserDto;
import gsc.projects.usersmcs.dto.UserUpdateDto;
import gsc.projects.usersmcs.model.User;
import gsc.projects.usersmcs.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class UserServiceImpTest {

    @Mock
    UserRepository userRepository;
    @Mock
    UserConverter userConverter;
    @InjectMocks
    UserServiceImp userServiceImp;

    UserCreateDto userCreateDto;
    User user;

    User user2;
    UserDto userDto;
    UserUpdateDto userUpdateDto;

    @BeforeEach
    void setup(){
        userRepository.deleteAll();

        userCreateDto = new UserCreateDto();
        userCreateDto.setEmail("unitTest@email.com");
        userCreateDto.setName("test");

        user = new User();
        user.setName("test");
        user.setEmail("unitTest@email.com");


        userUpdateDto = new UserUpdateDto();
        userUpdateDto.setEmail("newUnitTest@email.com");

        userDto = new UserDto();
        userDto.setName("test");
        userDto.setEmail(userUpdateDto.getEmail());

        user2 = new User();
        user2.setEmail("unitTest@email.com");
    }


    @Nested
    @Tag("Unit tests")
    class UserUnitTests  {

        @Test
        @DisplayName("create a new user, persist it on db")
        public void createAnNewUser(){

            given(userRepository.findById(user.getId())).willReturn(null);

            when(userRepository.save(user)).thenReturn(user);

            userDto.setId(user.getId());

            assertEquals(userDto.getId(), user.getId());
        }

        @Test
        @DisplayName("Try to create a user that already exists in db")
        public void tryToCreateUserThatAlreadyExists() {

            given(userRepository.save(user)).willReturn(user);

            given(userRepository.findById(user.getId())).willReturn(Optional.ofNullable(user));

            when(userRepository.save(user2)).thenReturn(null);

            assertEquals(null, user2.getId());
        }

        @Test
        @DisplayName("Delete user by id on db")
        public void deleteAnExistsUser(){

            given(userRepository.save(user)).willReturn(user);

            given(userRepository.findById(user.getId())).willReturn(Optional.ofNullable(user));

            userRepository.delete(user);

            when(userRepository.findById(user.getId())).thenReturn(null);

            assertEquals(null, user.getId());
        }


        @Test
        @DisplayName("Update user by id on db")
        public void updateUserByIdOnDb() {

            given(userRepository.save(user)).willReturn(user);

            given(userRepository.findById(user.getId())).willReturn(Optional.ofNullable(user));

            when(userServiceImp.updateUserById(user.getId(), userUpdateDto)).thenReturn(userDto);

            assertEquals(userDto.getEmail(), userUpdateDto.getEmail());
        }
    }


}