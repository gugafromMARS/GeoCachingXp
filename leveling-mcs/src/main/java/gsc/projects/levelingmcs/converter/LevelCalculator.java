package gsc.projects.levelingmcs.converter;


import gsc.projects.levelingmcs.dto.CacheLevelingDto;
import gsc.projects.levelingmcs.dto.LevelDto;
import gsc.projects.levelingmcs.dto.UserDto;
import gsc.projects.levelingmcs.model.Level;
import gsc.projects.levelingmcs.repository.LevelRepository;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class LevelCalculator {

    private static LevelRepository levelRepository;

    public LevelDto toDto(UserDto userDto, Level level){
        return LevelDto.builder()
                .userDto(userDto)
                .userLevel(level.getUserLevel())
                .build();
    }

    @Transactional
    public Level increaseLevel(UserDto userDto, CacheLevelingDto cacheLevelingDto){
        Level currentlevel = levelRepository.findByUserEmail(userDto.getEmail());
        double updateLevel = currentlevel.getUserLevel() + cacheLevelingDto.getExperienceEarned();
        currentlevel.setUserLevel(updateLevel);
        levelRepository.save(currentlevel);
        return currentlevel;
    }
}
