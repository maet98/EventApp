package edu.pucmm.notificacionesmicroservicio.Services;

import com.sendgrid.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

@Service
public class EmailServices {

    @Autowired
    private ReportService reportService;

    public boolean sendEmail(String email, String subject, String content){
        Email from = new Email("no-reply@em9523.miguelestevez.xyz");
        Email toEmail = new Email(email);
        Content plainContent = new Content("text/plain", content);
        Mail mail = new Mail(from, subject, toEmail, plainContent);

        SendGrid sg = new SendGrid("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");
        Request request = new Request();
        System.out.println("Se envio");

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);

            System.out.println("STATUS CODE " + response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error");
            return false;
        }
        System.out.println("Todo Bien");
        return true;
    }

    public boolean sendInvoice(String email){

        return sendEmail(email, "Factura Evento!", "AQUI VA EL REPORTE");
    }
}
