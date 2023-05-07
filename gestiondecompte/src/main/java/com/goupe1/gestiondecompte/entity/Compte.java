package com.goupe1.gestiondecompte.entity;

import jakarta.persistence.*;
import net.minidev.json.annotate.JsonIgnore;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "comptes")
    public class Compte implements Serializable {
        @Id
        @Column(unique = true)
        private String numero;

        @Column(nullable = false)
        private Double solde;

        @Enumerated(EnumType.STRING)
        private TypeCompte type;

        @Temporal(TemporalType.DATE)
        private Date dateCreation;


        @ManyToOne(fetch = FetchType.EAGER)
        private Client proprietaire;


    public Double getSolde() {
        return solde;
    }

    public void setSolde(Double solde) {
        this.solde = solde;
    }

    public TypeCompte getType() {
        return type;
    }

    public void setType(TypeCompte type) {
        this.type = type;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Client getProprietaire() {
        return proprietaire;
    }

    public void setProprietaire(Client proprietaire) {
        this.proprietaire = proprietaire;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }












   /* public void versement(double montant) {
            solde += montant;
        }
    public void retrait(double montant) throws SoldeInsuffisantException {
        if (montant > solde) {
            throw new SoldeInsuffisantException();
        }
        solde -= montant;
    }

    public void virement(Compte destination, double montant) throws SoldeInsuffisantException {
        if (montant > solde) {
            throw new SoldeInsuffisantException();
        }
        this.retrait(montant);
        destination.versement(montant);
    }



    private String generateUniqueAccountNumber() {
        String numero;
        do {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 5; i++) {
                sb.append((char) (Math.random() * 26 + 'A'));
            }
            numero = sb.toString() + new SimpleDateFormat("yyyy").format(new Date());
        } while (compteRepository.findByNumero(numero).isPresent());
        return numero;
    }

    public class SoldeInsuffisantException extends RuntimeException {

            public SoldeInsuffisantException() {
                super("Solde insuffisant");
            }
        }*/




    }
