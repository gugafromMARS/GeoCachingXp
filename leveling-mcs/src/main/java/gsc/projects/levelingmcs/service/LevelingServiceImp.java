package gsc.projects.levelingmcs.service;


import gsc.projects.levelingmcs.converter.LevelCalculator;
import gsc.projects.levelingmcs.repository.LevelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class LevelingServiceImp {

    private final LevelRepository levelRepository;

    private final LevelCalculator levelCalculator;

}
