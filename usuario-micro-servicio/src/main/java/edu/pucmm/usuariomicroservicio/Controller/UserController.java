package edu.pucmm.usuariomicroservicio.Controller;

import javax.servlet.http.HttpServletRequest;


import edu.pucmm.usuariomicroservicio.Classes.User;
import edu.pucmm.usuariomicroservicio.Services.UserServices;
import edu.pucmm.usuariomicroservicio.Utils.JWTUtils;
import  edu.pucmm.usuariomicroservicio.Utils.Parser;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired UserServices us;
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

    @RequestMapping("/welcome")
    public String welcoming(HttpServletRequest request){
        return "Soy el micro servicio de estudiante desde el puerto: " + request.getLocalPort();
    }

    @RequestMapping("/getUsers")
    public @ResponseBody List<User> getUsers(){
        return us.findAllUsers();
    }

    @RequestMapping("/validateUser")
    public @ResponseBody boolean validateUser(@RequestParam("username") String username, @RequestParam("password") String password){
        return us.validateUser(username, password);
    }

    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public ResponseEntity<String> createUser(@RequestBody User user){
        user.setPassword(DigestUtils.md5Hex(user.getPassword()));
        us.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/deleteUser", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteUser(@RequestParam("username") String username){
        us.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    @RequestMapping("/updateUser")
    public String editUser(@RequestParam("username") String username){
        us.editUser(us.findUser(username));
        return "";
    }


}