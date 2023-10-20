package gsc.projects.levelingmcs.service;

import gsc.projects.levelingmcs.dto.LevelCreateDto;
import gsc.projects.levelingmcs.dto.UserLevelDto;

public interface UserLevelService {
    UserLevelDto getLevelByEmail(String userEmail);

    UserLevelDto increaseUserLevel(LevelCreateDto levelCreateDto);
}
