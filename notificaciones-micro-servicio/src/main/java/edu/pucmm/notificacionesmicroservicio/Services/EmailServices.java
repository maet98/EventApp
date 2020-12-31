package edu.pucmm.notificacionesmicroservicio.Services;

import com.sendgrid.*;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

@Service
public class EmailServices {
    public boolean sendEmail(String email, String subject, String content){
        Email from = new Email("20170982@ce.pucmm.edu.do");
        Email toEmail = new Email(email);
        Content plainContent = new Content("text/plain", content);
        Mail mail = new Mail(from, subject, toEmail, plainContent);

        SendGrid sg = new SendGrid("API_KEY_HERE");
        Request request = new Request();

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
            return false;
        }
        return true;
    }

    public boolean sendInvoice(String email){
        return sendEmail(email, "Factura Evento!", "AQUI VA EL REPORTE");
    }
}
