package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;
import fr.afcepf.algeek.service.ConfiguratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/configure")
public class ConfiguratorRestController {

    @Autowired
    private ConfiguratorService configuratorService;

    /**
     * Retourne une ResponseEntity contenant soit la liste de tous les composants ordinateurs, soit un code HTTP NOT_FOUND
     * @return
     */
    @GetMapping(value = "/types/all")
    public ResponseEntity<List<TypeProduit>> getTypesComposants() {
        return configuratorService.getTypesComposants();
    }

    /**
     * Retourne une ResponseEntity contenant OK si les deux premiers Produit du tableau passé en paramètre sont compatibles,
     * NOT_ACCEPTABLE sinon, et BAD_REQUEST en cas d'exception.
     *
     * @param produits
     * @return
     */
    @PostMapping(value = "/compatibility")
    // PASSER LA METHODE EN GET
    public ResponseEntity<Produit[]> isCompatibleWith (@RequestBody Produit[] produits) {
        try {
            if (configuratorService.estCompatibleAvec(produits[0], produits[1])) {
                return new ResponseEntity<>(produits, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(produits, HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(produits, HttpStatus.BAD_REQUEST);
        }

    }
}
