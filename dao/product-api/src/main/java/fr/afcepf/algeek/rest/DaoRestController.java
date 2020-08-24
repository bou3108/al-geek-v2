package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.converter.ProduitConverter;
import fr.afcepf.algeek.dao.CaracteristiqueDao;
import fr.afcepf.algeek.dao.ProduitDao;
import fr.afcepf.algeek.dto.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController(value = "/catalog")
public class DaoRestController {

    @Autowired
    private CaracteristiqueDao caracteristiqueDao;
    @Autowired
    private ProduitDao produitDao;
    private final ProduitConverter produitConverter = new ProduitConverter();

    @GetMapping(value = "/items/id={id}")
    public Produit getProduitById(@PathVariable Long id){
        Produit product = new ProduitConverter().convertToDTO(produitDao.findById(id).get());
        return product;
    }

    @GetMapping(value = "/items")
    public List<Produit> getAllProduit(){
        List<fr.afcepf.algeek.entity.Produit> products = new ArrayList<>();
        products = (List<fr.afcepf.algeek.entity.Produit>) produitDao.findAll();

        List<Produit> productsDTO = new ArrayList<>();
        for (fr.afcepf.algeek.entity.Produit p : products) {
            productsDTO.add(produitConverter.convertToDTO(p));
        }

        return productsDTO;
    }

    @GetMapping(value = "/items/type={id}")
    public List<Produit> getProduitParType(@PathVariable Long id){
        List<fr.afcepf.algeek.entity.Produit> products = new ArrayList<>();
        products = (List<fr.afcepf.algeek.entity.Produit>) produitDao.findByTypeId(id);

        List<Produit> productsDTO = new ArrayList<>();
        for (fr.afcepf.algeek.entity.Produit p : products) {
            productsDTO.add(produitConverter.convertToDTO(p));
        }

        return productsDTO;
    }
}
