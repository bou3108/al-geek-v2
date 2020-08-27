package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Produit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/sales")
public class SalesRestController {

    private final ResponseEntityRestCommunicator<Produit> communicator = new ResponseEntityRestCommunicator<Produit>(Produit.class, Produit[].class);

    @Value("${algeek.manager.sales.address}")
    private String salesManagerUrl;


    // "http://ip:port/gateway/sales/"
    @GetMapping("/best-sales")
    public ResponseEntity<List<Produit>> getBestSales(@RequestParam(name = "size", required = true) int size) {
        String url = salesManagerUrl + "/best-sales/" + size;
        return communicator.getList(url);
    }

}
