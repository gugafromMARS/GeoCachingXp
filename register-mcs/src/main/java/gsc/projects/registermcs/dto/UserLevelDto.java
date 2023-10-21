package gsc.projects.registermcs.dto;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLevelDto {

    private String userEmail;
    private double userLevel;
}
