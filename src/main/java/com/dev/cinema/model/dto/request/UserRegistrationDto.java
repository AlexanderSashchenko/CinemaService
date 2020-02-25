package com.dev.cinema.model.dto.request;

import com.dev.cinema.security.EmailValidator;
import com.dev.cinema.security.PasswordEqualsValidator;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@PasswordEqualsValidator
@Getter
@Setter
public class UserRegistrationDto {
    @NotEmpty(message = "Entered value must not be empty")
    @EmailValidator
    private String email;
    @NotEmpty(message = "Entered value must not be empty")
    private String password;
    @NotEmpty(message = "Entered value must not be empty")
    private String repeatPassword;
}
