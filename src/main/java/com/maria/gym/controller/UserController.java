package com.maria.gym.controller;

import com.maria.gym.dto.UserCreateDTO;
import com.maria.gym.dto.UserMapper;
import com.maria.gym.dto.UserResponseDTO;
import com.maria.gym.dto.UserUpdateDTO;
import com.maria.gym.model.User;
import com.maria.gym.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/public/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> findById(@PathVariable(value = "id") Long id){
        Optional<User> user = Optional.ofNullable(userService.findById(id));

        if(!user.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserResponseDTO userResponseDTO = UserMapper.toDto(user.get());

        userResponseDTO.add(linkTo(methodOn(UserController.class).findAll()).withRel("List of users."));

        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> findAll(){
        List<User> users = userService.findAll();

        if(users.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<UserResponseDTO> userResponseDTOS = UserMapper.toListDto(users);

        for(UserResponseDTO user: userResponseDTOS){
            Long id = user.getId();
            user.add(linkTo(methodOn(UserController.class).findById(id)).withSelfRel());
        }

        return new ResponseEntity<>(userResponseDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(@RequestBody @Valid UserCreateDTO userCreateDTO){
        User user = userService.createUser(userCreateDTO);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        UserResponseDTO userResponseDTO = UserMapper.toDto(user);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable(value = "id") Long id, @RequestBody @Valid UserUpdateDTO data){
        User user = userService.updateUser(id, data);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        UserResponseDTO userResponseDTO = UserMapper.toDto(user);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(value = "id") Long id){
        User user = userService.findById(id);

        if(user == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}

























