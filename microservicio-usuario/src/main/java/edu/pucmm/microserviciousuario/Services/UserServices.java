package edu.pucmm.microserviciousuario.Services;

import edu.pucmm.microserviciousuario.Classes.User;
import edu.pucmm.microserviciousuario.Repository.UserRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
@RefreshScope
public class UserServices {
    @Autowired UserRepository ur;

    @Autowired
    private RestTemplate template;

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

    public List<String> findEmployee() {
        return ur.getbyRole("ROLE_EMPLEADO");
    }

    public void createAdmin() throws Exception {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String username = "admin";
        String password = bCryptPasswordEncoder.encode("admin");
        String email = "admin@admin.com";
        String role = "ROLE_ADMIN";
        if(ur.count() == 0) ur.save(new User(username, password, email, role));
    }

    public boolean validateUser(String username, String password){
        User user = findUser(username);
        if(user != null){
            return user.getPassword().equals(DigestUtils.md5Hex(password));
        }
        return false;
    }

    @Transactional
    public User createUser(User user) throws Exception {
        if(ur.findByUsername(user.getUsername()) != null){
            throw new Exception("User already created");
        }
        try {

            User result = template.postForObject("http://NOTIFICACION-SERVICIO/sendAccountNotification", user, User.class);
        } catch (Exception er) {

        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return ur.save(user);
    }

    @Transactional
    public boolean editUser(User user){
        User newUser = new User();
        if(user.getUsername() != null) {
            newUser.setUsername(user.getUsername());
        }
        if(user.getEmail() != null){

            newUser.setEmail(user.getEmail());
        }
        if(!user.getPassword().isEmpty()){
            BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            newUser.setPassword(user.getPassword());
        }
        newUser.setRole(user.getRole());
        this.ur.save(newUser);
        return true;
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
