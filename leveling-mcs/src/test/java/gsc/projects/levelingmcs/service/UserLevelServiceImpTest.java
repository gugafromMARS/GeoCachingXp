package gsc.projects.levelingmcs.service;

import gsc.projects.levelingmcs.UserLevelCalculator;
import gsc.projects.levelingmcs.converter.UserLevelConverter;
import gsc.projects.levelingmcs.dto.LevelCreateDto;
import gsc.projects.levelingmcs.model.ULevel;
import gsc.projects.levelingmcs.repository.UserLevelRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
class UserLevelServiceImpTest {

    @Mock
    UserLevelRepository userLevelRepository;

    @Mock
    UserLevelConverter userLevelConverter;

    @Mock
    UserLevelCalculator userLevelCalculator;

    @InjectMocks
    UserLevelServiceImp userLevelServiceImp;


    LevelCreateDto levelCreateDto;
    ULevel uLevel;

    @BeforeEach
    void setup(){
        userLevelRepository.deleteAll();

        levelCreateDto = new LevelCreateDto();
        levelCreateDto.setUserEmail("newtest@email.com");

        uLevel = new ULevel();
        uLevel.setUserLevel(1);
        uLevel.setUserEmail(levelCreateDto.getUserEmail());
    }

    @Nested
    @Tag("Unit tests")
    class LevelUnitTests{

        @Test
        @DisplayName("create a user level in bd")
        public void createUserLevel(){

            given(userLevelRepository.save(uLevel)).willReturn(uLevel);

            when(userLevelRepository.findByUserEmail(uLevel.getUserEmail())).thenReturn(uLevel);

            assertEquals(uLevel.getUserEmail(), levelCreateDto.getUserEmail());
        }


    }

}