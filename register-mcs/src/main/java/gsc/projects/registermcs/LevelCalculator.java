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
public class LevelCalculator {

    private final RegisterRepository registerRepository;

    public double increaseLevel(RegisterCreateDto registerCreateDto, CacheRegisterDto cacheRegisterDto){
        List<Register> registerList = registerRepository.findAllByUserEmail(registerCreateDto.getUserEmail());
        if(registerList != null && cacheRegisterDto != null){
            if(registerList.size() != 0){
                Register lastRegister = registerList.get(registerList.size() - 1);
                return lastRegister.getUserLevel() + cacheRegisterDto.getExperienceEarned();
            }
            return 1.0 + cacheRegisterDto.getExperienceEarned();
        }
        return 1.0;
    }
}
