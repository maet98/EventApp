package edu.pucmm.comprasmicroservicio.Repository;

import edu.pucmm.comprasmicroservicio.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String> {
}
