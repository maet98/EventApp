package edu.pucmm.comprasmicroservicio.Service;

import edu.pucmm.comprasmicroservicio.Repository.ComprasRepository;
import edu.pucmm.comprasmicroservicio.entity.Compras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComprasService {

    @Autowired
    private ComprasRepository comprasRepository;

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
