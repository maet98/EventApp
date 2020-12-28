package edu.pucmm.usuariomicroservicio;

import  edu.pucmm.usuariomicroservicio.Services.UserServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@EnableDiscoveryClient
@EnableCircuitBreaker
@SpringBootApplication
public class MicroservicioUsuarioApplication{

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(MicroservicioUsuarioApplication.class, args);
        UserServices userServices = (UserServices) applicationContext.getBean("userServices");
        userServices.createAdmin();
    }

}
