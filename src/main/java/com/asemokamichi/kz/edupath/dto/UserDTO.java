package com.asemokamichi.kz.edupath.dto;

import com.asemokamichi.kz.edupath.entity.User;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private String email;
    private String role;

    public UserDTO(User user) {
        id = user.getId();
        username = user.getUsername();
        password = user.getPassword();
        email = user.getEmail();
        role = user.getRole().toString();
    }
}
