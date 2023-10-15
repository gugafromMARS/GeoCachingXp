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
        ULevel uLevel = userLevelRepository.findByUserEmail(userDto.getEmail());
        if(uLevel == null){

            uLevel = new ULevel();
            uLevel.setUserLevel(1);
            uLevel.setUserEmail(userDto.getEmail());
            userLevelRepository.save(uLevel);

        } else {
            double currentLevel = uLevel.getUserLevel();
            double updateLevel = currentLevel + cacheLevelingDto.getExperienceEarned();
            uLevel.setUserLevel(updateLevel);
            userLevelRepository.save(uLevel);
        }
        return uLevel;
    }
}
