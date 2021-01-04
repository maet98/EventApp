package edu.pucmm.notificacionesmicroservicio.Classes;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Entity
@Getter
@Setter
public class Invoice {
    @Id
    @GeneratedValue
    private long id;
    private String username;

    @ManyToMany
    private List<Product> products;

    private Integer total;

    public Invoice(String username, List<Product> products, Integer total){
        this.username = username;
        this.products = products;
        this.total = total;
    }
}