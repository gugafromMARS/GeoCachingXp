package gsc.projects.usersmcs.service;


import gsc.projects.usersmcs.dto.UserLevelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "LEVELING-SERVICE")
public interface APILevel {

    @GetMapping("/level/user/{userEmail}")
    UserLevelDto getUserLevel(@PathVariable("userEmail") String userEmail);
}
