package gsc.projects.cachesmcs.service;

import gsc.projects.cachesmcs.dto.CacheCreateDto;
import gsc.projects.cachesmcs.dto.CacheDto;
import gsc.projects.cachesmcs.dto.CacheUpdateDto;

import java.util.List;

public interface CacheService {
    List<CacheDto> getAllCaches();

    CacheDto createCache(CacheCreateDto cacheCreateDto);

    CacheDto getCacheByCode(String cacheCode);

    void deleteById(Long cacheId);

    CacheDto updateCache(String cacheCode, CacheUpdateDto cacheUpdateDto);
}
