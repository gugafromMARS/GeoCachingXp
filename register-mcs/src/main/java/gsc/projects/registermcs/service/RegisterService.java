package gsc.projects.registermcs.service;

import gsc.projects.registermcs.dto.RegisterCreateDto;
import gsc.projects.registermcs.dto.RegisterDto;
import gsc.projects.registermcs.dto.UserLevelDto;

import java.util.List;

public interface RegisterService {
    List<RegisterDto> getAll(String userEmail);

    RegisterDto getByEmailAndCacheCode(String userEmail, String cacheCode);

    RegisterDto createRegister(RegisterCreateDto registerCreateDto);
    UserLevelDto getUserLevel(String userEmail);
}
