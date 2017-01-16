package ru.ordm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import ru.ordm.dto.DocType;
import ru.ordm.repository.jdbc.JdbcDocumentRepository;

@Controller
public class MainController {

    private static final String VIEW_INDEX = "index";
    private static final String VIEW_LOGIN = "login";


    @Autowired
    private JdbcDocumentRepository jdbcRepository;

    @RequestMapping(value="/", method = RequestMethod.GET)
    public ModelAndView index(ModelAndView modelAndView){

   String username = SecurityContextHolder.getContext().getAuthentication().getName();
   //     String password = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString().substring(username.length());

        modelAndView.setViewName(VIEW_INDEX);
        modelAndView.addObject("docType",jdbcRepository.getAllDocType(username));
        return modelAndView;
    }


    @RequestMapping(value="/login")
    public ModelAndView login(ModelAndView modelAndView){
        modelAndView.setViewName(VIEW_LOGIN);
        return modelAndView;
    }

}
