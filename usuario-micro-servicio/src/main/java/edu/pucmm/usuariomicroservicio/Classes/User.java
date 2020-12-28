package edu.pucmm.usuariomicroservicio.Classes;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @Column(length = 64)
    private String username;
    @Column(length = 2000)
    private String password;
    @Column(length = 2000)
    private String email;
    // @ManyToMany(fetch = FetchType.EAGER)
    private String role;

    public User(){}

    public User(String username, String password, String email, String role){
        this.username = username;
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
