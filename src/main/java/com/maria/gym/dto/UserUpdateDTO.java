package com.maria.gym.dto;

import com.maria.gym.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdateDTO {

    @Email
    private String email;

    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long.")
    private String currentPassword;

    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long.")
    private String newPassword;

    private Role role;
}
