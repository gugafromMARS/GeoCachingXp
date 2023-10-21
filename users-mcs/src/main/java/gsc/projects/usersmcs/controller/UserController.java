package gsc.projects.usersmcs.controller;

import gsc.projects.usersmcs.dto.UserCreateDto;
import gsc.projects.usersmcs.dto.UserUpdateDto;
import gsc.projects.usersmcs.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping("/{userEmail}")
    public ResponseEntity<?> getUser(@PathVariable ("userEmail") String userEmail){
        return ResponseEntity.ok(userService.getUserByEmail(userEmail));
    }

//    @GetMapping("/{userEmail}/level")
//    public ResponseEntity<?> getLevel(@PathVariable ("userEmail") String userEmail) {
//        return ResponseEntity.ok(userService.getUserLevel(userEmail));
//    }
    @GetMapping
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserCreateDto userCreateDto){
        return new ResponseEntity<>(userService.createUser(userCreateDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable ("userId") Long userId){
        userService.deleteUserById(userId);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable ("userId") Long userId, @RequestBody UserUpdateDto userUpdateDto){
        return ResponseEntity.ok(userService.updateUserById(userId, userUpdateDto));
    }
}

