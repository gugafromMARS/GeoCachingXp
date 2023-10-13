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


    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable ("userId") Long userId){
        return ResponseEntity.ok(userService.getUserById(userId));
    }

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

