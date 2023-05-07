package com.goupe1.gestiondecompte.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Récupérer tous les clients
    @Override
    List<Client> findAll();

    // Récupérer un client par son identifiant
    @Override
    Optional<Client> findById(Long id);

    Optional<Client> findByNom(String nom);

    // Créer un nouveau client
    Client save(Client client);

    // Mettre à jour un client existant
    Client saveAndFlush(Client client);

    // Supprimer un client
    void deleteById(Long id);
}

