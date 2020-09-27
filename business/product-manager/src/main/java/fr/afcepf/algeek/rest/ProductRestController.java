package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Categorie;
import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping
public class ProductRestController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/select")
    public ResponseEntity<List<Produit>> getSelectedProducts(@RequestBody Long[] productIds) {
        List<Produit> products = new ArrayList<>();
        for (Long id : productIds) {
            if(productService.getProduitAvecCaracteristiques(id).getStatusCode().equals(HttpStatus.OK)) {
                products.add(productService.getProduitAvecCaracteristiques(id).getBody());
            }
        }

        if(products.size() == productIds.length) {
            return new ResponseEntity<>(products, HttpStatus.OK);
        } else if (products.size() == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<>(products, HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping(value = "/latest")
    public ResponseEntity<List<Produit>> getNouveautes(){
        return productService.getNouveautes();
    }

    @GetMapping(value = "/type={id}&with={chargerCarac}")
    public ResponseEntity<List<Produit>> getProduitsParType(@PathVariable Long id, @PathVariable boolean chargerCarac) {
        return productService.getProduitsParType(id, chargerCarac);
    }

    @GetMapping(value = "/details/id={id}")
    public ResponseEntity<Produit> getProduitAvecCaracteristiques(@PathVariable Long id) {
        return productService.getProduitAvecCaracteristiques(id);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Produit> ajouterProduit(@RequestBody Produit produit) {
        return productService.ajouter(produit);
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Produit> modifierProduit(@RequestBody Produit produit) {
        return productService.modifier(produit);
    }

    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<Produit> supprimerProduit(@PathVariable Long id) {
        return productService.supprimer(id);
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<Produit> getProductById(@PathVariable Long id) {
        return productService.rechercherParId(id);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Produit>> getAll() {
        return productService.getAll();
    }

    @GetMapping(value = "/root")
    public ResponseEntity<Categorie> getRootCategorie() {
        return productService.getRootCategorie();
    }
}
