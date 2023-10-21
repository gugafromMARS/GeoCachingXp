package gsc.projects.cachesmcs.controller;

import gsc.projects.cachesmcs.dto.CacheCreateDto;
import gsc.projects.cachesmcs.dto.CacheUpdateDto;
import gsc.projects.cachesmcs.service.CacheService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cache")
@AllArgsConstructor
public class CacheController {

    private final CacheService cacheService;


    @GetMapping
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(cacheService.getAllCaches());
    }

    @GetMapping("/{cacheCode}")
    public ResponseEntity<?> getCache(@PathVariable ("cacheCode") String cacheCode){
        return ResponseEntity.ok(cacheService.getCacheByCode(cacheCode));
    }

    @GetMapping("/register/{cacheCode}")
    public ResponseEntity<?> getCacheForRegister(@PathVariable ("cacheCode") String cacheCode){
        return ResponseEntity.ok(cacheService.getCacheForReg(cacheCode));
    }


    @PostMapping
    public ResponseEntity<?> create(@RequestBody CacheCreateDto cacheCreateDto){
        return new ResponseEntity<>(cacheService.createCache(cacheCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{cacheId}")
    public ResponseEntity<?> delete(@PathVariable ("cacheId") Long cacheId){
        cacheService.deleteById(cacheId);
        return new ResponseEntity<>("Cache deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{cacheCode}")
    public ResponseEntity<?> update(@PathVariable("cacheCode") String cacheCode, @RequestBody CacheUpdateDto cacheUpdateDto){
        return ResponseEntity.ok(cacheService.updateCache(cacheCode, cacheUpdateDto));
    }

}
