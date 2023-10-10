package gsc.projects.cachesmcs.service;


import gsc.projects.cachesmcs.controller.CacheController;
import gsc.projects.cachesmcs.converter.CacheConverter;
import gsc.projects.cachesmcs.repository.CacheRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CacheServiceImp {


    private final CacheRepository cacheRepository;

    private final CacheConverter cacheConverter;


}
