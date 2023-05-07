package com.goupe1.gestiondecompte.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte, String> {
    @Override
    List<Compte> findAll();

    @Override
    Optional<Compte> findById(String numero);


    Optional<Compte> findByNumero(String numero);

}
