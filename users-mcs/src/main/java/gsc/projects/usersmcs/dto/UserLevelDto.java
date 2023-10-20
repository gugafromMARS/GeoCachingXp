package gsc.projects.usersmcs.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLevelDto {

    private UserDto userDto;
    private double userLevel;
}
