package gsc.projects.registermcs.service;


import gsc.projects.registermcs.dto.CacheRegisterDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CACHES-SERVICE")
public interface APICache {

    @GetMapping("/cache/register/{cacheCode}")
    CacheRegisterDto getCacheForRegister(@PathVariable("cacheCode") String cacheCode);
}
