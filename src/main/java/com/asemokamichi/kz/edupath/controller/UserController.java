package com.asemokamichi.kz.edupath.controller;

import com.asemokamichi.kz.edupath.dto.UserDTO;
import com.asemokamichi.kz.edupath.entity.User;
import com.asemokamichi.kz.edupath.kafka.producer.UserProducer;
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

    @Autowired
    private UserProducer kafkaProducer;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {
        User user = userService.createUser(userDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserDTO(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        User user = userService.findById(id);

        String message = "Данные о пользователе: " + user + " успешно получен";
        kafkaProducer.sendMessage(message);

        return ResponseEntity.ok(new UserDTO(user));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        User user = userService.updateUser(id, userDTO);

        return ResponseEntity.ok(new UserDTO(user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        String username = userService.deleteUser(id);

        return ResponseEntity.ok(String.format("Пользователь %s удален", username));
    }
}
