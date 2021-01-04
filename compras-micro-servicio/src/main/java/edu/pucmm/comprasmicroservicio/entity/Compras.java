package edu.pucmm.comprasmicroservicio.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Compras {
    @GeneratedValue
    @Id
    private Long id;
    private String usuario;
    private String email;
    private LocalDate fechaEvento;
    private LocalDate fechaCompra;

    @ManyToMany
    List<Product> products;
    private Integer total;
}
