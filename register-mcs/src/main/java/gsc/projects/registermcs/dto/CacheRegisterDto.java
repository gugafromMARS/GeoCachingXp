package gsc.projects.registermcs.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CacheRegisterDto {

    private Long id;

    private String address;

    private String description;

    private String tip;

    private int cacheLevel;

    private String cacheCode;

    private double experienceEarned;
}
