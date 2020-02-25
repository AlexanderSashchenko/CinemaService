package com.dev.cinema.model.dto.request;

import com.dev.cinema.security.EmailValidator;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    @NotEmpty(message = "Entered value must not be empty")
    @EmailValidator
    private String email;
    @NotEmpty(message = "Entered value must not be empty")
    private  String password;
}
