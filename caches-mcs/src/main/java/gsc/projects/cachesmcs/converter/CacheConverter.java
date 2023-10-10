package gsc.projects.cachesmcs.converter;

import gsc.projects.cachesmcs.dto.CacheCreateDto;
import gsc.projects.cachesmcs.dto.CacheDto;
import gsc.projects.cachesmcs.model.Cache;
import org.springframework.stereotype.Component;

@Component
public class CacheConverter {


    public CacheDto toDto(Cache cache) {
        return CacheDto.builder()
                .id(cache.getId())
                .address(cache.getAddress())
                .description(cache.getDescription())
                .tip(cache.getTip())
                .difficulty(cache.getDifficulty())
                .groundLevel(cache.getGroundLevel())
                .size(cache.getSize())
                .cacheLevel(cache.getCacheLevel())
                .experienceEarned(cache.getExperienceEarned())
                .build();
    }


    public Cache fromCreateDto(CacheCreateDto cacheCreateDto){
        return Cache.builder()
                .withDescription(cacheCreateDto.getDescription())
                .withTip(cacheCreateDto.getTip())
                .withDifficulty(cacheCreateDto.getDifficulty())
                .withGroundLevel(cacheCreateDto.getGroundLevel())
                .withSize(cacheCreateDto.getSize())
                .withCacheLevel(cacheCreateDto.getCacheLevel())
                .withExperienceEarned()
                .build();
    }
}
