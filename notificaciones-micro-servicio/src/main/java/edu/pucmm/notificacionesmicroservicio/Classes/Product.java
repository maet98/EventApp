package edu.pucmm.notificacionesmicroservicio.Classes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private String name;
    private Integer cost;
}
