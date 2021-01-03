package edu.pucmm.comprasmicroservicio.Service;

import edu.pucmm.comprasmicroservicio.Repository.ComprasRepository;
import edu.pucmm.comprasmicroservicio.entity.Compras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RefreshScope
public class ComprasService {

    @Autowired
    private ComprasRepository comprasRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Compras saveCompra(Compras compras) {

        return this.comprasRepository.save(compras);
    }

    public List<Compras> getByUser(String usuario) {
        return this.comprasRepository.findByUsuario(usuario);
    }

    public List<Compras> getAll() {
        return this.comprasRepository.findAll();
    }
}
