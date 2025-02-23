package com.goura.user.service.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserDTO {
    @Id
    @Column(name = "id")
    private String userId;
    private String name;
    private String email;
    private String password;
}
