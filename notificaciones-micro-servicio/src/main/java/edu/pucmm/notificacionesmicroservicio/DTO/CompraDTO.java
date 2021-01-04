package edu.pucmm.notificacionesmicroservicio.DTO;

import edu.pucmm.notificacionesmicroservicio.Classes.Product;
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
    private String email;
    private LocalDate fechaEvento;
    private LocalDate fechaCompra;
    private List<Product> products;
    private Integer total;
}