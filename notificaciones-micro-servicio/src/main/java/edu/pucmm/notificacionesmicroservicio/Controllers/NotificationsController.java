package edu.pucmm.notificacionesmicroservicio.Controllers;


import edu.pucmm.notificacionesmicroservicio.Classes.Invoice;
import edu.pucmm.notificacionesmicroservicio.Services.EmailServices;
import edu.pucmm.notificacionesmicroservicio.Services.InvoiceServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NotificationsController {
    @Autowired
    InvoiceServices is;
    @Autowired
    EmailServices es;

    @RequestMapping("/hw")
    public String helloWorld(){ return "Hello World!"; }

    @RequestMapping("/createInvoice")
    public String createInvoice(String username, String email, ArrayList<String> products, double total){
        is.createInvoice(new Invoice(username, products, total));
        es.sendEmail(email, "Gracias por realizar una compra!", "RESUMEN_DE_LA_COMPRA");
        return "compra realizada!";
    }

    @RequestMapping("/sendAccountNotification")
    public String sendAccountNotification(@RequestParam("email") String email){
        es.sendEmail(email, "Nueva cuenta!", "Aqui el reporte de su cuenta");
        return "";
    }

    @RequestMapping("/notifyAll")
    public String correoCompra(@RequestParam("empleados") List<String> empleados, @RequestParam("correos") List<String> correos)  {
        for(int i = 0; i < empleados.size(); i++){
            es.sendEmail(correos.get(i), "Compra realizada", "Usted realizo una compra!");
        }
        return "Correos enviados!";
    }
}
