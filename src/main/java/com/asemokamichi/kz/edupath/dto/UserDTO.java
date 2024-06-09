package com.asemokamichi.kz.edupath.dto;

import com.asemokamichi.kz.edupath.Enum.Role;
import com.asemokamichi.kz.edupath.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
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

    public boolean checkValidation() {
        try {
            Role.valueOf(role);
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
        return username != null && password != null && email != null;
    }
}
