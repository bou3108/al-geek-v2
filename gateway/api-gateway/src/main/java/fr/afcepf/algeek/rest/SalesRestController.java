package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Produit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/sales", headers = "Accept=application/json")
public class SalesRestController {

    ResponseEntityRestCommunicator<Produit> communicator = new ResponseEntityRestCommunicator<Produit>(Produit.class, Produit[].class);

    private String salesManagerUrl = "http://ip:port/manager/sales";


    // "http://ip:port/gateway/sales/"
    @GetMapping("/best-sales")
    public ResponseEntity<List<Produit>> getBestSales(@RequestParam(name = "size", required = true) int size) {
        String url = salesManagerUrl + "/best-sales/" + size;
        return communicator.getList(url);
    }

}
