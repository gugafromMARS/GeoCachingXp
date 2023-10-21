package gsc.projects.usersmcs.service;


import gsc.projects.usersmcs.dto.UserLevelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "REGISTER-SERVICE")
public interface APILevel {

    @GetMapping("/register/level/{userEmail}")
    UserLevelDto getLevel(@PathVariable ("userEmail") String userEmail);
}
