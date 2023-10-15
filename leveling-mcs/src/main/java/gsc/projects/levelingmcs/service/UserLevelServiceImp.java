package gsc.projects.levelingmcs.service;


import gsc.projects.levelingmcs.UserLevelCalculator;
import gsc.projects.levelingmcs.converter.UserLevelConverter;
import gsc.projects.levelingmcs.dto.CacheLevelingDto;
import gsc.projects.levelingmcs.dto.LevelCreateDto;
import gsc.projects.levelingmcs.dto.UserDto;
import gsc.projects.levelingmcs.dto.UserLevelDto;
import gsc.projects.levelingmcs.model.ULevel;
import gsc.projects.levelingmcs.repository.UserLevelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserLevelServiceImp {

    private final UserLevelRepository userLevelRepository;

    private final UserLevelConverter userLevelConverter;

    private final UserLevelCalculator userLevelCalculator;

    private final APIUser apiUser;

    private final APICache apiCache;


    public UserLevelDto getLevelByEmail(String userEmail) {
        ULevel uLevel = userLevelRepository.findByUserEmail(userEmail);
        UserDto userDto = apiUser.getUser(userEmail);

        return userLevelConverter.toDto(userDto, uLevel);
    }


    public UserLevelDto increaseUserLevel(LevelCreateDto levelCreateDto) {
        UserDto userDto = apiUser.getUser(levelCreateDto.getUserEmail());

        CacheLevelingDto cacheLevelingDto = apiCache.getCacheLevel(levelCreateDto.getCacheCode());

        ULevel uLevel = userLevelCalculator.increaseLevel(userDto, cacheLevelingDto);

        return userLevelConverter.toDto(userDto, uLevel);
    }
}
