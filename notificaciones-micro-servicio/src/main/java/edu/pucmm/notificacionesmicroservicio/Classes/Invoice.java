package edu.pucmm.notificacionesmicroservicio.Classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.*;

@Entity
public class Invoice {
    @Id
    @GeneratedValue
    private long id;
    private String username;
    private ArrayList<String> products;
    private double total;

    public Invoice(){}

    public Invoice(String username, ArrayList<String> products, double total){
        this.username = username;
        this.products = products;
        this.total = total;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<String> products) {
        this.products = products;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}