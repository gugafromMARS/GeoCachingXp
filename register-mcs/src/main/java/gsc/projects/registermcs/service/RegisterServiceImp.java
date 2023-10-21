package gsc.projects.registermcs.service;


import gsc.projects.registermcs.CanRegistry;
import gsc.projects.registermcs.LevelCalculator;
import gsc.projects.registermcs.converter.RegisterConverter;
import gsc.projects.registermcs.dto.CacheRegisterDto;
import gsc.projects.registermcs.dto.RegisterCreateDto;
import gsc.projects.registermcs.dto.RegisterDto;
import gsc.projects.registermcs.dto.UserLevelDto;
import gsc.projects.registermcs.model.Register;
import gsc.projects.registermcs.repository.RegisterRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RegisterServiceImp implements RegisterService {

    private final RegisterRepository registerRepository;

    private final RegisterConverter registerConverter;

    private final CanRegistry canRegistry;

    private final LevelCalculator levelCalculator;

    private final APICache apiCache;

    @Override
    public List<RegisterDto> getAll(String userEmail) {
        List<Register> registers = registerRepository.findAllByUserEmail(userEmail);
        if(registers.size() == 0){
            return new ArrayList<>();
        }
        return registerConverter.toDtos(registers);
    }

    @Override
    public RegisterDto getByEmailAndCacheCode(String userEmail, String cacheCode) {
        Register existingRegister = registerRepository.findRegisterByUserEmailAndCacheCode(userEmail,
                cacheCode);
        if(existingRegister == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Register not found");
        }
        return registerConverter.toDto(existingRegister);
    }


    @Override
    public RegisterDto createRegister(RegisterCreateDto registerCreateDto) {
        Register existingRegister = registerRepository.findRegisterByUserEmailAndCacheCode(registerCreateDto.getUserEmail(),
                registerCreateDto.getCacheCode());
        if(existingRegister != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Register already exists");
        }
        CacheRegisterDto cacheRegisterDto = apiCache.getCacheForRegister(registerCreateDto.getCacheCode());
        if(canRegistry.haveLevel(registerCreateDto, cacheRegisterDto)){
            double userLevel = levelCalculator.increaseLevel(registerCreateDto, cacheRegisterDto);
            Register newRegister = registerConverter.fromCreateDto(registerCreateDto, userLevel);
            registerRepository.save(newRegister);
            return registerConverter.toDto(newRegister);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Can not registry this cache because you dont have level");
    }

    @Override
    public UserLevelDto getUserLevel(String userEmail) {
        List<Register> registersList = registerRepository.findAllByUserEmail(userEmail);
        if(registersList != null && registersList.size() > 0){
            Register lastRegister = registersList.get(registersList.size() - 1);
            return registerConverter.toUserLevelDto(userEmail, lastRegister.getUserLevel());
        }
        return registerConverter.toUserLevelDto(userEmail, 1.0);
    }
}
