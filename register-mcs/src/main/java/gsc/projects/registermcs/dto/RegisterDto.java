package gsc.projects.registermcs.dto;

import jakarta.persistence.Column;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
@Builder
public class RegisterDto {

    private Long id;
    private LocalDate localDate;
    private CacheRegisterDto cacheRegisterDto;
}
