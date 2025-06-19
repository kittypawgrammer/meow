package com.example.CatFact.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.CatFact.Entity.CatFacts;
import com.example.CatFact.Service.CatfactService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api/v1")
public class CatFactController {

    @Autowired
    private  CatfactService catFactService;

    // GET /facts?page=0&size=10
    @GetMapping("/facts")
    public Page<CatFacts> getCatFacts(@RequestParam(defaultValue = "0")int page,
                                      @RequestParam (defaultValue = "10")int size){
        return catFactService.getAllFacts(PageRequest.of(page, size));
    }

    // GET /facts/{id}
    @GetMapping("/facts/{id}")
    public CatFacts getCatFactsById(@PathVariable long id){
        return catFactService.getCatFactsById(id);
    }

    // DELETE /facts/{id}
    @DeleteMapping("/facts/{id}")
    public void deleteCatFact(@PathVariable long id){
        catFactService.deleteCatFact(id);
    }

}
