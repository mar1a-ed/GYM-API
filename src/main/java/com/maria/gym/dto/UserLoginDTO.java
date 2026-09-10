package com.maria.gym.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginDTO {

    @NotBlank
    @Email(message = "Insert a valid email '@'")
    private String email;

    @NotBlank
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long")
    private String password;
}
