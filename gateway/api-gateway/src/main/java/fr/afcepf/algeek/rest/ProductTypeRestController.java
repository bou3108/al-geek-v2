package fr.afcepf.algeek.rest;


import fr.afcepf.algeek.dto.TypeProduit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@Slf4j
@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/product")
public class ProductTypeRestController {

    private final ResponseEntityRestCommunicator<TypeProduit> communicator = new ResponseEntityRestCommunicator<TypeProduit>(TypeProduit.class, TypeProduit[].class);

    @Value("${algeek.manager.product.address}")
    private String productManagerUrl;


    // "http://ip:port/al-geek-gateway/product/types/all"
    @GetMapping("/configure/types/all")
    public ResponseEntity<List<TypeProduit>> getAllProductTypes() {
        String url = productManagerUrl + "/configure/types/all";
        return communicator.getList(url);
    }
}
