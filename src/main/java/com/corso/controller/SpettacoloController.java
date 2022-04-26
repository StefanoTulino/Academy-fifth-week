package com.corso.controller;


import com.corso.domain.Client;
import com.corso.domain.Spettacolo;
import com.corso.service.SpettacoloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/spettacoli")
public class SpettacoloController {

    @Autowired
    SpettacoloService spettacoloService;

    private Spettacolo s;


    @PostMapping("/create")
    ResponseEntity<Spettacolo> create(@RequestParam int n){
        return new ResponseEntity(spettacoloService.create(n),HttpStatus.OK);
    }


    @GetMapping("/byId/{id}")
     ResponseEntity<Spettacolo> findById(@PathVariable int id) {
        return new ResponseEntity(spettacoloService.findById(id),HttpStatus.OK);
    }


    @GetMapping("/all")
    ResponseEntity<List<Spettacolo>> getAll(){
        return new ResponseEntity(spettacoloService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/incompleto")
    ResponseEntity<Boolean> findClient(@RequestParam int id){
        spettacoloService.incompleto(id);
        return new ResponseEntity("Incompleto",HttpStatus.OK);
    }

    @GetMapping("/findClient")
    ResponseEntity<Integer> findClient(@PathVariable String nome,@PathVariable String tel,@RequestParam int id){
        spettacoloService.trova(id,nome,tel);
        return new ResponseEntity("Trovato",HttpStatus.OK);
    }



    @PostMapping("/insert/{id}/{name}/{telephone}")
    void insertPrenotations(@PathVariable int id,@PathVariable String name,@PathVariable String telephone){
        spettacoloService.prenota(id,name,telephone);
    }

    @DeleteMapping("/delete")
    void delete (@RequestParam int id,@RequestBody Client c){
        String nome=c.getName();
        String tel=c.getTelephone();
        spettacoloService.disdici(id,nome,tel);
    }


}
