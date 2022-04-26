package com.corso.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "spettacolo")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Spettacolo {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Client[] prenotazioni;
    private int n_prenotazione;
    private ArrayList<Client> attesa;

    public Spettacolo(int n){
        this.n_prenotazione=n;
        this.attesa=new ArrayList<>(n);
        this.prenotazioni= new Client[n];
    }


}
