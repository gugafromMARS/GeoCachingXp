package gsc.projects.registermcs.service;


import gsc.projects.registermcs.dto.UserLevelDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "LEVELING-SERVICE")
public interface APIUserLevel {


    @GetMapping("/level/user/{userEmail}")
    UserLevelDto getUserLevel(@PathVariable ("userEmail") String userEmail);
}
