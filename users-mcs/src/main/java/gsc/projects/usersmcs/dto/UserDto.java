package gsc.projects.usersmcs.dto;


import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Long id;
    private String name;
    private String email;
}
