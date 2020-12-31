package edu.pucmm.notificacionesmicroservicio.Repositories;


import edu.pucmm.notificacionesmicroservicio.Classes.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacturaRepositories extends JpaRepository<Invoice, Long> {
    List<Invoice> findAllByUsername(String username);

    Invoice findById(long id);
}
