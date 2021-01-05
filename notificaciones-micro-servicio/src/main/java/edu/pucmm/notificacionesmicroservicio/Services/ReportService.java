package edu.pucmm.notificacionesmicroservicio.Services;


import edu.pucmm.notificacionesmicroservicio.Classes.Invoice;
import edu.pucmm.notificacionesmicroservicio.Repositories.FacturaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.util.*;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private FacturaRepositories facturaRepository;

    public byte[] exportReport(String reportFormat, int id) throws FileNotFoundException, JRException {
        byte[] output = new byte[0];
        List<Invoice> invoices = new ArrayList<>();
        Invoice invoice = facturaRepository.findById(id);
        invoices.add(invoice);

        File file = ResourceUtils.getFile("classpath:reporte.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(invoices);
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if(reportFormat.equalsIgnoreCase("pdf")){
            output = JasperExportManager.exportReportToPdf(jasperPrint);
        }
        return output;
    }
}