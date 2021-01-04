package edu.pucmm.notificacionesmicroservicio.Controllers;


import edu.pucmm.notificacionesmicroservicio.DTO.CompraDTO;
import edu.pucmm.notificacionesmicroservicio.DTO.UsuarioDTO;
import edu.pucmm.notificacionesmicroservicio.Services.EmailServices;
import edu.pucmm.notificacionesmicroservicio.Services.InvoiceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class NotificationsController {
    @Autowired
    InvoiceServices is;
    @Autowired
    EmailServices es;

    @RequestMapping("/hw")
    public String helloWorld(){ return "Hello World!"; }

    @PostMapping("/createInvoice")
    public CompraDTO createInvoice(@RequestBody CompraDTO compraDTO){
        System.out.println(compraDTO.getTotal());
        is.createInvoice(compraDTO.getUsuario(), compraDTO.getProducts(), compraDTO.getTotal());
        //is.sendCorreoCompra(compraDTO);
        es.sendEmail(compraDTO.getEmail(), "Gracias por realizar una compra!", "RESUMEN_DE_LA_COMPRA");
        return compraDTO;
    }

    @PostMapping("/sendAccountNotification")
    public UsuarioDTO sendAccountNotification(@RequestBody UsuarioDTO usuarioDTO){
        es.sendEmail(usuarioDTO.getEmail(), "Nueva cuenta!", "Aqui el reporte de su cuenta, nombre de usuario es " + usuarioDTO.getUsername() + " y la contraseña es " + usuarioDTO.getPassword());
        return usuarioDTO;
    }

}
