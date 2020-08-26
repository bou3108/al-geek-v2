package fr.afcepf.algeek.service;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;
import fr.afcepf.algeek.dto.composant.*;
import fr.afcepf.algeek.rest.ResponseEntityRestCommunicator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe d'implémentation du ConfiguratorService
 * Cette classe attaque les WebServices de l'application product-api (url stockée en attribut)
 */
@Service
public class ConfiguratorServiceImpl implements ConfiguratorService {

    private ResponseEntityRestCommunicator responseEntityRestCommunicator =
            new ResponseEntityRestCommunicator(TypeProduit.class, TypeProduit[].class);

//    private String urlProductApi = "http://localhost:8080/db/product/configure";
    private String urlProductApi = "http://productapi-env.eba-fts62g3r.eu-west-3.elasticbeanstalk.com/db/product/configure";

    /**
     * Cette méthode retourne une liste d'un certain nombre de composants, en fonction du nom (String) qu'ils contiennent
     * Cette liste est définie dans le corps de la méthode
     *
     * @return
     */
    @Override
    public ResponseEntity<List<TypeProduit>> getTypesComposants() {
        List<TypeProduit> typeProduits = new ArrayList<>();
        List<String> typeProduitsByName = new ArrayList<>();
        String urlGetTypeProduit = urlProductApi + "/typeProduit=";

        typeProduitsByName.add("Processeur");
        typeProduitsByName.add("Ventirad");
        typeProduitsByName.add("Carte mère");
        typeProduitsByName.add("RAM");
        typeProduitsByName.add("SSD");
        typeProduitsByName.add("Carte graphique");
        typeProduitsByName.add("Boitier");
        typeProduitsByName.add("Alimentation");
        typeProduitsByName.add("Carte réseau");

        for (String name : typeProduitsByName) {
            TypeProduit typeProduit = (TypeProduit) responseEntityRestCommunicator.get(urlGetTypeProduit + name).getBody();
            typeProduits.add(typeProduit);
//            addTypeProduitToList(typeProduits, responseEntityRestCommunicator.get(urlGetTypeProduit + name, HttpStatus.BAD_REQUEST));
        }

        if (!typeProduits.isEmpty()) {
            return new ResponseEntity<>(typeProduits, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * Méthode utilitaire de l'application pour déterminer la compatibilité de deux composants
     *
     * @param premier
     * @param second
     * @return
     */
    @Override
    public boolean estCompatibleAvec(Produit premier, Produit second) {
        if (premier == null || second == null) {
            return true;
        }
        if (premier.equals(second)) {
            return true;
        }

        ComposantOrdinateur premierComp = getComposantOrdinateur(premier);
        ComposantOrdinateur secondComp = getComposantOrdinateur(second);
        if (premierComp == null || secondComp == null) {
            return true;
        }

        return premierComp.estCompatibleAvec(secondComp);
    }

    /**
     * Méthode utilitaire de la classe pour instancier un ComposantOrdinateur à partir d'un Produit
     * @param p
     * @return
     */
    private ComposantOrdinateur getComposantOrdinateur(Produit p) {
        String nomType = p.getType().getNom();

        switch (nomType) {
            case "Processeur":
                return new Processeur(p);
            case "Ventirad":
                return new Ventirad(p);
            case "Carte mère":
                return new CarteMere(p);
            case "RAM":
                return new RAM(p);
            case "SSD":
                return new SSD(p);
            case "CarteGraphique":
                return new CarteGraphique(p);
            case "Boitier":
                return new Boitier(p);
            case "Alimentation":
                return new Alimentation(p);
            case "CarteReseau":
                return new CarteReseau(p);
        }

        return null;
    }

    private void addTypeProduitToList (List<TypeProduit> list, ResponseEntity<TypeProduit> response) {
        if (response.getHeaders().containsValue(HttpStatus.OK)) {
            list.add(response.getBody());
        }
    }
}
