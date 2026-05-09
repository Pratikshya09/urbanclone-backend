package com.urbanclone.urbanclonebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.urbanclone.urbanclonebackend.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
public class User {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter
    @Getter
    @NotBlank
    private String name;

    @Setter
    @Getter
    @Email
    @Column(unique = true)
    private String email;

    @Setter
    @Getter
    @NotBlank
    @JsonIgnore
    private String password;

    @Setter
    @Enumerated(EnumType.STRING)
    private Role role;

    // Getters & Setters
    public Role getRole() {
        return role;
    }

}