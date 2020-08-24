package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/items")
public class ProductRestController {

    @Autowired
    private ProductService productService;
    private String urlProductManager = "http://localhost:8180/items/";

    @PostMapping(value = "/add")
    public ResponseEntity<Produit> ajouterProduit(@RequestBody Produit produit) {
        Produit addedProduct = productService.ajouter(produit);
        if (addedProduct != null) {
            return new ResponseEntity<>(addedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.ALREADY_REPORTED);
        }
    }

    @PutMapping(value = "/id={id}")
    public ResponseEntity<Produit> modifierProduit(@RequestBody Produit produit) {
        try {
            productService.modifier(produit);
            return new ResponseEntity<>(produit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<Produit> supprimerProduit(@PathVariable Long id) {
        Produit p = productService.rechercherParId(id);
        try {
            productService.supprimer(id);
            return new ResponseEntity<>(p, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<Produit> getProductById(@PathVariable Long id) {
        if(productService.rechercherParId(id) != null) {
            return new ResponseEntity<Produit>(productService.rechercherParId(id), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Produit>> getAll() {
        if (productService.getAll() != null) {
            return new ResponseEntity<>(productService.getAll(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
