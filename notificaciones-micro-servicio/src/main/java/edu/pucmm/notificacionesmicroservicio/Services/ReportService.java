package edu.pucmm.notificacionesmicroservicio.Services;


import edu.pucmm.notificacionesmicroservicio.Classes.Invoice;
import edu.pucmm.notificacionesmicroservicio.DTO.CompraDTO;
import edu.pucmm.notificacionesmicroservicio.Repositories.FacturaRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.util.*;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private FacturaRepositories facturaRepository;

    public byte[] exportReport(CompraDTO compra, String reportFormat, int id) throws FileNotFoundException, JRException {
        byte[] output = new byte[0];
        List<CompraDTO> invoices = new ArrayList<>();
        invoices.add(compra);

        InputStream reportStream = getClass().getClassLoader().getResourceAsStream("reporte.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(invoices);
        Map<String, Object> parameters = new HashMap<>();
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
        if(reportFormat.equalsIgnoreCase("pdf")){
            output = JasperExportManager.exportReportToPdf(jasperPrint);
        }
        return output;
    }
}