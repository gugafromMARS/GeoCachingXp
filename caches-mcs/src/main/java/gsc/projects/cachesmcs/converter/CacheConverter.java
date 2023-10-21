package gsc.projects.cachesmcs.converter;

import gsc.projects.cachesmcs.dto.CacheCreateDto;
import gsc.projects.cachesmcs.dto.CacheDto;
import gsc.projects.cachesmcs.dto.CacheRegisterDto;
import gsc.projects.cachesmcs.model.Cache;
import org.springframework.stereotype.Component;

@Component
public class CacheConverter {

    public CacheRegisterDto cacheRegisterDto(Cache cache){
        return CacheRegisterDto.builder()
                .id(cache.getId())
                .address(cache.getAddress())
                .description(cache.getDescription())
                .tip(cache.getTip())
                .cacheLevel(cache.getCacheLevel())
                .cacheCode(cache.getCacheCode())
                .experienceEarned(cache.getExperienceEarned())
                .build();
    }

    public CacheDto toDto(Cache cache) {
        return CacheDto.builder()
                .id(cache.getId())
                .address(cache.getAddress())
                .description(cache.getDescription())
                .tip(cache.getTip())
                .difficulty(cache.getDifficulty())
                .groundLevel(cache.getGroundLevel())
                .cacheSize(cache.getCacheSize())
                .cacheLevel(cache.getCacheLevel())
                .experienceEarned(cache.getExperienceEarned())
                .cacheCode(cache.getCacheCode())
                .build();
    }


    public Cache fromCreateDto(CacheCreateDto cacheCreateDto){
        return Cache.builder()
                .withAddress(cacheCreateDto.getAddress())
                .withDescription(cacheCreateDto.getDescription())
                .withTip(cacheCreateDto.getTip())
                .withDifficulty(cacheCreateDto.getDifficulty())
                .withGroundLevel(cacheCreateDto.getGroundLevel())
                .withSize(cacheCreateDto.getCacheSize())
                .withCacheLevel(cacheCreateDto.getCacheLevel())
                .withExperienceEarned()
                .withCacheCode(cacheCreateDto.getCacheCode())
                .build();
    }
}
