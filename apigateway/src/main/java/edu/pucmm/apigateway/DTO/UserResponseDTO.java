package edu.pucmm.apigateway.DTO;

import edu.pucmm.apigateway.Entity.User;

public class UserResponseDTO {
    private User user;
    private String token;

    public UserResponseDTO() {
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
