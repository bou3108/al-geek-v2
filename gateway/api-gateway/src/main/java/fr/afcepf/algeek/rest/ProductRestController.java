package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/product", headers = "Accept=application/json")
public class ProductRestController {

    private static RestTemplate restTemplate = new RestTemplate();

    private String productManagerUrl = "http://ip:port/manager/product/";

    // "http://ip:port/gateway/product/all"
    @GetMapping("/all")
    public List<Produit> getAllProducts() {
        List<Produit> listProducts = null;
        try {
            Produit[] products = restTemplate.getForObject(productManagerUrl + "/all", Produit[].class);
            listProducts = Arrays.asList(products);
        } catch (Exception ex) {
            log.error("getAllProducts : " + ex.getMessage(), ex);
        }
        return listProducts;
    }

    // http://ip:port/gateway/product/id={id}"
    @GetMapping("/id={id}")
    public Produit getProduct(@RequestParam(value = "id", required = false) Long id) {
        Produit product = null;
        try {
            product = restTemplate.getForObject(productManagerUrl + "/id=" + id, Produit.class);
        } catch (Exception ex) {
            log.error("getProduct : " + ex.getMessage(), ex);
        }
        return product;
    }

    // "http://ip:port/gateway/product/add"
    @PostMapping("/add")
    public void addProduct(@RequestBody Produit product) {
        try {
            Produit addedProduct = restTemplate.postForObject(productManagerUrl + "/add", product, Produit.class);
        } catch (Exception ex) {
            log.error("addProduct : " + ex.getMessage(), ex);
        }
    }

    // "http://ip:port/gateway/product/update"
    @PutMapping("/update")
    public void updateProduct(@RequestBody Produit product) {
        try {
            restTemplate.put(productManagerUrl + "/update", product);
        } catch (Exception ex) {
            log.error("updateProduct : " + ex.getMessage(), ex);
        }
    }

    // "http://ip:port/gateway/product/id={id}"
    @DeleteMapping("/id={id}")
    public Produit deleteProduct(@PathVariable Long id) {
        try {
            restTemplate.delete(productManagerUrl + "/id=" + id);
        } catch (Exception ex) {
            log.error("deleteProduct : " + ex.getMessage(), ex);
        }
        return null;
    }

    // "http://ip:port/gateway/product/latest"
    @GetMapping("/latest")
    public List<Produit> getLatestProducts() {
        List<Produit> listProducts = null;
        try {
            Produit[] products = restTemplate.getForObject(productManagerUrl + "/nouveautes", Produit[].class);
            listProducts = Arrays.asList(products);
        } catch (Exception ex) {
            log.error("getLatestProducts : " + ex.getMessage(), ex);
        }
        return listProducts;
    }

    // "http://ip:port/gateway/product/details/id={id}"
    @GetMapping("/details")
    public Produit getDetailedProduct(@RequestParam(value = "id", required = false) Long productId) {
        try {
            return restTemplate.getForObject(productManagerUrl + "/details/" + productId, Produit.class);
        } catch (Exception ex) {
            log.error("getDetailedProduct : " +ex.getMessage(), ex);
        }
        return null;
    }

    // "http://ip:port/gateway/product/type={id}&with={chargerCaracs}"
    @GetMapping("/type={id}&with={chargerCaracs}")
    public List<Produit> getProductByType(@PathVariable Long id, @PathVariable boolean chargerCaracs) {
        List<Produit> listProducts = null;
        try {
            Produit[] products = restTemplate.getForObject(productManagerUrl + "/type=" + id + "&with=" + chargerCaracs, Produit[].class);
            listProducts = Arrays.asList(products);
        } catch (Exception ex) {
            log.error("getProductByType : " + ex.getMessage(), ex);
        }
        return listProducts;
    }

    // "http://ip:port/al-geek-gateway/product/types/all"
    @GetMapping("/types/all")
    public List<TypeProduit> getAllProductTypes() {
        List<TypeProduit> listProductTypes = null;
        try {
            TypeProduit[] productTypes = restTemplate.getForObject(productManagerUrl + "/types/all", TypeProduit[].class);
            listProductTypes = Arrays.asList(productTypes);
        } catch (Exception ex) {
            log.error("getAllProductTypes : " + ex.getMessage(), ex);
        }
        return listProductTypes;
    }

    // "http://ip:port/al-geek-gateway/product/casual-use"
    @GetMapping("/casual-use/kind={computerKind}")
    public List<Produit> getProductForCasualUse(@RequestBody List<Produit> productsList, @PathVariable String computerKind) {
        List<Produit> listProducts = null;
        try {
            Produit[] products = restTemplate.getForObject(productManagerUrl + "/casual-use/kind=" + computerKind, Produit[].class, productsList);
            listProducts = Arrays.asList(products);
        } catch (Exception ex) {
            log.error("getProductForCasualUse : " + ex.getMessage(), ex);
        }
        return listProducts;
    }

    // "http://ip:port/al-geek-gateway/product/regular-use"
    @GetMapping("/regular-use")
    public List<Produit> getProductForRegularUse(List<Produit> productsList, String computerKind) {
        List<Produit> listProducts = null;
        try {
            Produit[] products = restTemplate.getForObject(productManagerUrl + "/regular-use", Produit[].class, productsList, computerKind);
            listProducts = Arrays.asList(products);
        } catch (Exception ex) {
            log.error("getProductForRegularUse : " + ex.getMessage(), ex);
        }
        return listProducts;
    }

    // "http://ip:port/al-geek-gateway/product/intensive-use"
    @GetMapping("/intensive-use")
    public List<Produit> getProductForIntensiveUse(List<Produit> productsList, String computerKind) {
        List<Produit> listProducts = null;
        try {
            Produit[] products = restTemplate.getForObject(productManagerUrl + "/intensive-use", Produit[].class, productsList, computerKind);
            listProducts = Arrays.asList(products);
        } catch (Exception ex) {
            log.error("getProductForIntensiveUse : " + ex.getMessage(), ex);
        }
        return listProducts;
    }

    // "http://ip:port/al-geek-gateway/product/sort/price/ascending"
    @GetMapping("/sort/price/ascending/{id}")
    public List<Produit> sortFromCheapestToMostExpensive(@RequestParam(value = "id", required = false) Long idType) {
        List<Produit> listProducts = null;
        try {
            Produit[] products = restTemplate.getForObject(productManagerUrl + "/sort/price/ascending/" + idType, Produit[].class);
            listProducts = Arrays.asList(products);
        } catch (Exception ex) {
            log.error("sortFromCheapestToMostExpensive : " + ex.getMessage(), ex);
        }
        return listProducts;
    }
}
