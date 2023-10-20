package gsc.projects.levelingmcs.controller;


import gsc.projects.levelingmcs.dto.LevelCreateDto;
import gsc.projects.levelingmcs.service.UserLevelService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/level")
@AllArgsConstructor
public class UserLevelingController {

    private final UserLevelService userLevelService;

    @GetMapping("/user/{userEmail}")
    public ResponseEntity<?> getUserLevel(@PathVariable ("userEmail") String userEmail){
        return ResponseEntity.ok(userLevelService.getLevelByEmail(userEmail));
    }

    @PostMapping()
    public ResponseEntity<?> createLeveling(@RequestBody LevelCreateDto levelCreateDto){
        return new ResponseEntity<>(userLevelService.increaseUserLevel(levelCreateDto), HttpStatus.CREATED);
    }


}
