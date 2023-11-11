package com.example.mobilele.model.dto;

import com.example.mobilele.model.validation.FieldMatch;
import com.example.mobilele.model.validation.UniqueUserEmail;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
@FieldMatch(
    first = "password",
    second = "confirmPassword",
    message = "Passwords should match"
)
public record UserRegistrationDTO(@NotEmpty String firstName,
                                  @NotEmpty String lastName,
                                  @NotNull @Email @UniqueUserEmail String email,
                                  String password,
                                  String confirmPassword) {

    public String fullName() {
        return firstName + " " + lastName;
    }
}
