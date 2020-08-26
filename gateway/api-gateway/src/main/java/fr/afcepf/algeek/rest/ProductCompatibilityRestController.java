package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Produit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/product")
public class ProductCompatibilityRestController {

    ResponseEntityRestCommunicator<Produit[]> communicator = new ResponseEntityRestCommunicator<Produit[]>(Produit[].class, Produit[][].class);

    private String productManagerUrl = "http://ip:port/manager/product/";


    // "http://ip:port/al-geek-gateway/product/configure/compatibility"
    @PostMapping("/configure/compatibility")
    public ResponseEntity<Produit[]> isCompatible(@RequestBody Produit[] products) {
        String url = productManagerUrl + "/configure/compatibility";
        return communicator.post(url, products);
    }
}
