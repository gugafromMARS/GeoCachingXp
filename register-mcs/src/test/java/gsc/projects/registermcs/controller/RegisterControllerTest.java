package gsc.projects.registermcs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gsc.projects.registermcs.dto.CacheRegisterDto;
import gsc.projects.registermcs.dto.RegisterCreateDto;
import gsc.projects.registermcs.dto.RegisterDto;
import gsc.projects.registermcs.model.Register;
import gsc.projects.registermcs.repository.RegisterRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class RegisterControllerTest {


    @Autowired
    RegisterRepository registerRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;
    Register register;
    RegisterDto registerDto;
    List<RegisterDto> registerDtosList;
    RegisterCreateDto registerCreateDto;
    @BeforeEach
    void setup(){
        registerRepository.deleteAll();

        register = new Register();
        register.setUserEmail("test@gmail.com");
        register.setCacheCode("firstcacheapp");
        register.setLocalDate(LocalDate.parse("2023-01-21"));

        registerDto = new RegisterDto();
        registerDto.setLocalDate(register.getLocalDate());

        registerDtosList = new ArrayList<>();
    }

    void persistRegister(){
        registerRepository.save(register);

        registerDtosList.add(registerDto);
    }

    void createReg(){
        registerCreateDto = new RegisterCreateDto();
        registerCreateDto.setCacheCode(register.getCacheCode());
        registerCreateDto.setUserEmail("newEmail@gmail.com");
    }
    @Nested
    @Tag("Integration tests")
    class RegisterIntegrationTests{

        @Test
        @DisplayName("Get an exists registers by user email and return 200")
        public void getAnExistsRegistersByUserEmail200() throws Exception{
            persistRegister();

            ResultActions response = mockMvc.perform(get("/register/{userEmail}", register.getUserEmail()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());

            assertEquals(1, registerDtosList.size());

        }

        @Test
        @DisplayName("Try to get a registers from a non exists user and return 404")
        public void tryToGetARegistersFromNonExistsUser404() throws Exception{

            ResultActions response = mockMvc.perform(get("/register/{userEmail}", "albino"));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());

            assertEquals(0, registerDtosList.size());
        }

        @Test
        @DisplayName("Get a cache from a register with userEmail and cacheCode and return 200")
        public void getAnCacheFromRegister200() throws Exception{
            persistRegister();

            ResultActions response = mockMvc.perform(get("/register/{userEmail}/cache/{cacheCode}",
                    register.getUserEmail(), register.getCacheCode()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Try to get a register that not exist return 404")
        public void tryToGetRegisterNonExists404() throws Exception{

            ResultActions response = mockMvc.perform(get("/register/{userEmail}/cache/{cacheCode}",
                    register.getUserEmail(), "testcode"));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("Create a non exists register and return 200")
        public void createRegister200() throws Exception{
            createReg();

            ResultActions response = mockMvc.perform(post("/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(registerCreateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isCreated());
        }

        @Test
        @DisplayName("Try to create an already exists register and return 404")
        public void tryToCreateAlreadyExistsRegister404() throws Exception{
            persistRegister();
            createReg();
            registerCreateDto.setUserEmail(register.getUserEmail());

            ResultActions response = mockMvc.perform(post("/register")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(registerCreateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isBadRequest());
        }
    }

}