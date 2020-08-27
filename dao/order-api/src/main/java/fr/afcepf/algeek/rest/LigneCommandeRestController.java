package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.converter.Converter;
import fr.afcepf.algeek.dao.LigneCommandeDao;
import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.entity.LigneCommandeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/ligneCommande")
public class LigneCommandeRestController {

    @Autowired
    private LigneCommandeDao ligneCommandeDao;

    @GetMapping(value = "/id={id}")
    public ResponseEntity<LigneCommande> getById(@PathVariable Long id) {
        if (ligneCommandeDao.findById(id).isPresent()) {
            LigneCommande ligneCommande = new Converter().ligneCommandeConverter.convertToDTO(
                    ligneCommandeDao.findById(id).get(), new Commande());
            return new ResponseEntity<>(ligneCommande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
