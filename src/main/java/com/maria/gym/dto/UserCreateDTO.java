package com.maria.gym.dto;

import com.maria.gym.model.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserCreateDTO {

    @NotEmpty
    @Email(message = "Insert a valid email '@'")
    private String email;

    @NotEmpty
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long.")
    private String password;
    
    private Role role = Role.ROLE_CLIENT;
}
