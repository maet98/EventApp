package edu.pucmm.notificacionesmicroservicio.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompraDTO {
    private Long id;
    private String usuario;
    private LocalDate fechaEvento;
    private LocalDate fechaCompra;
    private String plan;
    private Integer costo;
}