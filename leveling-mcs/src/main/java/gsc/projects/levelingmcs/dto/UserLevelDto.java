package gsc.projects.levelingmcs.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLevelDto {

    private UserDto userDto;
    private double userLevel;
}
