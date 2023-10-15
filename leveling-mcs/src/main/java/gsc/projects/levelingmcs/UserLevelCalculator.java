package gsc.projects.levelingmcs;

import gsc.projects.levelingmcs.dto.CacheLevelingDto;
import gsc.projects.levelingmcs.dto.UserDto;
import gsc.projects.levelingmcs.model.ULevel;
import gsc.projects.levelingmcs.repository.UserLevelRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserLevelCalculator {

    private static UserLevelRepository userLevelRepository;

    @Transactional
    public ULevel increaseLevel(UserDto userDto, CacheLevelingDto cacheLevelingDto){
        ULevel currentLevel = userLevelRepository.findByUserEmail(userDto.getEmail());
        double updateLevel = currentLevel.getUserLevel() + cacheLevelingDto.getExperienceEarned();
        currentLevel.setUserLevel(updateLevel);
        userLevelRepository.save(currentLevel);
        return currentLevel;
    }
}
