package edu.pucmm.notificacionesmicroservicio.Controllers;


import edu.pucmm.notificacionesmicroservicio.DTO.CompraDTO;
import edu.pucmm.notificacionesmicroservicio.DTO.UsuarioDTO;
import edu.pucmm.notificacionesmicroservicio.Services.EmailServices;
import edu.pucmm.notificacionesmicroservicio.Services.InvoiceServices;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;

@RestController
public class NotificationsController {
    @Autowired
    InvoiceServices is;
    @Autowired
    EmailServices es;


    @Value("${NOTIFICACION}")
    public String hola;

    @RequestMapping("/hw")
    public String helloWorld(){ return "Hello World! " + hola; }

    @PostMapping("/createInvoice")
    public CompraDTO createInvoice(@RequestBody CompraDTO compraDTO) throws FileNotFoundException, JRException {
        System.out.println(compraDTO.getTotal());
        is.createInvoice(compraDTO);
        //is.sendCorreoCompra(compraDTO);
        return compraDTO;
    }

    @PostMapping("/sendAccountNotification")
    public UsuarioDTO sendAccountNotification(@RequestBody UsuarioDTO usuarioDTO){
        es.sendEmail(usuarioDTO.getEmail(), "Nueva cuenta!", "Aqui el reporte de su cuenta, nombre de usuario es " + usuarioDTO.getUsername() + " y la contraseña es " + usuarioDTO.getPassword());
        return usuarioDTO;
    }

}
