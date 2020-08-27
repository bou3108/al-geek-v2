package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.Produit;

import fr.afcepf.algeek.service.SalesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static java.util.stream.Collectors.toMap;

@Slf4j
@CrossOrigin("*")
@RestController
public class SalesRestController {

    private final ResponseEntityRestCommunicator<Commande> orderCommunicator = new ResponseEntityRestCommunicator<Commande>(Commande.class, Commande[].class);
    private final ResponseEntityRestCommunicator<Produit> productCommunicator = new ResponseEntityRestCommunicator<Produit>(Produit.class, Produit[].class);

    @Autowired
    private SalesService salesService;


    // "http://ip:port/manager/sales/best-sales/max={size}"
    @GetMapping("/best-sales")
    public ResponseEntity<List<Produit>> getBestSales(@RequestParam(name = "size", required = true) int size) {
        List<Long> productIdList = salesService.getBestSoldProcuctIds(size);
        if (productIdList == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return salesService.getSelectionOfProducts(productIdList);
    }



}
