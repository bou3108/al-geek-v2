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
@RequestMapping(value = "/product")
public class ProductFilterRestController {

    private final ResponseEntityRestCommunicator<Produit> communicator = new ResponseEntityRestCommunicator<Produit>(Produit.class, Produit[].class);

    @Value("${algeek.manager.product.address}")
    private String productManagerUrl;


    // "http://ip:port/al-geek-gateway/product/casual-use"
    @PostMapping("/filter/use/casual/kind={computerKind}")
    public ResponseEntity<List<Produit>> getProductForCasualUse(@RequestBody List<Produit> productsList, @PathVariable String computerKind) {
        String url = productManagerUrl + "/filter/use/casual/kind=" + computerKind;
        return communicator.postList(url, productsList);
    }

    // "http://ip:port/al-geek-gateway/product/regular-use"
    @PostMapping("/filter/use/regular/kind={computerKind}")
    public ResponseEntity<List<Produit>> getProductForRegularUse(@RequestBody List<Produit> productsList, @PathVariable String computerKind) {
        String url = productManagerUrl + "/filter/use/regular/kind=" + computerKind;
        return communicator.postList(url, productsList);
    }

    // "http://ip:port/al-geek-gateway/product/intensive-use"
    @PostMapping("/filter/use/intensive/kind={computerKind}")
    public ResponseEntity<List<Produit>> getProductForIntensiveUse(@RequestBody List<Produit> productsList, @PathVariable String computerKind) {
        String url = productManagerUrl + "/filter/use/intensive/kind=" + computerKind;
        return communicator.postList(url, productsList);
    }

    // "http://ip:port/al-geek-gateway/product/sort/price/ascending"
    @GetMapping("/filter/sort/price/ascending/id={id}")
    public ResponseEntity<List<Produit>> sortFromCheapestToMostExpensive(@PathVariable Long id) {
        String url = productManagerUrl + "/filter/sort/price/ascending/id=" + id;
        return communicator.getList(url);
    }
}
