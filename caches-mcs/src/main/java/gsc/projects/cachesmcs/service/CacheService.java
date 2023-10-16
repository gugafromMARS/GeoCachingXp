package gsc.projects.cachesmcs.service;

import gsc.projects.cachesmcs.dto.*;

import java.util.List;

public interface CacheService {
    List<CacheDto> getAllCaches();

    CacheDto createCache(CacheCreateDto cacheCreateDto);

    CacheDto getCacheByCode(String cacheCode);

    void deleteById(Long cacheId);

    CacheDto updateCache(String cacheCode, CacheUpdateDto cacheUpdateDto);

    CacheLevelingDto getCacheForLevel(String cacheCode);

    CacheRegisterDto getCacheForReg(String cacheCode);

}
