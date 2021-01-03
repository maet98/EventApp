package edu.pucmm.notificacionesmicroservicio.Services;

import edu.pucmm.notificacionesmicroservicio.Classes.Invoice;
import edu.pucmm.notificacionesmicroservicio.Classes.Product;
import edu.pucmm.notificacionesmicroservicio.DTO.CompraDTO;
import edu.pucmm.notificacionesmicroservicio.DTO.EmpleadosDTO;
import edu.pucmm.notificacionesmicroservicio.Repositories.FacturaRepositories;
import edu.pucmm.notificacionesmicroservicio.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServices {
    @Autowired
    FacturaRepositories facturaRepositories;

    @Autowired
    private EmailServices es;

    @Autowired
    ProductRepository pr;

    @Autowired
    private RestTemplate restTemplate;

    public List<Invoice> getFacturasByUsername(String username){
        return facturaRepositories.findAllByUsername(username);
    }

    @Transactional
    public boolean createInvoice(String username, ArrayList<String> products, Double Total){
        ArrayList<Product> list = new ArrayList<>();
        for(String ac: products) {
            Product product = new Product();
            product.setName(ac);
            list.add(pr.save(product));
        }
        Invoice invoice = new Invoice(username, list, Total);
        if(facturaRepositories.findById(invoice.getId()) != null){
            return false;
        }
        facturaRepositories.save(invoice);
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
    public String sendCorreoCompra(CompraDTO compraDTO) {
        EmpleadosDTO empleadosDTO = new EmpleadosDTO();
        restTemplate.postForObject("http://USUARIO-SERVICIOS/getEmpleados", empleadosDTO, EmpleadosDTO.class);
        for(String correo: empleadosDTO.getCorreos()){
            es.sendEmail(correo, "Compra realizada", "El Cliente " + compraDTO.getUsuario() + " ha realizado una compra para " + compraDTO.getPlan() + " para el dia " + compraDTO.getFechaCompra() );
        }
        return "Correos enviados!";
    }

    @Transactional
    public boolean editFactura(Invoice invoice){
        Optional<Invoice> e = Optional.ofNullable(facturaRepositories.findById(invoice.getId()));
        if(e.isPresent()){
            Invoice newInvoice = e.get();
            newInvoice.setUsername(invoice.getUsername());
            newInvoice.setProducts(invoice.getProducts());
            newInvoice.setTotal(invoice.getTotal());
            return true;
        }
        return false;
    }
}
