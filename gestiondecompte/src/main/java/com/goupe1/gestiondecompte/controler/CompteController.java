package com.goupe1.gestiondecompte.controler;

import com.goupe1.gestiondecompte.entity.Compte;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.goupe1.gestiondecompte.services.CompteService;

import java.util.List;

@RestController
@RequestMapping("/api/comptes/")
public class CompteController {
    @Autowired
    private CompteService compteService;

    @GetMapping("all")
    public List<Compte> getAllComptes() {
        System.out.println("--------");
        return compteService.getAllComptes();
    }

    @GetMapping("{numero}")
    public Compte getCompteByNumero(@PathVariable String numero) {
        return compteService.getCompteByNumero(numero);
    }

    @PostMapping("create")
    public Compte createCompte(@RequestBody Compte compte) {
        return compteService.createCompte(compte);
    }

    @PutMapping("update/{numero}")
    public Compte updateCompte(@PathVariable String numero, @RequestBody Compte compte) {
        return compteService.updateCompte(numero, compte);
    }

    @DeleteMapping("delete/{numero}")
    public void deleteCompte(@PathVariable String numero) {
        compteService.deleteCompte(numero);
    }

    @PostMapping("{numero}/versement")
    public Compte effectuerVersement(@PathVariable String numero, @RequestParam double montant) {
//        return ResponseStatus
        return compteService.effectuerVersement(numero, montant);
    }

    @PostMapping("{numero}/retrait")
    public Compte effectuerRetrait(@PathVariable String numero, @RequestParam double montant) {
        return compteService.effectuerRetrait(numero, montant);
    }

    @PostMapping("virement/")
    public void effectuerVirement(@RequestParam String numeroSource, @RequestParam String numeroDestination, @RequestParam double montant) {
        compteService.effectuerVirement(numeroSource, numeroDestination, montant);
    }
}


