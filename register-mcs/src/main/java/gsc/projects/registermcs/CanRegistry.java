package gsc.projects.registermcs;


import gsc.projects.registermcs.dto.CacheRegisterDto;
import gsc.projects.registermcs.dto.RegisterCreateDto;
import gsc.projects.registermcs.model.Register;
import gsc.projects.registermcs.repository.RegisterRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
public class CanRegistry {

    private final RegisterRepository registerRepository;

    public boolean haveLevel(RegisterCreateDto registerCreateDto, CacheRegisterDto cacheRegisterDto){
        List<Register> registerList = registerRepository.findAllByUserEmail(registerCreateDto.getUserEmail());

        if(registerList != null && cacheRegisterDto != null){
            if(registerList.size() == 0){
                return cacheRegisterDto.getCacheLevel() == 1;
            }
            Register lastRegister = registerList.get(registerList.size() - 1);
            return lastRegister.getUserLevel() >= cacheRegisterDto.getCacheLevel();
        }
        return false;
    }
}
