package gsc.projects.cachesmcs.service;


import gsc.projects.cachesmcs.controller.CacheController;
import gsc.projects.cachesmcs.converter.CacheConverter;
import gsc.projects.cachesmcs.dto.*;
import gsc.projects.cachesmcs.model.Cache;
import gsc.projects.cachesmcs.repository.CacheRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class CacheServiceImp implements CacheService {


    private final CacheRepository cacheRepository;

    private final CacheConverter cacheConverter;


    @Override
    public List<CacheDto> getAllCaches() {
        return cacheRepository.findAll().stream()
                .map(cache -> cacheConverter.toDto(cache))
                .toList();
    }

    @Override
    public CacheDto createCache(CacheCreateDto cacheCreateDto) {
        Cache existingCache = cacheRepository.findByCacheCode(cacheCreateDto.getCacheCode());
        if(existingCache != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Cache already exists");
        }
        Cache newCache = cacheConverter.fromCreateDto(cacheCreateDto);
        cacheRepository.save(newCache);
        return cacheConverter.toDto(newCache);
    }

    @Override
    public CacheDto getCacheByCode(String cacheCode) {
        Cache existingCache = cacheRepository.findByCacheCode(cacheCode);
        if(existingCache == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cache not found");
        }
        return cacheConverter.toDto(existingCache);
    }

    @Override
    public void deleteById(Long cacheId) {
        Cache existingCache = cacheRepository.findById(cacheId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Cache not found"));
        cacheRepository.delete(existingCache);
    }

    @Override
    public CacheDto updateCache(String cacheCode, CacheUpdateDto cacheUpdateDto) {
        Cache existingCache = cacheRepository.findByCacheCode(cacheCode);
        if(existingCache == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cache not found");
        }
        existingCache.setTip(cacheUpdateDto.getTip());
        cacheRepository.save(existingCache);
        return cacheConverter.toDto(existingCache);
    }

    @Override
    public CacheRegisterDto getCacheForReg(String cacheCode) {
        Cache existingCache = cacheRepository.findByCacheCode(cacheCode);

        if(existingCache == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cache not found");
        }
        return cacheConverter.cacheRegisterDto(existingCache);
    }
}
