package gsc.projects.registermcs.controller;


import gsc.projects.registermcs.dto.RegisterCreateDto;
import gsc.projects.registermcs.service.RegisterServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/register")
@AllArgsConstructor
public class RegisterController {

    private final RegisterServiceImp registerServiceImp;

    @GetMapping("/{userEmail}")
    public ResponseEntity<?> getRegisters(@PathVariable ("userEmail") String userEmail){
        return ResponseEntity.ok(registerServiceImp.getAll(userEmail));
    }

    @GetMapping("/{userEmail}/cache/{cacheCode}")
    public ResponseEntity<?> getRegister(@PathVariable ("userEmail") String userEmail, @PathVariable ("cacheCode") String cacheCode){
        return ResponseEntity.ok(registerServiceImp.getByEmailAndCacheCode(userEmail, cacheCode));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody RegisterCreateDto registerCreateDto) {
        return new ResponseEntity<>(registerServiceImp.createRegister(registerCreateDto), HttpStatus.CREATED);
    }
}
