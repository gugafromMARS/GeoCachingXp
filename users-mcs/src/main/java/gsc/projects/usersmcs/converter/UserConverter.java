package gsc.projects.usersmcs.converter;

import gsc.projects.usersmcs.dto.UserCreateDto;
import gsc.projects.usersmcs.dto.UserDto;
import gsc.projects.usersmcs.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    public UserDto toDto(User user){
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }

    public User fromCreateDto(UserCreateDto userCreateDto){
        return User.builder()
                .withAge(userCreateDto.getAge())
                .withAddress(userCreateDto.getAddress())
                .withEmail(userCreateDto.getEmail())
                .build();
    }
}
