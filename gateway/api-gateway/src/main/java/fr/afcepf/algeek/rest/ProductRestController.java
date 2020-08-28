package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/product")
public class ProductRestController {

    private final ResponseEntityRestCommunicator<Produit> communicator = new ResponseEntityRestCommunicator<Produit>(Produit.class, Produit[].class);

    @Value("${algeek.manager.product.address}")
    private String productManagerUrl;


    // "http://ip:port/gateway/product/all"
    @GetMapping("/all")
    public ResponseEntity<List<Produit>> getAllProducts() {
        String url = productManagerUrl + "/all";
        return communicator.getList(url);
    }

    // http://ip:port/gateway/product/id={id}"
    @GetMapping("/id={id}")
    public ResponseEntity<Produit> getProduct(@PathVariable Long id) {
        String url = productManagerUrl + "/id=" + id;
        return communicator.get(url);
    }

    // "http://ip:port/gateway/product/add"
    @PostMapping("/add")
    public ResponseEntity<Produit> addProduct(@RequestBody Produit product) {
        String url = productManagerUrl + "/add";
        return communicator.post(url, product);
    }

    // "http://ip:port/gateway/product/update"
    @PutMapping("/update")
    public ResponseEntity<Produit> updateProduct(@RequestBody Produit product) {
        String url = productManagerUrl + "/update";
        return communicator.put(url, product);
    }

    // "http://ip:port/gateway/product/id={id}"
    @DeleteMapping("/id={id}")
    public ResponseEntity<Produit> deleteProduct(@PathVariable Long id) {
        String url = productManagerUrl + "/id=" + id;
        return communicator.delete(url);
    }

    // "http://ip:port/gateway/product/nouveautes"
    @GetMapping("/latest")
    public ResponseEntity<List<Produit>> getLatestProducts() {
        String url = productManagerUrl + "/latest";
        return communicator.getList(url);
    }

    // "http://ip:port/gateway/product/details/id={id}"
    @GetMapping("/details/id={id}")
    public ResponseEntity<Produit> getDetailedProduct(@PathVariable Long id) {
        String url = productManagerUrl + "/details/id=" + id;
        return communicator.get(url);
    }

    // "http://ip:port/gateway/product/type={id}&with={chargerCaracs}"
    @GetMapping("/type={id}&with={chargerCaracs}")
    public ResponseEntity<List<Produit>> getProductByType(@PathVariable Long id, @PathVariable boolean chargerCaracs) {
        String url = productManagerUrl + "/type=" + id + "&with=" + chargerCaracs;
        return communicator.getList(url);
    }

}
