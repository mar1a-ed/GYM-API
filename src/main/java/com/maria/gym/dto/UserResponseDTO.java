package com.maria.gym.dto;

import com.maria.gym.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponseDTO extends RepresentationModel<UserResponseDTO> {

    private Long id;

    private String email;

    private Role role;
}
