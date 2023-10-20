package gsc.projects.levelingmcs.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CacheLevelingDto {

    private Long id;

    private double experienceEarned;

    public CacheLevelingDto(double experienceEarned) {
        this.experienceEarned = experienceEarned;
    }
}
