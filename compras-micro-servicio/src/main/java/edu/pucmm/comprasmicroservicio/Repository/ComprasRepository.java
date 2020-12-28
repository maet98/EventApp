package edu.pucmm.comprasmicroservicio.Repository;

import edu.pucmm.comprasmicroservicio.entity.Compras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ComprasRepository extends JpaRepository<Compras, Long> {
    public List<Compras> findByUsuario(String usuario);
}
