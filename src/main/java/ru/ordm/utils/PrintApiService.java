package ru.ordm.utils;

import net.sf.jasperreports.engine.*;
import ru.ordm.domain.PlatPor;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

@Component

public class PrintApiService {
    public void generateReport(List<PlatPor> cities) throws JRException {
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        jasperReport = JasperCompileManager
                .compileReport("src/main/resources/static/jasper/PlatPor.jrxml");
        CustomJRDataSource<PlatPor> dataSource = new CustomJRDataSource<PlatPor>();
              //  .initBy(cities);
        jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap(),
                dataSource);
        JasperExportManager.exportReportToPdfFile(jasperPrint,
                "src/main/resources/static/jasper/PlatPor.pdf");
    }
}

