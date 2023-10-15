package gsc.projects.levelingmcs.converter;


import gsc.projects.levelingmcs.dto.UserLevelDto;
import gsc.projects.levelingmcs.dto.UserDto;
import gsc.projects.levelingmcs.model.ULevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserLevelConverter {


    public UserLevelDto toDto(UserDto userDto, ULevel uLevel){
        return UserLevelDto.builder()
                .userDto(userDto)
                .userLevel(uLevel.getUserLevel())
                .build();
    }

}
