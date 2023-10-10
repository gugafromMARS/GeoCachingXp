package gsc.projects.cachesmcs.dto;

import gsc.projects.cachesmcs.model.CacheLevel;
import gsc.projects.cachesmcs.model.DifficultyLevel;
import gsc.projects.cachesmcs.model.GroundLevel;
import gsc.projects.cachesmcs.model.Size;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;

@Getter
public class CacheCreateDto {

    private String description;

    private String tip;

    @Enumerated(value = EnumType.STRING)
    private DifficultyLevel difficulty;

    @Enumerated(value = EnumType.STRING)
    private GroundLevel groundLevel;

    @Enumerated(value = EnumType.STRING)
    private Size size;

    @Enumerated(value = EnumType.STRING)
    private CacheLevel cacheLevel;
}
