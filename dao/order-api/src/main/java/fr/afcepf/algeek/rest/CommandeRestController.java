package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.converter.Converter;
import fr.afcepf.algeek.dto.Commande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/commande")
public class CommandeRestController {

    @Autowired
    private fr.afcepf.algeek.dao.CommandeDao commandeDao;

    @GetMapping(value = "/id={id}")
    public ResponseEntity<Commande> getById(@PathVariable Long id) {
        if (commandeDao.findById(id).isPresent()) {
            Commande commande = new Converter().commandeConverter.convertToDTO(
                    commandeDao.findById(id).get());
            return new ResponseEntity<>(commande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
