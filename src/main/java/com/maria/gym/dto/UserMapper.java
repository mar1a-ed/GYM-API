package com.maria.gym.dto;

import com.maria.gym.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserResponseDTO toDto(User user){
        return new UserResponseDTO(user.getId(), user.getEmail(), user.getRole());
    }

    public static List<UserResponseDTO> toListDto(List<User> users){

        List<UserResponseDTO> usersDto = new ArrayList<>();

        for(User user: users){
            usersDto.add(new UserResponseDTO(user.getId(), user.getEmail(), user.getRole()));
        }

        return usersDto;
    }
}
