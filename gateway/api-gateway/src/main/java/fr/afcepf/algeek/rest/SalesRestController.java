package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.Produit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/sales", headers = "Accept=application/json")
public class SalesRestController {

    private static RestTemplate restTemplate = new RestTemplate();

    private String salesManagerUrl = "http://ip:port/manager/sales";

    // "http://ip:port/gateway/sales/"
    @GetMapping("/best-sales")
    List<Produit> getBestSales(@RequestParam(name = "size", required = true) int size) {
        List<Produit> productsList = null;
        try {
            Produit[] products = restTemplate.getForObject(salesManagerUrl + "/best-sales/" + size, Produit[].class);
            productsList = Arrays.asList(products);
        } catch (Exception ex) {
            log.error("get best sales failed : " + ex.getMessage(), ex);
        }
        return productsList;
    }

}
