package com.asemokamichi.kz.edupath.service;

import com.asemokamichi.kz.edupath.dto.UserDTO;
import com.asemokamichi.kz.edupath.entity.User;
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

    @Transactional
    public boolean existsByUsername(String username){
        return userRepository.existsByUsername(username);
    }

    @Transactional
    public User createUser(UserDTO userDTO){
        User user = new User(userDTO);

        userRepository.save(user);

        return user;
    }

    @Transactional
    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }

    @Transactional
    public User updateUser(Long id, UserDTO userDTO){
        User user = userRepository.findById(id).orElse(null);
        if (user == null) return null;

        if (userDTO.getUsername()!=null && !userDTO.getUsername().isBlank()) user.setUsername(userDTO.getUsername());
        if (userDTO.getPassword()!=null && !userDTO.getPassword().isBlank()) user.setPassword(userDTO.getPassword());
        if (userDTO.getEmail()!=null && !userDTO.getEmail().isBlank()) user.setEmail(userDTO.getEmail());

        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(User user){
        userRepository.delete(user);
    }
}
