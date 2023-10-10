package gsc.projects.registermcs.converter;

import gsc.projects.registermcs.dto.RegisterCreateDto;
import gsc.projects.registermcs.dto.RegisterDto;
import gsc.projects.registermcs.model.Register;
import org.springframework.stereotype.Component;

@Component
public class RegisterConverter {

    public RegisterDto toDto(Register register){
        return RegisterDto.builder()
                .id(register.getId())
                .localDate(register.getLocalDate())
                .build();
    }

    public Register fromCreateDto(RegisterCreateDto registerCreateDto){
        return Register.builder()
                .withLocalDate(registerCreateDto.getLocalDate())
                .build();

    }
}
