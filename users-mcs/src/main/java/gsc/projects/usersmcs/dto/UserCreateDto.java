package gsc.projects.usersmcs.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateDto {

    private String name;
    private int age;
    private String address;
    private String email;
}
