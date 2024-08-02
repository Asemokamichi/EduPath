package com.asemokamichi.kz.edupath.entity;

import com.asemokamichi.kz.edupath.Enum.Role;
import com.asemokamichi.kz.edupath.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static jakarta.persistence.EnumType.STRING;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Enumerated(STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Course> courses;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Enrollment> enrollments;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Progress> progress;

    public User(UserDTO userDTO) {
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.email = userDTO.getEmail();
        this.role = Role.valueOf(userDTO.getRole().toUpperCase());
    }

    public User(Long id, String username, String password, String email, Role role) {
//        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
