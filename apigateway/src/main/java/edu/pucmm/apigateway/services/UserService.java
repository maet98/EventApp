package edu.pucmm.apigateway.services;

import edu.pucmm.apigateway.DTO.UserResponseDTO;
import edu.pucmm.apigateway.Entity.User;
import edu.pucmm.apigateway.UserRepository;
import edu.pucmm.apigateway.config.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticate;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;


    public UserResponseDTO signin(String username, String password) {
        authenticate.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUser(this.userRepository.findByUsername(username));
        userResponseDTO.setToken(jwtTokenProvider.createToken(username, userRepository.findByUsername(username).getRole()));
        return userResponseDTO;
    }
    public void createAdmin() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        User admin = new User();
        admin.setEmail("admin@admin.com");
        admin.setPassword(bCryptPasswordEncoder.encode("admin"));
        admin.setRole("ROLE_ADMIN");
        admin.setUsername("admin");
        this.userRepository.save(admin);
    }

}
