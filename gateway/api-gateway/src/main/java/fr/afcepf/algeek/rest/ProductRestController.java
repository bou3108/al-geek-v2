package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Produit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/product", headers = "Accept=application/json")
public class ProductRestController {

    ResponseEntityRestCommunicator<Produit> communicator = new ResponseEntityRestCommunicator<Produit>(Produit.class, Produit[].class);
    private static RestTemplate restTemplate = new RestTemplate();

    private String productManagerUrl = "http://ip:port/manager/product/";

    // "http://ip:port/gateway/product/all"
    @GetMapping("/all")
    public ResponseEntity<List<Produit>> getAllProducts() {
        String url = productManagerUrl + "/all";
        return communicator.getList(url);
    }

    // http://ip:port/gateway/product/id={id}"
    @GetMapping("/id={id}")
    public ResponseEntity<Produit> getProduct(@RequestParam(value = "id", required = false) Long id) {
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
    @GetMapping("/nouveautes")
    public ResponseEntity<List<Produit>> getLatestProducts() {
        String url = productManagerUrl + "/nouveautes";
        return communicator.getList(url);
    }

    // "http://ip:port/gateway/product/details/id={id}"
    @GetMapping("/details")
    public ResponseEntity<Produit> getDetailedProduct(@RequestParam(value = "id", required = false) Long productId) {
        String url = productManagerUrl + "/details/id=" + productId;
        return communicator.get(url);
    }

    // "http://ip:port/gateway/product/type={id}&with={chargerCaracs}"
    @GetMapping("/type={id}&with={chargerCaracs}")
    public ResponseEntity<List<Produit>> getProductByType(@PathVariable Long id, @PathVariable boolean chargerCaracs) {
        String url = productManagerUrl + "/type=" + id + "&with=" + chargerCaracs;
        return communicator.getList(url);
    }

    // "http://ip:port/al-geek-gateway/product/types/all"
    @GetMapping("/types/all")
    public ResponseEntity<List<Produit>> getAllProductTypes() {
        String url = productManagerUrl + "/types/all";
        return communicator.getList(url);
    }

    // "http://ip:port/al-geek-gateway/product/casual-use"
    @GetMapping("/casual-use/kind={computerKind}")
    public ResponseEntity<List<Produit>> getProductForCasualUse(@RequestBody List<Produit> productsList, @PathVariable String computerKind) {
        String url = productManagerUrl + "/casual-use/kind=" + computerKind;
        return null;
    }

    // "http://ip:port/al-geek-gateway/product/regular-use"
    @GetMapping("/regular-use/kind={computerKind}")
    public ResponseEntity<List<Produit>> getProductForRegularUse(List<Produit> productsList, String computerKind) {
        String url = productManagerUrl + "/regular-use/kind=" + computerKind;
        return null;
    }

    // "http://ip:port/al-geek-gateway/product/intensive-use"
    @GetMapping("/intensive-use/kind={computerKind}")
    public ResponseEntity<List<Produit>> getProductForIntensiveUse(List<Produit> productsList, String computerKind) {
        String url = productManagerUrl + "/intensive-use/kind=" + computerKind;
        return null;
    }

    // "http://ip:port/al-geek-gateway/product/sort/price/ascending"
    @GetMapping("/sort/price/ascending/id={id}")
    public ResponseEntity<List<Produit>> sortFromCheapestToMostExpensive(@RequestParam(value = "id", required = false) Long idType) {
        String url = productManagerUrl + "/sort/price/ascending/id=" + idType;
        return communicator.getList(url);
    }
}
