package edu.pucmm.notificacionesmicroservicio.Classes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String plan;
    private Integer total;

    public Invoice(String username, String plan, Integer total) {
        this.username = username;
        this.plan = plan;
        this.total = total;
    }
}