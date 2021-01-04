package edu.pucmm.apigateway.Entity;


import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @Column(length = 64)
    private String username;
    @Column(length = 100)
    private String name;
    @Column(length = 2000)
    private String password;
    @Column(length = 2000)
    private String email;

    private String role;

    public User(){}

    public User(String username, String name, String password, String email, String role) {
        this.username = username;
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String mail) {
        this.email = mail;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
