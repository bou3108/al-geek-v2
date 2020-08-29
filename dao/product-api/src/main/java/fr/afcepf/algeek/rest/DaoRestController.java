package fr.afcepf.algeek.rest;

import fr.afcepf.algeek.converter.CaracteristiqueConverter;
import fr.afcepf.algeek.converter.CategorieConverter;
import fr.afcepf.algeek.converter.ProduitConverter;
import fr.afcepf.algeek.converter.TypeProduitConverter;
import fr.afcepf.algeek.dao.CaracteristiqueDao;
import fr.afcepf.algeek.dao.CategorieDao;
import fr.afcepf.algeek.dao.ProduitDao;
import fr.afcepf.algeek.dao.TypeProduitDao;
import fr.afcepf.algeek.dto.Caracteristique;
import fr.afcepf.algeek.dto.Categorie;
import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(headers = "Accept=application/json")
public class DaoRestController {

    @Autowired
    private CaracteristiqueDao caracteristiqueDao;
    @Autowired
    private ProduitDao produitDao;
    @Autowired
    private TypeProduitDao typeProduitDao;
    @Autowired
    private CategorieDao categorieDao;

    private final ProduitConverter produitConverter = new ProduitConverter();
    private final CaracteristiqueConverter caracteristiqueConverter = new CaracteristiqueConverter();
    private final TypeProduitConverter typeProduitConverter = new TypeProduitConverter();
    private final CategorieConverter categorieConverter = new CategorieConverter();

    @PostMapping(value = "/add")
    public ResponseEntity<Produit> ajouterProduit(@RequestBody Produit produit) {
        Produit returnedProduct = null;
        if (produit.getId() == null) {
            fr.afcepf.algeek.entity.Produit addedEntity = produitDao.save(produitConverter.convertToEntity(produit));
            returnedProduct = produitConverter.convertToDTO(addedEntity);
            return new ResponseEntity<Produit>(returnedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<Produit>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/id={id}")
    public ResponseEntity<Produit> supprimerProduit(@PathVariable Long id) {
        Produit deletedProduct = null;
        if (produitDao.findById(id).isPresent()) {
              try {
                  for (fr.afcepf.algeek.entity.Caracteristique c : produitDao.findById(id).get().getCaracteristiques()) {
                      caracteristiqueDao.delete(c);
                  }
                  deletedProduct = produitConverter.convertToDTO(produitDao.findById(id).get());
                  produitDao.delete(produitDao.findById(id).get());
                  return new ResponseEntity<Produit>(deletedProduct, HttpStatus.OK);
              } catch (Exception e) {
                 e.printStackTrace();
                 return new ResponseEntity<Produit>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<Produit>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<Produit> modifierProduit(@RequestBody Produit produit) {
        fr.afcepf.algeek.entity.Produit p = produitConverter.convertToEntity(produit);
        try {
            p = produitDao.save(p);
            return new ResponseEntity<Produit>(produitConverter.convertToDTO(p), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<Produit>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/id={id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id){
        if (produitDao.findById(id).isPresent()){
            Produit product = produitConverter.convertToDTO(produitDao.findById(id).get());
            return new ResponseEntity<Produit>(product, HttpStatus.OK);
        } else {
            return new ResponseEntity<Produit>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<Produit>> getAllProduit(){
        try {
            List<fr.afcepf.algeek.entity.Produit> products = new ArrayList<>();
            products = (List<fr.afcepf.algeek.entity.Produit>) produitDao.findAll();

            List<Produit> productsDTO = new ArrayList<>();
            for (fr.afcepf.algeek.entity.Produit p : products) {
                productsDTO.add(produitConverter.convertToDTO(p));
            }
            return new ResponseEntity<>(productsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/type={id}&with={chargerCaracs}")
    public ResponseEntity<List<Produit>> getProduitParType(@PathVariable Long id, @PathVariable boolean chargerCaracs){
        try {
            List<fr.afcepf.algeek.entity.Produit> products = produitDao.findByTypeId(id);
            List<Produit> productsDTO = new ArrayList<>();

            for (fr.afcepf.algeek.entity.Produit p : products) {
                Produit produitDTO = produitConverter.convertToDTO(p);
                if(chargerCaracs) {
                    List<fr.afcepf.algeek.entity.Caracteristique> caracsEntity = caracteristiqueDao.findByProduit(p);
                    List<Caracteristique> caracteristiques = new ArrayList<>();
                    for (fr.afcepf.algeek.entity.Caracteristique c : caracsEntity) {
                        caracteristiques.add(caracteristiqueConverter.convertToDTO(c));
                    }
                    produitDTO.setCaracteristiques(caracteristiques);
                }
                productsDTO.add(produitDTO);
            }
            return new ResponseEntity<>(productsDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/carac/id={id}")
    public ResponseEntity<List<Caracteristique>> getCaracteristiques (@PathVariable Long id) {
        if (produitDao.findById(id).isPresent()) {
            System.out.println("on rentre dans le if DAO");
            List<fr.afcepf.algeek.entity.Caracteristique> caracEntity = caracteristiqueDao.findByProduit(produitDao.findById(id).get());
            System.out.println("on a récupéré une liste de caractéristiques");
            List<Caracteristique> caracDTO = new ArrayList<>();
            for (fr.afcepf.algeek.entity.Caracteristique c : caracEntity) {
                caracDTO.add(caracteristiqueConverter.convertToDTO(c));
            }
            return new ResponseEntity<>(caracDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/nouveautes")
    public ResponseEntity<List<Produit>> getNouveautes() {
        try {
            List<fr.afcepf.algeek.entity.Produit> nouveautesEntity = produitDao.findTop30ByOrderByDateAjoutDesc();
            List<Produit> nouveautesDTO = new ArrayList<>();
            for (fr.afcepf.algeek.entity.Produit p : nouveautesEntity) {
                nouveautesDTO.add(produitConverter.convertToDTO(p));
            }
            return new ResponseEntity<>(nouveautesDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping(value = "/configure/typeProduit={nomProduit}")
    public ResponseEntity<TypeProduit> getTypeProduit(@PathVariable String nomProduit) {
        try {
            TypeProduit typeProduit = typeProduitConverter.convertToDTO(typeProduitDao.findByNom(nomProduit));
            return new ResponseEntity<>(typeProduit, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value= "/root")
    public ResponseEntity<Categorie> getRootCategorie() {
        try {
            fr.afcepf.algeek.entity.Categorie categorie = categorieDao.findFirstByParentIsNull();
            Categorie cat = categorieConverter.convertToDTO(categorie);
            return new ResponseEntity<>(cat, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
