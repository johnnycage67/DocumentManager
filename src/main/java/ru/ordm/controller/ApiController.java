package ru.ordm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ordm.domain.Document;
import ru.ordm.domain.PlatPor;
import ru.ordm.repository.DocumentRepository;
import ru.ordm.repository.PlatPorRepository;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private DocumentRepository repository;



    @RequestMapping(value = "/test", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    private List<Document> getAll(@RequestParam(value = "type_doc",required = false) String docType,
                                  @RequestParam(value = "itIsMine",required = false) boolean itIsMy){

        if(docType == null )return null;

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if(itIsMy){
            return repository.findByOwnerNameAndTpOrderByLocalDateDesc(username,docType);
        }
        else
            return repository.findByTpOrderByLocalDateDesc(docType);
    }

    @RequestMapping(value = "/api", method = RequestMethod.GET)
    private List<Document> getAll(){
        return repository.findByTpOrderByLocalDateDesc("NAKL_POST");
    }
    
    @Autowired
    private PlatPorRepository platPorRepository;
    
    
    @RequestMapping(value = "/api1", method = RequestMethod.GET)
    private PlatPor get123(@RequestParam(value = "id",required = false) Long id){
        return platPorRepository.findByCustomQuery(id);
    }
    

    @RequestMapping(value = "/td", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Document> getBetween(
            @RequestParam(value = "type_doc", required = false) String docType) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findByOwnerNameAndTpOrderByLocalDateDesc(username,docType);
    }


}
