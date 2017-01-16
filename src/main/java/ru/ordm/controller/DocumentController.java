package ru.ordm.controller;

import java.util.HashMap;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import ru.ordm.repository.PlatPorRepository;
import ru.ordm.utils.PrintApiService;

@Controller
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    private PlatPorRepository platPorRepository;

    private static final String VIEW_PLAT_POR = "document/platporuch";
    private static final String VIEW_SKLPORDER = "document/sklporder";

    @RequestMapping(value="/pp")
    public ModelAndView getPp(ModelAndView modelAndView,
                              @RequestParam(value = "id",required = false) Long id){

     //   modelAndView.addObject("doclist", platPorRepository.findOne(id));
    	 modelAndView.addObject("doclist", platPorRepository.findByCustomQuery(id));
        modelAndView.setViewName(VIEW_PLAT_POR);
        return modelAndView;
    }
    

    @RequestMapping(value="/po")
    public ModelAndView getPo(ModelAndView modelAndView,
                              @RequestParam(value = "id",required = false) Long id){
        System.out.println("1");
        modelAndView.setViewName(VIEW_SKLPORDER);
        return modelAndView;
    }

}
