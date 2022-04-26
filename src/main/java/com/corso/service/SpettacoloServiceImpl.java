package com.corso.service;

import com.corso.domain.Client;
import com.corso.domain.Spettacolo;
import com.corso.repository.SpettacoloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class SpettacoloServiceImpl implements SpettacoloService{

    @Autowired
    SpettacoloRepository spettacoloRepository;



    @Override
    public Spettacolo create(int n) {
        Spettacolo spettacolo=new Spettacolo(n);
        return spettacoloRepository.save(spettacolo);
    }

    @Override
    public Spettacolo findById(int id) {
        return spettacoloRepository.findById(id).orElse(null);
    }


    @Override
    public List<Spettacolo> findAll() {
        return spettacoloRepository.findAll();
    }



    @Override
    public boolean libero(int id) {
        Spettacolo s=spettacoloRepository.findById(id).orElse(null);
        int count=0;
        Client[] cArray = s.getPrenotazioni();
        List<Client> tempList = new ArrayList<Client>(Arrays.asList(cArray));
        for(int i=0;i<tempList.size();i++){
            if(tempList.get(i)==null)
                count++;
        }
        int prenotatiReal= s.getN_prenotazione()- count;
        if(prenotatiReal == tempList.size())
        return false;
            return true;
    }



    @Override
    public int trova(int id, String nome, String tel) {
        Spettacolo s=spettacoloRepository.findById(id).orElse(null);
        Client c=new Client(nome,tel);
        Client[] cArray = s.getPrenotazioni();
        ArrayList<Client> attesa = s.getAttesa();



        for(int i=0;i<cArray.length;i++){
            if( cArray[i].getName().equalsIgnoreCase(c.getName()) &&
                    cArray[i].getTelephone().equalsIgnoreCase(c.getTelephone()) )
                return 0;
        }
        for(int i=0;i< attesa.size();i++){
            if( attesa.get(i).getName().equalsIgnoreCase(c.getName()) &&
                    attesa.get(i).getTelephone().equalsIgnoreCase(c.getTelephone()) )
                return 1;
        }
        return -1;
    }



    @Override
    public void prenota(int id,String nome, String tel) {
        boolean prenotazione=libero(id);
        Client c= new Client(nome,tel);
        Spettacolo s= spettacoloRepository.findById(id).orElse(null);
        Client[] cArray = s.getPrenotazioni();
        //conversione in arrayList e poi riporto in array
        List<Client> tempList = new ArrayList<Client>(Arrays.asList(cArray));

        if(prenotazione) {
            for(int i=0;i<tempList.size();i++){
                if(tempList.get(i)==null){
                    tempList.set(i,c);
                    cArray=tempList.toArray(new Client[0]);
                    s.setPrenotazioni(cArray);
                    spettacoloRepository.save(s);
                    break;
                }
            }
        }
        //else {
          // ArrayList<Client> attesa= s.getAttesa();
           //attesa.add(c);
           //s.setAttesa(attesa);
           //spettacoloRepository.save(s);
        //}
    }

    @Override
    public void disdici(int id,String nome, String tel) {
        Spettacolo s = spettacoloRepository.findById(id).orElse(null);
        Client[] cArray = s.getPrenotazioni();
        ArrayList<Client> attesa = s.getAttesa();
        int searchClient= trova(id,nome,tel);
            for(int i=0;i<cArray.length;i++) {
                if (searchClient == 0) {
                    cArray[i] = null;
                    s.setPrenotazioni(cArray);
                    spettacoloRepository.save(s);
                    break;
                }
            }

            for(int i=0;i<attesa.size();i++){
                if(searchClient==1){
                attesa.remove(i);
                spettacoloRepository.save(s);
                break;
            }
        }
    }


    @Override
    public boolean incompleto(int id) {
        Spettacolo s= spettacoloRepository.findById(id).orElse(null);
        if(s.getPrenotazioni().length>0 && s.getAttesa().size()>0)
                return true;
                    return false;
        }

}
