package com.maria.gym.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserUpdatePasswordDTO {

    @NotEmpty
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long.")
    private String currentPassword;

    @NotEmpty
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long.")
    private String newPassword;
}
