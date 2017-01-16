package ru.ordm.controller;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;


@Controller
@RequestMapping("/document")
public class ReportController {

    final static String PlatPorSource = "classpath:static/jasper/PlatPor.jrxml";

    
    @Autowired
    private DataSource dataSource;

    @Autowired
    private ApplicationContext appContext;

    
    @RequestMapping("/pdf")
    public ModelAndView getPdfReport(@RequestParam(value = "id",required = false) Long id) {

       final JasperReportsPdfView view = new JasperReportsPdfView();
       view.setUrl(PlatPorSource);
       view.setJdbcDataSource(dataSource);
       view.setApplicationContext(appContext);
       
       Map<String, Object> params = new HashMap<>();
       params.put("id_doc",id);
       return new ModelAndView(view, params);
    }
	
}
