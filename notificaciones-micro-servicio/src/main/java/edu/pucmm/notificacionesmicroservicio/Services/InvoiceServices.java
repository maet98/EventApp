package edu.pucmm.notificacionesmicroservicio.Services;

import edu.pucmm.notificacionesmicroservicio.Classes.Invoice;
import edu.pucmm.notificacionesmicroservicio.Repositories.FacturaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceServices {
    @Autowired
    FacturaRepositories facturaRepositories;

    public List<Invoice> getFacturasByUsername(String username){
        return facturaRepositories.findAllByUsername(username);
    }

    @Transactional
    public boolean createInvoice(Invoice invoice){
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
