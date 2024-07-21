package com.goura.user.service.repository;

import com.goura.user.service.dto.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserDTO, String> {

    @Query("SELECT u FROM UserDTO u WHERE u.email = :email")
    Optional<UserDTO> findByEmail(String email);
}
