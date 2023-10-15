package gsc.projects.levelingmcs.controller;


import gsc.projects.levelingmcs.dto.LevelCreateDto;
import gsc.projects.levelingmcs.service.UserLevelServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/level")
@AllArgsConstructor
public class UserLevelingController {

    private final UserLevelServiceImp userLevelServiceImp;

    @GetMapping("/user/{userEmail}")
    public ResponseEntity<?> getUserLevel(@PathVariable ("userEmail") String userEmail){
        return ResponseEntity.ok(userLevelServiceImp.getLevelByEmail(userEmail));
    }

    @PostMapping()
    public ResponseEntity<?> createLeveling(@RequestBody LevelCreateDto levelCreateDto){
        return ResponseEntity.ok(userLevelServiceImp.increaseUserLevel(levelCreateDto));
    }


}
