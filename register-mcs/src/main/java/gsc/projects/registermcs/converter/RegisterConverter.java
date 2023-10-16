package gsc.projects.registermcs.converter;

import gsc.projects.registermcs.dto.CacheRegisterDto;
import gsc.projects.registermcs.dto.RegisterCreateDto;
import gsc.projects.registermcs.dto.RegisterDto;
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

    public RegisterDto toDto(Register register){
        CacheRegisterDto cacheRegister = apiCache.getCacheForRegister(register.getCacheCode());

        return RegisterDto.builder()
                .id(register.getId())
                .localDate(register.getLocalDate())
                .cacheRegisterDto(cacheRegister)
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

    public Register fromCreateDto(RegisterCreateDto registerCreateDto){
        return Register.builder()
                .withLocalDate(registerCreateDto.getLocaldate())
                .withUserEmail(registerCreateDto.getUserEmail())
                .withCacheCode(registerCreateDto.getCacheCode())
                .build();
    }
}
