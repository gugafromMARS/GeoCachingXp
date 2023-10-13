package gsc.projects.cachesmcs.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class CacheLevelingDto {

    private Long id;

    private String cacheCode;

    private double experienceEarned;

}
