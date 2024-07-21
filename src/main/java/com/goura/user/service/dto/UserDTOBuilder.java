package com.goura.user.service.dto;

import com.goura.user.service.entitiy.User;

import java.util.UUID;

public class UserDTOBuilder {

    private String userId;
    private String name;
    private String email;
    private String password;

    private UserDTOBuilder() {
    }

    public static UserDTOBuilder get() {
        return new UserDTOBuilder();
    }

    public UserDTOBuilder setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public UserDTOBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public UserDTOBuilder setEmail(String email) {
        this.email = email;
        return this;
    }

    public UserDTOBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserDTO build() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(this.userId);
        userDTO.setName(this.name);
        userDTO.setEmail(this.email);
        userDTO.setPassword(this.password);
        return userDTO;
    }

    public static User from(UserDTO userDTO) {
        User user = new User();
        user.setUserId(userDTO.getUserId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        return user;
    }

    public static UserDTO from(User user) {
        UUID uuid = UUID.randomUUID();
        String userId = user.getUserId();
        if (userId == null ) {
            userId = String.format("%.8s%.8s",
                            Math.abs(uuid.getMostSignificantBits()),
                            Math.abs(uuid.getLeastSignificantBits()));
        }
        return UserDTOBuilder.get()
                .setUserId(userId)
                .setName(user.getName())
                .setEmail(user.getEmail())
                .setPassword(user.getPassword())
                .build();
    }
}