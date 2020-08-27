package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.converter.Converter;
import fr.afcepf.algeek.dao.InfosBancairesDao;
import fr.afcepf.algeek.dto.InfosBancaires;
import fr.afcepf.algeek.entity.InfosBancairesEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/bankInfos")
public class InfosBancairesRestController {

    @Autowired
    private InfosBancairesDao infosBancairesDao;

    private Converter converter = new Converter();

    @PostMapping(value = "/add")
    public ResponseEntity<InfosBancaires> add(@RequestBody InfosBancaires infosBancaires) {
        InfosBancaires returnedinfosBancaires;
        if (infosBancaires.getId() == null) {
            InfosBancairesEntity addedEntity = infosBancairesDao.save(converter.infosBancairesConverter.convertToEntity(infosBancaires));
            returnedinfosBancaires = converter.infosBancairesConverter.convertToDTO(addedEntity);
            return new ResponseEntity<>(returnedinfosBancaires, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<InfosBancaires> delete(@PathVariable Long id) {
        InfosBancaires deletedInfosBancaires;
        if (infosBancairesDao.findById(id).isPresent()) {
            try {
                InfosBancairesEntity deletedInfosBancairesEntity = infosBancairesDao.findById(id).get();
                deletedInfosBancaires = converter.infosBancairesConverter.convertToDTO(deletedInfosBancairesEntity);
                infosBancairesDao.delete(infosBancairesDao.findById(id).get());

                return new ResponseEntity<>(deletedInfosBancaires, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<InfosBancaires> update(@RequestBody InfosBancaires infosBancaires) {
        InfosBancairesEntity infosBancairesEntity = converter.infosBancairesConverter.convertToEntity(infosBancaires);
        try {
            infosBancairesEntity = infosBancairesDao.save(infosBancairesEntity);
            return new ResponseEntity<>(converter.infosBancairesConverter.convertToDTO(infosBancairesEntity), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/id={id}") // OK testé dans POSTMAN
    public ResponseEntity<InfosBancaires> getById(@PathVariable Long id){
        if (infosBancairesDao.findById(id).isPresent()){
            InfosBancaires infosBancaires = converter.infosBancairesConverter.convertToDTO(infosBancairesDao.findById(id).get());
            return new ResponseEntity<>(infosBancaires, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all") // Testé OK dans POSTMAN
    public List<InfosBancaires> getAll(){
        List<InfosBancairesEntity> infosBancairesEntities;
        infosBancairesEntities = (List<InfosBancairesEntity>) infosBancairesDao.findAll();

        List<InfosBancaires> commandesDTO = new ArrayList<>();
        for (InfosBancairesEntity commandeEntity : infosBancairesEntities) {
            commandesDTO.add(converter.infosBancairesConverter.convertToDTO(commandeEntity));
        }
        return commandesDTO;
    }
}
