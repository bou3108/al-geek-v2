package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Produit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/product", headers = "Accept=application/json")
public class StockController {

    private static RestTemplate restTemplate = new RestTemplate();

    private String stockManagerUrl = "http://ip:port/manager/stock/";

    // "http://ip:port/al-geek-gateway/stock/"
    @GetMapping("")
    public List<Object> getAllStockEntries() {
        List<Object> listStockEntries = null;
        try {
            Object[] entries = restTemplate.getForObject(stockManagerUrl , Object[].class);
            listStockEntries = Arrays.asList(entries);
        } catch (Exception ex) {
            log.error("getAllStockEntries : " + ex.getMessage(), ex);
        }
        return listStockEntries;
    }
}
