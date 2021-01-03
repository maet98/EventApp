package edu.pucmm.notificacionesmicroservicio.Repositories;

import edu.pucmm.notificacionesmicroservicio.Classes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,String> {

}
