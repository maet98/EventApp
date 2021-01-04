package edu.pucmm.microserviciousuario.Controller;

import javax.servlet.http.HttpServletRequest;
import edu.pucmm.microserviciousuario.Classes.User;
import edu.pucmm.microserviciousuario.Services.UserServices;
import edu.pucmm.microserviciousuario.Utils.JWTUtils;
import  edu.pucmm.microserviciousuario.Utils.Parser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UserController {
    @Autowired
    private UserServices us;
    @Autowired
    private Parser parser;
    @Autowired
    private JWTUtils jwtUtils;

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        if (!us.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No user has this email!");
        }
        User user = us.findByEmail(email);
        if (!us.validateUser(user.getUsername(),password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad credentials!");
        }
        return jwtUtils.createJWT(UUID.randomUUID().toString(), "ISC517 Final", "Access Token", email);
    }

    @GetMapping("/welcome")
    public String welcoming(HttpServletRequest request){
        return "Soy el micro servicio de estudiante desde el puerto: " + request.getLocalPort();
    }

    @GetMapping("/employees")
    public List<User> getAllEmployees() {
        return this.us.findEmployee();
    }

    @GetMapping
    public @ResponseBody List<User> getUsers(){
        return us.findAllUsers();
    }

    @GetMapping("/validateUser")
    public @ResponseBody boolean validateUser(@RequestParam("username") String username, @RequestParam("password") String password){
        return us.validateUser(username, password);
    }

    @PostMapping(value = "/createUser")
    public User createUser(@RequestBody User user) throws Exception {
        return us.createUser(user);
    }

    @DeleteMapping(value = "/deleteUser")
    public ResponseEntity<String> deleteUser(@RequestParam("username") String username){
        us.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    @PutMapping("/updateUser")
    public String editUser(@RequestParam("username") String username){
        us.editUser(us.findUser(username));
        return "";
    }
}
