package com.corso.service;


import com.corso.domain.Client;
import com.corso.domain.Spettacolo;

import java.util.List;
import java.util.Optional;

public interface SpettacoloService {

    Spettacolo findById(int id);
    List<Spettacolo> findAll();
    Spettacolo create(int n);

    boolean libero(int id);
    int trova(int id,String nome, String tel);
    void prenota(int id,String nome, String tel);
    void disdici(int id,String nome, String tel);
    boolean incompleto(int id);

}
