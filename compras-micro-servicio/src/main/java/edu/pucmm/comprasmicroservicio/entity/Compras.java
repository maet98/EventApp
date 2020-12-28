package edu.pucmm.comprasmicroservicio.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Compras {
    @GeneratedValue
    @Id
    private Long id;
    private String usuario;
    private LocalDate fechaEvento;
    private LocalDate fechaCompra;
    private String plan;
    private Integer costo;
}