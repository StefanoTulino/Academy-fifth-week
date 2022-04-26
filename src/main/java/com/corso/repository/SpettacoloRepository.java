package com.corso.repository;

import com.corso.domain.Client;
import com.corso.domain.Spettacolo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpettacoloRepository extends JpaRepository<Spettacolo,Integer> {

}
