package edu.pucmm.comprasmicroservicio.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseDTO {
    private LocalDate fecha;
    private Long count;

    public PurchaseDTO(LocalDate fecha, Long count) {
        this.fecha = fecha;
        this.count = count;
    }
}
