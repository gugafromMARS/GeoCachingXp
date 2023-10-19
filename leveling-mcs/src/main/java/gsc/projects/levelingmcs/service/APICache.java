package gsc.projects.levelingmcs.service;


import gsc.projects.levelingmcs.dto.CacheLevelingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CACHES-SERVICE")
public interface APICache {

    @GetMapping("/cache/level/{cacheCode}")
    CacheLevelingDto getCacheLevel(@PathVariable ("cacheCode") String cacheCode);
}
