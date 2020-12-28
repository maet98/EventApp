package edu.pucmm.usuariomicroservicio.Services;

import edu.pucmm.usuariomicroservicio.Classes.User;
import edu.pucmm.usuariomicroservicio.Repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UserServices {
    @Autowired UserRepository ur;

    public User findUser(String username){
        return ur.findByUsername(username);
    }

    public List<User> findAllUsers() { return ur.findAll(); }

    public boolean existsByEmail(String email) {
        return ur.existsByEmail(email) == 1;
    }

    public User findByEmail(String email) {
        return ur.findByEmail(email);
    }

    public void createAdmin() {
        String username = "admin";
        String password = DigestUtils.md5Hex("admin");
        String email = "admin@admin.com";
        String role = "ADMIN";
        if(ur.count() == 0) createUser(new User(username, password, email, role));
    }

    public boolean validateUser(String username, String password){
        User user = findUser(username);
        if(user != null){
            return user.getPassword().equals(DigestUtils.md5Hex(password));
        }
        return false;
    }

    @Transactional
    public boolean createUser(User user){
        if(ur.findByUsername(user.getUsername()) != null){
            return false;
        }
        ur.save(user);
        return true;
    }

    @Transactional
    public boolean editUser(User user){
        Optional<User> e = Optional.ofNullable(ur.findByUsername(user.getUsername()));
        if(e.isPresent()){
            User newUser = e.get();
            newUser.setUsername(user.getUsername());
            newUser.setEmail(user.getEmail());
            newUser.setPassword(user.getPassword());
            newUser.setRole(user.getRole());
            return true;
        }
        return false;
    }

    @Transactional
    public boolean deleteUser(String username){
        User user = ur.findByUsername(username);
        if(user != null){
            ur.delete(user);
            return true;
        }
        return false;
    }
}
