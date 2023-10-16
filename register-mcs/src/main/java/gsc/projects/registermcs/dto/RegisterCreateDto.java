package gsc.projects.registermcs.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


@Getter
@Setter
public class RegisterCreateDto {

    private LocalDate localdate;
    private String userEmail;
    private String cacheCode;
}
