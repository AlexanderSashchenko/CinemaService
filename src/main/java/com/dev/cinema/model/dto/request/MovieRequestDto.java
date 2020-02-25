package com.dev.cinema.model.dto.request;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieRequestDto {
    @NotEmpty(message = "Entered value must not be empty")
    private String title;
    @NotEmpty(message = "Entered value must not be empty")
    private String description;
}
