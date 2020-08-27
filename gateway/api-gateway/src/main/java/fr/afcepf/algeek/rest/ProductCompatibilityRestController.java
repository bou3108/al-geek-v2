package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Produit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/product")
public class ProductCompatibilityRestController {

    private final ResponseEntityRestCommunicator<Produit[]> communicator = new ResponseEntityRestCommunicator<Produit[]>(Produit[].class, Produit[][].class);

    @Value("${algeek.manager.product.address}")
    private String productManagerUrl;


    // "http://ip:port/al-geek-gateway/product/configure/compatibility"
    @PostMapping("/configure/compatibility")
    public ResponseEntity<Produit[]> isCompatible(@RequestBody Produit[] products) {
        String url = productManagerUrl + "/configure/compatibility";
        return communicator.post(url, products);
    }
}
