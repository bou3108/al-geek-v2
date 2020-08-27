package fr.afcepf.algeek;

import fr.afcepf.algeek.dto.Caracteristique;
import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.service.FilterService;
import fr.afcepf.algeek.service.FilterServiceImpl;
import fr.afcepf.algeek.service.ProductService;
import fr.afcepf.algeek.service.ProductServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;

import java.util.List;

@SpringBootApplication
public class ProductManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagerApplication.class, args);
	}

}
