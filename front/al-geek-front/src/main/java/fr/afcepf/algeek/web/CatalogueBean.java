package fr.afcepf.algeek.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import fr.afcepf.algeek.dto.Marque;
import fr.afcepf.algeek.dto.Produit;

import fr.afcepf.algeek.service.ProduitService;
import fr.afcepf.algeek.service.VenteService;
import fr.afcepf.algeek.tools.tri.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean
@ViewScoped
@Getter @Setter @NoArgsConstructor
public class CatalogueBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Produit> produitsFiltres;
	
	private List<Produit> produits;
	
	private List<Marque> filtreMarques;
	
	private List<Marque> marquesVisibles;
	
	private double filtrePrixMin;
	
	private double filtrePrixMax;
	
	private double prixMinVisible;
	
	private double prixMaxVisible;
	
	private String filtreReference;
	
	@ManagedProperty("#{param.triChoisi}")
	private String triChoisi;
	
	@ManagedProperty("#{param.typeProduitId}")
	private Long typeProduitId;

	@Autowired
	ProduitService produitService;

	@Autowired
	VenteService venteService;
	
	@PostConstruct
	public void init() {
		String paramId = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("typeProduitId");
		String paramTri = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("triChoisi");
		//System.out.println("paramId : " + paramId + " || triChoisi : " + paramTri);
		
		try {
			typeProduitId = Long.parseLong(paramId);
			//System.out.println("typeProduitId : " + typeProduitId);
			
			triChoisi = paramTri;
			//System.out.println("triChoisi : " + triChoisi);
		} catch (Exception e) {
			e.printStackTrace();
			
			typeProduitId = null;
		}
		
		filtreMarques = new ArrayList<Marque>();
		marquesVisibles = new ArrayList<Marque>();
		
		chargerCatalogue();
		
		// TEST
		List<Produit> meilleuresVentes = getMeilleuresVentes(5);
		System.out.println(meilleuresVentes.size());
		
	}
	
	public void chargerCatalogue() {
		produits = getProduitsParType(typeProduitId, false);
		
		System.out.println("Chargement du catalogue : " + produits.size() + " produits trouvés.");
		
		marquesVisibles = new ArrayList<Marque>();
		
		// Préparation des filtres
		double minPrix = -1;
		double maxPrix = -1;

		for (Produit p : produits) {
			Marque m = p.getMarque();
			
			// Ajout des marques
			if (m != null && ! marquesVisibles.contains(m)) {
				marquesVisibles.add(p.getMarque());
			}
			
			// Recherche des prix min et max
			if (minPrix < 0 || minPrix > p.getPrix())
				minPrix = p.getPrix();

			if (maxPrix < 0 || maxPrix < p.getPrix())
				maxPrix = p.getPrix();
		}
		
		// Limites du slider de prix
		prixMinVisible = minPrix > 0 ? minPrix : 0;
		prixMaxVisible = maxPrix > 0 ? maxPrix : 0;
		
		// Selection du slider de prix (par défaut, aux limites du slider)
		filtrePrixMax = prixMaxVisible;
		filtrePrixMin = prixMinVisible;
		
		appliquerFiltres();
	}

	public String afficher(Long id) {
		typeProduitId = id;
		return "catalogue.xhtml?faces-redirect=true&includeViewParams=true";
	}

	public void appliquerFiltres() {
		produitsFiltres = new ArrayList<Produit>();
		
		boolean filtrerParMarques = ! filtreMarques.isEmpty();
		
		for (Produit p : produits) {
			if (p.getPrix() < prixMinVisible || p.getPrix() > prixMaxVisible)
				continue;
			
			if (filtrerParMarques && ! filtreMarques.contains(p.getMarque()))
				continue;
			
			if (filtreReference != null && ! filtreReference.isEmpty())
				if (! p.getNom().toLowerCase().contains(filtreReference.toLowerCase()))
					continue;
			
			produitsFiltres.add(p);
			trierProduits();
		}
		
//		System.out.println("Marques visibles : " + marquesVisibles.size());
//		System.out.println("Filtres marques : " + filtreMarques.size());
//
//		System.out.println("Application des filtres : " + produitsFiltres.size() + " produits trouvés.");
	}
	
	public void trierProduits() {
		if (null != triChoisi && !triChoisi.isEmpty()) {
			if (triChoisi.contentEquals("triNomAlpha")) produitsFiltres.sort(new TriNomAlphabetique());
			if (triChoisi.contentEquals("triPrixCroissant")) produitsFiltres.sort(new TriPrixCroissant());
			if (triChoisi.contentEquals("triPrixDecroissant")) produitsFiltres.sort(new TriPrixDecroissant());
			if (triChoisi.contentEquals("triMarqueCroissant")) produitsFiltres.sort(new TriMarqueCroissant());
			if (triChoisi.contentEquals("triMarqueDecroissant")) produitsFiltres.sort(new TriMarqueDecroissant());
		} else {
			produitsFiltres.sort(new TriNomAlphabetique());
		}
		
		//System.out.println("------------------- tri choisi : " + triChoisi);
	}

	private List<Produit> getMeilleuresVentes(int taille) {
		return venteService.getMeilleuresVentes(taille);
	}

	private List<Produit> getProduitsParType(Long typeProduitId, boolean chargerCaracteristiques) {
		return produitService.getProduitsParType(typeProduitId, chargerCaracteristiques);
	}
}