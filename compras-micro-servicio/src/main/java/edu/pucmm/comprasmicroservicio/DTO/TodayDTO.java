package edu.pucmm.comprasmicroservicio.DTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class TodayDTO {
    private LocalDate today;
    private Integer count;
}
