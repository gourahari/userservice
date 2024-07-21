package com.goura.user.service.entitiy;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import static jakarta.validation.constraints.Pattern.Flag.CASE_INSENSITIVE;

@Data
public class User {
    @Id
    @Column(name = "id")
    private String userId;

    @NotBlank(message = "Name is mandatory.")
    private String name;

    @NotBlank(message = "Email is mandatory.")
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$",
            message = "Email is invalid.")
    private String email;

    @NotBlank(message = "Password is mandatory.")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters.")
    private String password;
}
