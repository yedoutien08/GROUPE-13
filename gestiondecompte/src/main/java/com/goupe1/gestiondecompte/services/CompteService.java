package com.goupe1.gestiondecompte.services;

import com.goupe1.gestiondecompte.entity.Client;
import com.goupe1.gestiondecompte.entity.ClientRepository;
import com.goupe1.gestiondecompte.entity.Compte;
import com.goupe1.gestiondecompte.entity.CompteRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Random;

@Service
@AllArgsConstructor
public class CompteService {
    @Autowired
    private CompteRepository compteRepository;

    @Autowired
    private ClientRepository clientRepository;

    public List<Compte> getAllComptes() {
        System.out.println(compteRepository.findAll());
        return compteRepository.findAll();
    }

    public Compte getCompteByNumero(String numero) {
        return compteRepository.findById(numero).orElseThrow(() -> new EntityNotFoundException("compte non trouvé : " + numero));
    }

    public Compte createCompte(Compte compte) {
        // Génère un numéro de compte unique
        String numero = generateUniqueNumeroCompte();
        compte.setNumero(numero);

        // Vérifie que le propriétaire du compte existe
        Long clientId = compte.getProprietaire().getId();
        Client client = clientRepository.findById(clientId).orElseThrow(() -> new EntityNotFoundException("client non trouvé(" + clientId+ ")" ));
        compte.setProprietaire(client);

        // Initialise le solde à zéro
        compte.setSolde(0.0);

        return compteRepository.save(compte);
    }

    public Compte updateCompte(String numero, Compte compte) {
        getCompteByNumero(numero); // Vérifie que le compte existe
        compte.setNumero(numero);
        return compteRepository.save(compte);
    }

    public void deleteCompte(String numero) {
        getCompteByNumero(numero); // Vérifie que le compte existe
        compteRepository.deleteById(numero);
    }

    public Compte effectuerVersement(String numero, double montant) {
        Compte compte = getCompteByNumero(numero);
        double nouveauSolde = compte.getSolde() + montant;
        compte.setSolde(nouveauSolde);
        return compteRepository.save(compte);
    }

    public Compte effectuerRetrait(String numero, double montant) {
        Compte compte = getCompteByNumero(numero);
        double nouveauSolde = compte.getSolde() - montant;
        if (nouveauSolde < 0) {
            throw new IllegalArgumentException("Le solde du compte ne peut pas être négatif");
        }
        compte.setSolde(nouveauSolde);
        return compteRepository.save(compte);
    }

    public void effectuerVirement(String numeroSource, String numeroDestination, double montant) {

        if (getCompteByNumero(numeroSource).getSolde() >= montant) {
           effectuerRetrait(getCompteByNumero(numeroSource).getNumero(),montant);
           effectuerVersement(getCompteByNumero(numeroDestination).getNumero(), montant);
           System.out.println("virement effectuer");

        }else {throw new IllegalArgumentException("Le solde du compte source est insuffisant pour effectuer le virement");}

//        return List<Compte> findById(String numero)

    }

    private String generateUniqueNumeroCompte() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        sb.append(Calendar.getInstance().get(Calendar.YEAR));
        String numero = sb.toString();

        // Vérifie que le numéro de compte est unique
        while (compteRepository.findByNumero(numero).isPresent()) {
            sb.setCharAt(random.nextInt(5), alphabet.charAt(random.nextInt(alphabet.length())));
            numero = sb.toString();
        }
        return numero;
    }
}

