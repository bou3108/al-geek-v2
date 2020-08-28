package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.dto.Categorie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/product")
@CrossOrigin(origins = "*")
@RestController
public class CategoryRestController {

    private final ResponseEntityRestCommunicator<Categorie> communicator = new ResponseEntityRestCommunicator<Categorie>(Categorie.class, Categorie[].class);

    @Value("${algeek.manager.product.address}")
    private String categoryManagerUrl;


    // http://ip:port/manager/product/root
    @GetMapping("/root")
    public ResponseEntity<Categorie> getRootCategorie() {
        String url = categoryManagerUrl + "/root";
        return communicator.get(url);
    }
}
