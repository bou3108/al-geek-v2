package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.converter.Converter;
import fr.afcepf.algeek.dao.CommandeDao;
import fr.afcepf.algeek.dao.InfosBancairesDao;
import fr.afcepf.algeek.dao.LigneCommandeDao;
import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.entity.LigneCommandeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/orderLine")
public class LigneCommandeRestController {

    @Autowired
    private LigneCommandeDao ligneCommandeDao;

    @Autowired
    private CommandeDao commandeDao;

    private Converter converter = new Converter();

    @PostMapping(value = "/add")
    public ResponseEntity<LigneCommande> add(@RequestBody LigneCommande ligneCommande) {
        LigneCommande returnedLigneCommande;
        if (ligneCommande.getId() == null) {
            LigneCommandeEntity addedEntity = ligneCommandeDao.save(converter.ligneCommandeConverter.convertToEntity(ligneCommande,
                    new Converter().commandeConverter.convertToDTO(commandeDao.findById(ligneCommande.getCommandeId()).get())));
            returnedLigneCommande = converter.ligneCommandeConverter.convertToDTO(addedEntity);
            return new ResponseEntity<>(returnedLigneCommande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<LigneCommande> delete(@PathVariable Long id) {
        LigneCommande deletedLigneCommande;
        if (commandeDao.findById(id).isPresent()) {
            try {
                LigneCommandeEntity deletedLigneCommandeEntity = ligneCommandeDao.findById(id).get();
                deletedLigneCommande = converter.ligneCommandeConverter.convertToDTO(deletedLigneCommandeEntity);
                ligneCommandeDao.delete(ligneCommandeDao.findById(id).get());

                return new ResponseEntity<>(deletedLigneCommande, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<LigneCommande> update(@RequestBody LigneCommande ligneCommande) {
        LigneCommandeEntity ligneCommandeEntity = converter.ligneCommandeConverter.convertToEntity(ligneCommande,
                new Converter().commandeConverter.convertToDTO(commandeDao.findById(ligneCommande.getCommandeId()).get()));
        try {
            ligneCommandeEntity = ligneCommandeDao.save(ligneCommandeEntity);
            return new ResponseEntity<>(converter.ligneCommandeConverter.convertToDTO(ligneCommandeEntity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/id={id}") // OK testé dans POSTMAN
    public ResponseEntity<LigneCommande> getById(@PathVariable Long id){
        if (ligneCommandeDao.findById(id).isPresent()){
            LigneCommande ligneCommande = converter.ligneCommandeConverter.convertToDTO(ligneCommandeDao.findById(id).get());
            return new ResponseEntity<>(ligneCommande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all") // Testé OK dans POSTMAN
    public List<LigneCommande> getAll(){
        List<LigneCommandeEntity> ligneCommandeEntities;
        ligneCommandeEntities = (List<LigneCommandeEntity>) ligneCommandeDao.findAll();

        List<LigneCommande> lignesCommandesDTO = new ArrayList<>();
        for (LigneCommandeEntity ligneCommandeEntity : ligneCommandeEntities) {
            lignesCommandesDTO.add(converter.ligneCommandeConverter.convertToDTO(ligneCommandeEntity));
        }
        return lignesCommandesDTO;
    }

    @GetMapping(value = "/order/id={id}")
    public ResponseEntity<List<LigneCommande>> getAllByCommande(@PathVariable Long id) {
        if (commandeDao.findById(id).isPresent()) {
            List<LigneCommande> ligneCommandes = new ArrayList<>();
            Commande commande = converter.commandeConverter.convertToDTO(commandeDao.findById(id).get());
            for (LigneCommande ligneCommande : commande.getListLigneCommande()) {
                LigneCommandeEntity ligneCommandeEntity = ligneCommandeDao.findById(ligneCommande.getId()).get();
                LigneCommande ligneCommandeDto = converter.ligneCommandeConverter.convertToDTO(
                        ligneCommandeEntity);
                ligneCommandes.add(ligneCommandeDto);
            }
            return new ResponseEntity<>(ligneCommandes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
