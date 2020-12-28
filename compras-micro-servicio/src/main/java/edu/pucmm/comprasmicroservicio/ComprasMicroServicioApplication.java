package edu.pucmm.comprasmicroservicio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ComprasMicroServicioApplication {

    public static void main(String[] args) {
        SpringApplication.run(ComprasMicroServicioApplication.class, args);
    }

}
