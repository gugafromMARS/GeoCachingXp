package gsc.projects.usersmcs.controller;

import gsc.projects.usersmcs.dto.UserCreateDto;
import gsc.projects.usersmcs.dto.UserUpdateDto;
import gsc.projects.usersmcs.service.UserServiceImp;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private final UserServiceImp userServiceImp;


    @GetMapping("/{userId}")
    public ResponseEntity<?> getUser(@PathVariable ("userId") Long userId){
        return ResponseEntity.ok(userServiceImp.getUserById(userId));
    }

    @GetMapping
    public ResponseEntity<?> getUsers(){
        return ResponseEntity.ok(userServiceImp.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody UserCreateDto userCreateDto){
        return ResponseEntity.ok(userServiceImp.createUser(userCreateDto));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> delete(@PathVariable ("userId") Long userId){
        userServiceImp.deleteUserById(userId);
        return new ResponseEntity<>("User deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable ("userId") Long userId, @RequestBody UserUpdateDto userUpdateDto){
        return new ResponseEntity<>(userServiceImp.updateUserById(userId, userUpdateDto), HttpStatus.CREATED);
    }
}

