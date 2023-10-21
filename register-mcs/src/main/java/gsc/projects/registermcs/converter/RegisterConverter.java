package gsc.projects.registermcs.converter;

import gsc.projects.registermcs.dto.CacheRegisterDto;
import gsc.projects.registermcs.dto.RegisterCreateDto;
import gsc.projects.registermcs.dto.RegisterDto;
import gsc.projects.registermcs.dto.UserLevelDto;
import gsc.projects.registermcs.model.Register;
import gsc.projects.registermcs.service.APICache;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class RegisterConverter {

    private final APICache apiCache;

    public UserLevelDto toUserLevelDto(String userEmail, double userLevel){
        return UserLevelDto.builder()
                .userEmail(userEmail)
                .userLevel(userLevel)
                .build();
    }

    public RegisterDto toDto(Register register){
        CacheRegisterDto cacheRegister = apiCache.getCacheForRegister(register.getCacheCode());

        return RegisterDto.builder()
                .id(register.getId())
                .localDate(register.getLocalDate())
                .cacheRegisterDto(cacheRegister)
                .userLevel(register.getUserLevel())
                .build();
    }

    public List<RegisterDto> toDtos(List<Register> registers){
        List<RegisterDto> registerDtosList = new ArrayList<>();
        for(Register r : registers){
            RegisterDto regDto = toDto(r);
            registerDtosList.add(regDto);
        }
        return registerDtosList;
     }

    public Register fromCreateDto(RegisterCreateDto registerCreateDto, double userLevel){
        return Register.builder()
                .withLocalDate(registerCreateDto.getLocaldate())
                .withUserEmail(registerCreateDto.getUserEmail())
                .withCacheCode(registerCreateDto.getCacheCode())
                .withUserLevel(userLevel)
                .build();
    }
}
