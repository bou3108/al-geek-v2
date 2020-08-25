package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/filter")
public class FilterRestController {

    @Autowired
    private FilterService filterService;

    // TO DO
    @GetMapping(value = "/occasional")
    public List<Produit> getFilteredOccasionalUse (@RequestBody List<Produit> produits) {


        return null;
    }

    // TO DO
    @GetMapping(value = "/regular")
    public List<Produit> getFilteredRegularUse (@RequestBody List<Produit> produits) {


        return null;
    }

    // TO DO
    @GetMapping(value = "/intensive")
    public List<Produit> getFilteredIntensiveUse (@RequestBody List<Produit> produits) {


        return null;
    }
}
