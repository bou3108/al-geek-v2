package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.service.FilterService;
import fr.afcepf.algeek.service.ProductService;
import fr.afcepf.algeek.util.TriPrixCroissant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/filter")
public class FilterRestController {

    @Autowired
    private FilterService filterService;

    @Autowired
    private ProductService productService;

    /**
     * Renvoie une liste de Produits adaptés à un usage occasionnel.
     *
     * @param produits
     * @param computerKind : correspond à la clé d'une caractéristique d'usage associée au produit (ex : "ordi_bur_usage").
     * @return
     */
    @PostMapping(value = "/use/casual/kind={computerKind}")
    public List<Produit> getFilteredOccasionalUse(@RequestBody List<Produit> produits, @PathVariable String computerKind) {
        return filterService.filtrerUsageOccasionnel(produits, computerKind);
    }

    /**
     * Renvoie une liste de produits adaptés à un usage régulier
     *
     * @param produits
     * @param computerKind : correspond à la clé d'une caractéristique d'usage associée au produit (ex : "ordi_port_usage").
     * @return
     */
    @PostMapping(value = "/use/regular/kind={computerKind}")
    public List<Produit> getFilteredRegularUse(@RequestBody List<Produit> produits, @PathVariable String computerKind) {
        return filterService.filtrerUsageRegulier(produits, computerKind);
    }

    /**
     * Renvoie une liste de produits adaptés à un usage intensif.
     *
     * @param produits
     * @param computerKind : correspond à la clé d'une caractéristique d'usage associée au produit (ex : "ordi_bur_usage").
     * @return
     */
    @PostMapping(value = "/use/intensive/kind={computerKind}")
    public List<Produit> getFilteredIntensiveUse(@RequestBody List<Produit> produits, @PathVariable String computerKind) {
        return filterService.filtrerUsageIntensif(produits, computerKind);
    }

    /**
     * Retourne une liste de tous les produits du type passé en paramètre, triée par prix croissant
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/sort/price/ascending/id={id}")
    public ResponseEntity<List<Produit>> trierMoinsCherAuPlusCher(@PathVariable Long id) {
        List<Produit> productsByType = getProduitsParType(id);
        if (productsByType != null) {
            productsByType.sort(new TriPrixCroissant());
            return new ResponseEntity<>(productsByType, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    private List<Produit> getProduitsParType(Long idType) {
        List<Produit> products = new ArrayList<>();
        products = productService.getProduitsParType(idType, true).getBody();
        return products;
    }
}
