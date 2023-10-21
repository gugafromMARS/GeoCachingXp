package gsc.projects.registermcs;


import gsc.projects.registermcs.dto.CacheRegisterDto;
import gsc.projects.registermcs.dto.RegisterCreateDto;
import gsc.projects.registermcs.dto.UserLevelDto;
import gsc.projects.registermcs.service.APICache;
import gsc.projects.registermcs.service.APIUserLevel;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CanRegistry {

    private final APIUserLevel apiUserLevel;
    private final APICache apiCache;

    public boolean haveLevel(RegisterCreateDto registerCreateDto){
        UserLevelDto userLevelDto = apiUserLevel.getUserLevel(registerCreateDto.getUserEmail());
        CacheRegisterDto cacheRegisterDto = apiCache.getCacheForRegister(registerCreateDto.getCacheCode());
        if(userLevelDto != null && cacheRegisterDto != null){
            return userLevelDto.getUserLevel() >= cacheRegisterDto.getCacheLevel();
        }
        return false;
    }
}
