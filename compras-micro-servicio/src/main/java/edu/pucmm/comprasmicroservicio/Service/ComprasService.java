package edu.pucmm.comprasmicroservicio.Service;

import edu.pucmm.comprasmicroservicio.Repository.ComprasRepository;
import edu.pucmm.comprasmicroservicio.Repository.ProductRepository;
import edu.pucmm.comprasmicroservicio.entity.Compras;
import edu.pucmm.comprasmicroservicio.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@RefreshScope
public class ComprasService {

    @Autowired
    private ComprasRepository comprasRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Compras saveCompra(Compras compras) {
        Compras result = restTemplate.postForObject("http://NOTIFICACION-SERVICIO/createInvoice", compras, Compras.class);
        compras.setProducts(productRepository.saveAll(compras.getProducts()));
        return this.comprasRepository.save(compras);
    }

    public List<Compras> getByUser(String usuario) {
        return this.comprasRepository.findByUsuario(usuario);
    }

    public List<Compras> getAll() {
        return this.comprasRepository.findAll();
    }
}
