package gsc.projects.registermcs.controller;


import gsc.projects.registermcs.dto.RegisterCreateDto;
import gsc.projects.registermcs.service.RegisterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @GetMapping("/{userEmail}")
    public ResponseEntity<?> getRegisters(@PathVariable ("userEmail") String userEmail){
        return ResponseEntity.ok(registerService.getAll(userEmail));
    }

    @GetMapping("/{userEmail}/cache/{cacheCode}")
    public ResponseEntity<?> getRegister(@PathVariable ("userEmail") String userEmail, @PathVariable ("cacheCode") String cacheCode){
        return ResponseEntity.ok(registerService.getByEmailAndCacheCode(userEmail, cacheCode));
    }

    @GetMapping("/level/{userEmail}")
    public ResponseEntity<?> getLevel(@PathVariable ("userEmail") String userEmail){
        return ResponseEntity.ok(registerService.getUserLevel(userEmail));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RegisterCreateDto registerCreateDto) {
        return new ResponseEntity<>(registerService.createRegister(registerCreateDto), HttpStatus.CREATED);
    }
}
