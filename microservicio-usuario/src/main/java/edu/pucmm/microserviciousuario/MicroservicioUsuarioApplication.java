package edu.pucmm.microserviciousuario;

import  edu.pucmm.microserviciousuario.Services.UserServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableDiscoveryClient
@SpringBootApplication
public class MicroservicioUsuarioApplication{

    public static void main(String[] args) {
        ApplicationContext applicationContext = SpringApplication.run(MicroservicioUsuarioApplication.class, args);
        UserServices userServices = (UserServices) applicationContext.getBean("userServices");
        userServices.createAdmin();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
