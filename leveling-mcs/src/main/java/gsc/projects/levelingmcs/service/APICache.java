package gsc.projects.levelingmcs.service;


import gsc.projects.levelingmcs.dto.CacheLevelingDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url ="http://localhost:8083", value = "CACHES-SERVICE")
public interface APICache {

    @GetMapping("/level/{cacheCode}")
    CacheLevelingDto getCacheLevel(@PathVariable ("cacheCode") String cacheCode);
}
