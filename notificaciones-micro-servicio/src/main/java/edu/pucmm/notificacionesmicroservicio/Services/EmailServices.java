package edu.pucmm.notificacionesmicroservicio.Services;

import com.sendgrid.*;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

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

    public Boolean sendMasssive(List<String> emails, String subject, String content, byte[] out) {
        Email from = new Email("no-reply@em9523.miguelestevez.xyz");
        Personalization personalization = new Personalization();

        for(String email: emails) {
            personalization.addTo(new Email(email));
        }

        Content plainContent = new Content("text/plain", content);
        Mail mail = new Mail();
        mail.setFrom(from);
        mail.setSubject(subject);
        mail.addContent(plainContent);
        mail.addPersonalization(personalization);
        Attachments attachments = new Attachments();
        Base64 x = new Base64();
        String reportPDF = x.encodeAsString(out);
        attachments.setContent(reportPDF);
        attachments.setType("application/pdf");//"application/pdf"
        attachments.setFilename("report.pdf");
        attachments.setDisposition("attachment");
        attachments.setContentId("Banner");
        mail.addAttachments(attachments);

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

    public boolean sendEmailAttachment(String email, String subject, String content, byte[] out) {
        Email from = new Email("no-reply@em9523.miguelestevez.xyz");
        Email toEmail = new Email(email);
        Content plainContent = new Content("text/plain", content);
        Mail mail = new Mail(from, subject, toEmail, plainContent);
        Attachments attachments = new Attachments();
        Base64 x = new Base64();
        String reportPDF = x.encodeAsString(out);
        attachments.setContent(reportPDF);
        attachments.setType("application/pdf");//"application/pdf"
        attachments.setFilename("report.pdf");
        attachments.setDisposition("attachment");
        attachments.setContentId("Banner");
        mail.addAttachments(attachments);

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
}
