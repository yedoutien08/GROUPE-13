package com.goupe1.gestiondecompte.services;

import com.goupe1.gestiondecompte.entity.Client;
import com.goupe1.gestiondecompte.entity.ClientRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("client non trouvé: " + id));
    }

    public Client createClient(Client client) {
        System.out.println(client.toString());
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client client) {
        getClientById(id); // Vérifie que le client existe
        client.setId(id);
        return clientRepository.save(client);
    }

    public void deleteClient(Long id) {
        getClientById(id); // Vérifie que le client existe
        clientRepository.deleteById(id);
    }
}

