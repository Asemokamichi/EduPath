package com.asemokamichi.kz.edupath.service;

import com.asemokamichi.kz.edupath.dto.UserDTO;
import com.asemokamichi.kz.edupath.entity.User;
import com.asemokamichi.kz.edupath.exceptions.InvalidRequest;
import com.asemokamichi.kz.edupath.exceptions.ResourceNotFoundException;
import com.asemokamichi.kz.edupath.exceptions.UserAlreadyExists;
import com.asemokamichi.kz.edupath.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }

    private final static String invalidRequest = "username, password, email, and role";
    private final static String resourceNotFound = "user";

    @Transactional
    public User createUser(UserDTO userDTO){
        if (!userDTO.checkValidation()){
            throw new InvalidRequest(invalidRequest);
        }
        if (userRepository.existsByUsernameOrEmail(userDTO.getUsername(), userDTO.getEmail())) {
            throw new UserAlreadyExists(userDTO.getUsername());
        }

        User user = new User(userDTO);

        return userRepository.save(user);
    }

    @Transactional
    public User findById(Long id){
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(resourceNotFound));

    }

    @Transactional
    public User updateUser(Long id, UserDTO userDTO){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(resourceNotFound));

        if (userDTO.getUsername()!=null && !userDTO.getUsername().isBlank()) user.setUsername(userDTO.getUsername());
        if (userDTO.getPassword()!=null && !userDTO.getPassword().isBlank()) user.setPassword(userDTO.getPassword());
        if (userDTO.getEmail()!=null && !userDTO.getEmail().isBlank()) user.setEmail(userDTO.getEmail());

        return userRepository.save(user);
    }

    @Transactional
    public String deleteUser(Long id){
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(resourceNotFound));

        userRepository.delete(user);

        return user.getUsername();
    }
}
