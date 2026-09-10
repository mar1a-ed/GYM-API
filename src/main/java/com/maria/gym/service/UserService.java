package com.maria.gym.service;

import com.maria.gym.dto.UserCreateDTO;
import com.maria.gym.dto.UserUpdateDTO;
import com.maria.gym.dto.UserUpdatePasswordDTO;
import com.maria.gym.exception.EmailAlreadyExistsException;
import com.maria.gym.exception.ResourceNotFoundException;
import com.maria.gym.model.Role;
import com.maria.gym.model.User;
import com.maria.gym.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    public User findById(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found.")
        );

        return user;
    }

    @Transactional
    public List<User> findAll(){
        List<User> users = userRepository.findAll();

        if(users.isEmpty()){
            throw new ResourceNotFoundException("Users not found.");
        }

        return users;
    }

    @Transactional
    public User createUser(UserCreateDTO userCreateDTO){
        User user = new User();

        user.setEmail(userCreateDTO.getEmail());
        user.setPassword(passwordEncoder.encode(userCreateDTO.getPassword()));

        if(userCreateDTO.getRole() == null){
            user.setRole(Role.ROLE_CLIENT);
        }else{
            user.setRole(userCreateDTO.getRole());
        }

        if(userRepository.existsByEmail(userCreateDTO.getEmail())){
            throw new EmailAlreadyExistsException("Email already exists.");
        }

        userRepository.save(user);

        return user;
    }

    @Transactional
    public User updateUser(Long id, UserUpdateDTO data){
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found.")
        );

        if(data.getEmail() == null){
            user.setEmail(user.getEmail());
        }else{
            user.setEmail(data.getEmail());
        }

        if(data.getCurrentPassword() == null && data.getNewPassword() == null){
            user.setPassword(user.getPassword());
        }else{
            user.setPassword(passwordEncoder.encode(updatePassword(id, new UserUpdatePasswordDTO(data.getCurrentPassword(), data.getNewPassword()))));
        }

        if(data.getRole() == null){
            user.setRole(user.getRole());
        }else{
            user.setRole(data.getRole());
        }

        userRepository.save(user);

        return user;
    }

    @Transactional
    public String updatePassword(Long id, UserUpdatePasswordDTO userUpdatePasswordDTO){
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found.")
        );

        if(!userUpdatePasswordDTO.getCurrentPassword().equals(user.getPassword())){
            throw new IllegalArgumentException("Password does not match with the current password.");
        }

        return userUpdatePasswordDTO.getNewPassword();
    }

    @Transactional
    public void deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User not found.")
        );

        userRepository.delete(user);
    }
}



















