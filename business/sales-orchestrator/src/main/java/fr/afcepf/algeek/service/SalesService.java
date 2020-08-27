package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.rest.ResponseEntityRestCommunicator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.stream.Collectors.toMap;

@Slf4j
@Service
public class SalesService {

    private final ResponseEntityRestCommunicator<LigneCommande> orderLinesCommunicator = new ResponseEntityRestCommunicator<LigneCommande>(LigneCommande.class, LigneCommande[].class);
    private final ResponseEntityRestCommunicator<Produit> productCommunicator = new ResponseEntityRestCommunicator<Produit>(Produit.class, Produit[].class);

    @Value("${algeek.manager.product.address}")
    private String productManagerUrl;

    @Value("${algeek.manager.order.address}")
    private String orderManagerUrl;



    public List<Long> getBestSoldProcuctIds(int maxResult) {
        List<LigneCommande> lines = getAllOrderLines();
        if (lines == null) {
            return null;
        }
        return extractBestSoldProcuctIds(lines, maxResult);
    }

    private List<Long> extractBestSoldProcuctIds(List<LigneCommande> lines, Integer maxNumberOfProduct) {
        Map<Long, Integer> salesQuantity = new HashMap<>();

        for (LigneCommande line : lines) {
            Long produitId = line.getProduitId();
            int num = line.getQuantite();

            if (salesQuantity.containsKey(produitId)) {
                salesQuantity.put(produitId, salesQuantity.get(produitId) + num);
            } else {
                salesQuantity.put(produitId, num);
            }
        }

        Map<Long, Integer> sorted = salesQuantity.entrySet().stream()               // Get a stream of Map entry
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))     // Sorted by decreasing value (the quantity)
                .limit(maxNumberOfProduct)                                          // Get only the chosen max number of results
                .collect(toMap(Map.Entry::getKey,
                        Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));  // Ensure the resulted map stays sorted

        List<Long> result = new LinkedList<>(sorted.keySet());      // Get a list of product ids

        return result;
    }


    // "http://ip:port/manager/oerder/all"
    private List<LigneCommande> getAllOrderLines() {
        String url = orderManagerUrl + "/all";
        ResponseEntity<List<LigneCommande>> response = orderLinesCommunicator.getList(url);
        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        }
        return null;
    }

    // "http://ip:port/manager/product/select"
    public ResponseEntity<List<Produit>> getSelectionOfProducts(List<Long> productIdList) {
        String url = productManagerUrl + "/select";
        return productCommunicator.postForList(url, productIdList);
    }
}
