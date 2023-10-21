package gsc.projects.registermcs.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    private Long id;
    private LocalDate localDate;
    private CacheRegisterDto cacheRegisterDto;
    private double userLevel;
}
