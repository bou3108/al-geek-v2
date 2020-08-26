package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;
import fr.afcepf.algeek.service.ConfiguratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/typeComposants")
    public ResponseEntity<List<TypeProduit>> getTypesComposants() {
        return configuratorService.getTypesComposants();
    }

    /**
     * Retourne une ResponseEntity contenant true si les deux premiers Produit du tableau passé en paramètre sont compatibles,
     * false sinon.
     *
     * @param produits
     * @return
     */
    @GetMapping(value = "/compatibility")
    public ResponseEntity<Boolean> isCompatibleWith (@RequestBody Produit[] produits) {
        if (configuratorService.estCompatibleAvec(produits[0], produits[1])) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }
}
