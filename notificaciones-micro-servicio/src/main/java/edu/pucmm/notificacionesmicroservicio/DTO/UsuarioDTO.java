package edu.pucmm.notificacionesmicroservicio.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsuarioDTO {
    private String username;
    private String name;
    private String password;
    private String email;
    private String role;
}
