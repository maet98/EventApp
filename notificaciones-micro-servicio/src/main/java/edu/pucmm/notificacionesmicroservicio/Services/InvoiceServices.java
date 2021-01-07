package edu.pucmm.notificacionesmicroservicio.Services;

import edu.pucmm.notificacionesmicroservicio.Classes.Invoice;
import edu.pucmm.notificacionesmicroservicio.DTO.CompraDTO;
import edu.pucmm.notificacionesmicroservicio.DTO.EmpleadosDTO;
import edu.pucmm.notificacionesmicroservicio.Repositories.FacturaRepositories;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServices {
    @Autowired
    FacturaRepositories facturaRepositories;

    @Autowired
    private EmailServices es;

    @Autowired
    private ReportService reportService;

    @Autowired
    private RestTemplate restTemplate;

    public List<Invoice> getFacturasByUsername(String username){
        return facturaRepositories.findAllByUsername(username);
    }

    @Transactional
    public boolean createInvoice(CompraDTO compraDTO) throws FileNotFoundException, JRException {
        Invoice invoice = new Invoice(compraDTO.getUsuario(), compraDTO.getPlan(), compraDTO.getTotal());
        var invoice1 = facturaRepositories.save(invoice);
        var out = this.reportService.exportReport(compraDTO,"pdf", Math.toIntExact(compraDTO.getId()));
        es.sendEmailAttachment(compraDTO.getEmail(), "Gracias por realizar una compra!", "Resumen de la compra.", out);
        sendCorreoCompra(out);
        return true;
    }

    @Transactional
    public boolean deleteFactura(long id){
        Invoice invoice = facturaRepositories.findById(id);
        if(invoice != null){
            facturaRepositories.delete(invoice);
            return true;
        }
        return false;
    }

    @Transactional
    public void sendCorreoCompra(byte[] out) {
        ResponseEntity<List<String>> response =  restTemplate.
                exchange("http://USUARIOS-SERVICIOS/usuarios/employees",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<String>>() {
                        }
                );
        if(response != null && response.hasBody()){
            System.out.println(response.getBody());
            this.es.sendMasssive(response.getBody(),"Asignacion de Evento", "Se ha realizado una compra.",out);
        }
    }

}
