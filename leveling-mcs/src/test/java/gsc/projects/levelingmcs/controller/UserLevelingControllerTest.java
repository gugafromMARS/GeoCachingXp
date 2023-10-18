package gsc.projects.levelingmcs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import gsc.projects.levelingmcs.dto.LevelCreateDto;
import gsc.projects.levelingmcs.dto.UserLevelDto;
import gsc.projects.levelingmcs.model.ULevel;
import gsc.projects.levelingmcs.repository.UserLevelRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
class UserLevelingControllerTest {


    @Autowired
    UserLevelRepository userLevelRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;
    LevelCreateDto levelCreateDto;
    UserLevelDto userLevelDto;

    ULevel uLevel;
    @BeforeEach
    void setup(){
        userLevelRepository.deleteAll();

        levelCreateDto = new LevelCreateDto();
        levelCreateDto.setCacheCode("firstcacheapp");
        levelCreateDto.setUserEmail("newtest@email.com");

        userLevelDto = new UserLevelDto();

        uLevel = new ULevel();
        uLevel.setUserLevel(1.0);
        uLevel.setUserEmail(levelCreateDto.getUserEmail());
    }

    void persistUserLevel(){
        userLevelRepository.save(uLevel);
    }
    @Nested
    @Tag("Integration tests")
    class LevelIntegrationTests{


        @Test
        @DisplayName("Create leveling for a exists user and a exists cache and return 200")
        public void createLeveling200() throws Exception{

            ResultActions response = mockMvc.perform(post("/level")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(levelCreateDto)));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isCreated());
        }

        @Test
        @DisplayName("Get level from user email 200")
        public void getLevelByUserEmail200() throws Exception{

            persistUserLevel();

            ResultActions response = mockMvc.perform(get("/level/user/{userEmail}", levelCreateDto.getUserEmail()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isOk());
        }

        @Test
        @DisplayName("Try to get a level from a user that not have level yet 400")
        public void tryToGetLevelFromUserThatNotHave400() throws Exception{

            ResultActions response = mockMvc.perform(get("/level/user/{userEmail}", levelCreateDto.getUserEmail()));

            response.andDo(MockMvcResultHandlers.print())
                    .andExpect(status().isBadRequest());
        }

    }
}