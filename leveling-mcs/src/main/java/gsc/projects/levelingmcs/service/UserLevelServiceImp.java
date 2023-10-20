package gsc.projects.levelingmcs.service;


import gsc.projects.levelingmcs.UserLevelCalculator;
import gsc.projects.levelingmcs.converter.UserLevelConverter;
import gsc.projects.levelingmcs.dto.CacheLevelingDto;
import gsc.projects.levelingmcs.dto.LevelCreateDto;
import gsc.projects.levelingmcs.dto.UserDto;
import gsc.projects.levelingmcs.dto.UserLevelDto;
import gsc.projects.levelingmcs.model.ULevel;
import gsc.projects.levelingmcs.repository.UserLevelRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class UserLevelServiceImp implements UserLevelService {

    private final UserLevelRepository userLevelRepository;

    private final UserLevelConverter  userLevelConverter;

    private final UserLevelCalculator userLevelCalculator;

    private final APIUser apiUser;

    private final APICache apiCache;


    @Override
    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultUser")
    public UserLevelDto getLevelByEmail(String userEmail) {
        ULevel uLevel = userLevelRepository.findByUserEmail(userEmail);
        if(uLevel == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User don't have level yet");
        }
        UserDto userDto = apiUser.getUser(userEmail);

        return userLevelConverter.toDto(userDto, uLevel);
    }

    public UserLevelDto getDefaultUser(String userEmail) throws Exception{
        ULevel uLevel = userLevelRepository.findByUserEmail(userEmail);
        if(uLevel == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User don't have level yet");
        }
        UserDto userDto = new UserDto("default@email.com");
        return userLevelConverter.toDto(userDto, uLevel);
    }


    @Override
    @CircuitBreaker(name = "${spring.application.name}", fallbackMethod = "getDefaultUserAndCache")
    public UserLevelDto increaseUserLevel(LevelCreateDto levelCreateDto) {
        UserDto userDto = apiUser.getUser(levelCreateDto.getUserEmail());

        CacheLevelingDto cacheLevelingDto = apiCache.getCacheLevel(levelCreateDto.getCacheCode());

        ULevel uLevel = userLevelCalculator.increaseLevel(userDto, cacheLevelingDto);

        return userLevelConverter.toDto(userDto, uLevel);
    }

    public UserLevelDto getDefaultUserAndCache(LevelCreateDto levelCreateDto) throws Exception{
        UserDto userDto = new UserDto("default@email.com");
        CacheLevelingDto cacheLevelingDto = new CacheLevelingDto(0);
        ULevel uLevel = new ULevel(userDto.getEmail(), 0);
        return userLevelConverter.toDto(userDto, uLevel);
    }
}
