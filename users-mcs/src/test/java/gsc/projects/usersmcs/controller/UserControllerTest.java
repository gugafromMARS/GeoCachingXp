package gsc.projects.usersmcs.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gsc.projects.usersmcs.dto.UserCreateDto;
import gsc.projects.usersmcs.dto.UserDto;
import gsc.projects.usersmcs.dto.UserUpdateDto;
import gsc.projects.usersmcs.model.User;
import gsc.projects.usersmcs.repository.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;


import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class UserControllerTest {


    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    UserCreateDto userCreateDto;
    User user;
    UserDto userDto;

    UserUpdateDto userUpdateDto;

    @BeforeEach
    void setup(){
        userRepository.deleteAll();

        userCreateDto = new UserCreateDto();
        userCreateDto.setName("test");
        userCreateDto.setEmail("test@email.com");
        userCreateDto.setAddress("address test");

        user = new User();
        user.setName("test");
        user.setEmail("test@email.com");
        user.setAddress("address test");


        userDto = new UserDto();
        userDto.setName("test");
        userDto.setEmail("test@email.com");

        userUpdateDto = new UserUpdateDto();
        userUpdateDto.setEmail("newtest@email.com");

    }


    public void persistUser() {
        userRepository.save(user);
        userDto.setId(user.getId());
    }

    @Nested
    @Tag("Integration tests")
    public class UserIntegrationTests {


        @Test
        @DisplayName("Create an user that not exists and return 200")
        public void createValidUser200() throws Exception {

            ResultActions response = mockMvc.perform(post("/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userCreateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.name", is(userCreateDto.getName())));
        }

        @Test
        @DisplayName("Try to create a user with a email that already exist return 400")
        public void tryToCreateAnUserThatAlreadyExists400() throws Exception {

            persistUser();

            ResultActions response = mockMvc.perform(post("/user")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userCreateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("Get an exists user and return 200")
        public void getAnExistsUser200() throws Exception{
            persistUser();

            ResultActions response = mockMvc.perform(get("/user/{userId}", user.getId()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        }


        @Test
        @DisplayName("Delete an exists user and return 200")
        public void deleteAnExistsUser200() throws Exception {

            persistUser();

            ResultActions response = mockMvc.perform(delete("/user/{userId}", user.getId()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());

            ResultActions response2 = mockMvc.perform(get("/user/{userId}", user.getId()));

            response2.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Update an exists user and return 200")
        public void updateAnExistsUser200() throws Exception{

            persistUser();

            ResultActions response = mockMvc.perform(get("/user/{userId}", user.getId()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.email", is(user.getEmail())));


            ResultActions response2 = mockMvc.perform(put("/user/{userId}", user.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(userUpdateDto)));

            response2.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.email", is(userUpdateDto.getEmail())));
        }


    }












}