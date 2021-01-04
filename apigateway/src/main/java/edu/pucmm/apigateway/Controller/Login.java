package edu.pucmm.apigateway.Controller;

import edu.pucmm.apigateway.DTO.LoginDTO;
import edu.pucmm.apigateway.DTO.UserResponseDTO;
import edu.pucmm.apigateway.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/login")
@RestController
public class Login {

    @Autowired
    private UserService userService;

    @PostMapping
    public UserResponseDTO Login(@RequestBody LoginDTO loginDTO) {
        return userService.signin(loginDTO.getUsername(), loginDTO.getPassword());
    }

}
