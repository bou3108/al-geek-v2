package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.converter.Converter;
import fr.afcepf.algeek.dao.CommandeDao;
import fr.afcepf.algeek.dao.InfosBancairesDao;
import fr.afcepf.algeek.dao.LigneCommandeDao;
import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.entity.CommandeEntity;
import fr.afcepf.algeek.entity.LigneCommandeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/order")
public class CommandeRestController {

    @Autowired
    private LigneCommandeDao ligneCommandeDao;

    @Autowired
    private CommandeDao commandeDao;

    @Autowired
    private CommandeDao commandeDaoDegueu;

    @Autowired
    private InfosBancairesDao infosBancairesDao;

    private Converter converter = new Converter();

    @PostMapping(value = "/add")
    public ResponseEntity<Commande> add(@RequestBody Commande commande) {
        Commande returnedCommande;
        if (commande.getId() == null) {
            CommandeEntity addedEntity = commandeDao.save(converter.commandeConverter.convertToEntity(commande, commandeDao));
            returnedCommande = converter.commandeConverter.convertToDTO(addedEntity);
            return new ResponseEntity<>(returnedCommande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<Commande> delete(@PathVariable Long id) {
        Commande deletedCommande;
        if (commandeDao.findById(id).isPresent()) {
            try {

                for (LigneCommandeEntity ligneCommandeEntity : commandeDao.findById(id).get().getListLigneCommande()) {
                    ligneCommandeDao.delete(ligneCommandeEntity);
                }
                CommandeEntity deletedCommandeEntity = commandeDaoDegueu.findById(id).get();
                deletedCommande = converter.commandeConverter.convertToDTO(deletedCommandeEntity);

                commandeDao.delete(commandeDao.findById(id).get());
                infosBancairesDao.delete(deletedCommandeEntity.getInfosBank());

                return new ResponseEntity<>(deletedCommande, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Commande> update(@RequestBody Commande commande) {
        CommandeEntity commandeEntity = converter.commandeConverter.convertToEntity(commande, commandeDao);
        try {
            commandeEntity = commandeDao.save(commandeEntity);
            return new ResponseEntity<>(converter.commandeConverter.convertToDTO(commandeEntity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/id={id}") // Testé OK dans POSTMAN
    public ResponseEntity<Commande> getById(@PathVariable Long id){
        if (commandeDao.findById(id).isPresent()){
            Commande commande = converter.commandeConverter.convertToDTO(commandeDao.findById(id).get());
            return new ResponseEntity<>(commande, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all") // Testé OK dans POSTMAN
    public List<Commande> getAll(){
        List<CommandeEntity> commandeEntities;
        commandeEntities = (List<CommandeEntity>) commandeDao.findAll();

        List<Commande> commandesDTO = new ArrayList<>();
        for (CommandeEntity commandeEntity : commandeEntities) {
            commandesDTO.add(converter.commandeConverter.convertToDTO(commandeEntity));
        }
        return commandesDTO;
    }
}
