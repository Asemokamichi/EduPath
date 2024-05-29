package com.asemokamichi.kz.edupath.controller;

import com.asemokamichi.kz.edupath.dto.UserDTO;
import com.asemokamichi.kz.edupath.entity.User;
import com.asemokamichi.kz.edupath.exeption.UserAlreadyExists;
import com.asemokamichi.kz.edupath.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        if (userService.existsByUsername(userDTO.getUsername())) {
            throw new UserAlreadyExists("Пользователь с именем " + userDTO.getUsername() + " уже существует.");
        }

        User user = userService.createUser(userDTO);
        return ResponseEntity.ok(new UserDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user = userService.findById(id);

//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь не найден...");
//        }

        return ResponseEntity.ok(new UserDTO(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = userService.updateUser(id, userDTO);

//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь не найден...");
//        }

        return ResponseEntity.ok(new UserDTO(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userService.findById(id);

//        if (user == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Пользователь не найден...");
//        }

        userService.deleteUser(user);

        return ResponseEntity.ok(String.format("Пользователь %s удален", user.getUsername()));
    }
}
