package gsc.projects.cachesmcs.dto;


import gsc.projects.cachesmcs.model.CacheLevel;
import gsc.projects.cachesmcs.model.DifficultyLevel;
import gsc.projects.cachesmcs.model.GroundLevel;
import gsc.projects.cachesmcs.model.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CacheDto {

    private Long id;

    private String address;

    private String description;

    private String tip;

    private DifficultyLevel difficulty;

    private GroundLevel groundLevel;

    private Size size;

    private CacheLevel cacheLevel;

    private double experienceEarned;
}
