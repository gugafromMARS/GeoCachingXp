package gsc.projects.levelingmcs.service;


import gsc.projects.levelingmcs.dto.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(url = "http://localhost:8080" , value = "USERS-SERVICE")
public interface APIUser {

    @GetMapping("/user/{userEmail}")
    UserDto getUser(@PathVariable("userEmail") String userEmail);
}
