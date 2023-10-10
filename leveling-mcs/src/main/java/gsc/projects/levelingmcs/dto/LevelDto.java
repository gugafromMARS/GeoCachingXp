package gsc.projects.levelingmcs.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LevelDto {

    private UserDto userDto;
    private double userLevel;
}
