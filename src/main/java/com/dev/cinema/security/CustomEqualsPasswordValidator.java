package com.dev.cinema.security;

import com.dev.cinema.model.dto.request.UserRegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CustomEqualsPasswordValidator
        implements ConstraintValidator<PasswordEqualsValidator, UserRegistrationDto> {
    @Override
    public boolean isValid(UserRegistrationDto userRegistrationDto,
                           ConstraintValidatorContext constraintValidatorContext) {
        return userRegistrationDto.getPassword()
                .equals(userRegistrationDto.getRepeatPassword());
    }
}
