package fr.afcepf.algeek.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import fr.afcepf.algeek.dto.Produit;

import fr.afcepf.algeek.service.ProduitService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@ManagedBean
@SessionScoped
public class AchatGuideBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private List<Produit> listPortables;
	
	@Getter @Setter
	private List<Produit> listOrdiBureau;
	
	@Getter @Setter
	private List<Produit> listFiltree;
	
	@Getter @Setter
	private List<Produit> listFiltreeParPrix;
	
	@Getter @Setter
	private boolean overlayVisible;

	@Getter @Setter
	private List<Produit> overlayProduits;

	@Getter @Setter
	private boolean ecranIsSelected = false;
	
	@Getter @Setter
	private boolean clavierIsSelected = false;
	
	@Getter @Setter
	private boolean sourisIsSelected = false;
	
	
	@Inject
	private PanierBean panierBean;

	@Autowired
	private ProduitService produitService;
	
	
	// chargement de tous les ordinateurs (portables / ordi bureau
	public String chargerPortables() {
		listOrdiBureau = new ArrayList<Produit>();
		listPortables = trierMoinsCherAuPlusCher(9L);
		return "achatguideusage.xhtml?faces-redirect=true&includeViewParams=true";
	}
	
	public String chargerOrdiBureau() {
		listPortables = new ArrayList<Produit>();
		listOrdiBureau = trierMoinsCherAuPlusCher(8L);
		return "achatguideusage.xhtml?faces-redirect=true&includeViewParams=true";
	}
	
	
	
	// filtrage par usage
	public String filtrerUsageOccasionnel () {
		if(listOrdiBureau.isEmpty()) {
			String typeOrdi = "ordi_port_usage";
			listFiltree  = new ArrayList<Produit>();
			listFiltree = filtrerUsageOccasionnel(listPortables, typeOrdi);
			for (Produit pr : listFiltree) {
				System.out.println(" *** ordi portable occas' : " + pr.getNom() + pr.getPrix());
			}
		} else {
			String typeOrdi = "ordi_bur_usage";
			listFiltree  = new ArrayList<Produit>();
			listFiltree = filtrerUsageOccasionnel(listOrdiBureau, typeOrdi);
			for (Produit pr : listFiltree) {
				System.out.println(" *** ordi bureau occas' : " + pr.getNom() + pr.getPrix());
			}

		}

		return "achatguidebudget.xhtml?faces-redirect=true";
	}
	
	
	public String filtrerUsageRegulier () {
		if(listOrdiBureau.isEmpty()) {
			String typeOrdi = "ordi_port_usage";
			listFiltree  = new ArrayList<Produit>();
			listFiltree = filtrerUsageRegulier(listPortables, typeOrdi);
			for (Produit pr : listFiltree) {
				System.out.println(" *** ordi portable regul' : " + pr.getNom() + pr.getPrix());
			}
		} else {
			String typeOrdi = "ordi_bur_usage";
			listFiltree  = new ArrayList<Produit>();
			listFiltree = filtrerUsageRegulier(listOrdiBureau, typeOrdi);
			for (Produit pr : listFiltree) {
				System.out.println(" *** ordi bureau regul' : " + pr.getNom() + pr.getPrix());
			}

		}

		return "achatguidebudget.xhtml?faces-redirect=true";
	}
	
	
	public String filtrerUsageIntensif () {
		if(listOrdiBureau.isEmpty()) {
			String typeOrdi = "ordi_port_usage";
			listFiltree  = new ArrayList<Produit>();
			listFiltree = filtrerUsageIntensif(listPortables, typeOrdi);
			for (Produit pr : listFiltree) {
				System.out.println(" *** ordi portable intens'' : " + pr.getNom() + pr.getPrix());
			}
		} else {
			String typeOrdi = "ordi_bur_usage";
			listFiltree  = new ArrayList<Produit>();
			listFiltree = filtrerUsageIntensif(listOrdiBureau, typeOrdi);
			for (Produit pr : listFiltree) {
				System.out.println(" *** ordi bureau intens' : " + pr.getNom() + pr.getPrix());
			}

		}

		return "achatguidebudget.xhtml?faces-redirect=true";
	}
	
	
	
	// filtrage par prix
	public String filtrerPrixBas() {
		listFiltreeParPrix = new ArrayList<Produit>();
		for (Produit produit : listFiltree) {
			if (produit.getPrix() < 500) {
				listFiltreeParPrix.add(produit);
			}
		}
		return "achatguidesuggestion.xhtml?faces-redirect=true";
		
	}
	
	
	public String filtrerPrixMoyen() {
		listFiltreeParPrix = new ArrayList<Produit>();
		for (Produit produit : listFiltree) {
			if (produit.getPrix() > 500 && produit.getPrix() < 1000) {
				listFiltreeParPrix.add(produit);
			}
		}
		return "achatguidesuggestion.xhtml?faces-redirect=true";
		
	}
	
	
	public String filtrerPrixHaut() {
		listFiltreeParPrix = new ArrayList<Produit>();
		for (Produit produit : listFiltree) {
			if (produit.getPrix() > 1000) {
				listFiltreeParPrix.add(produit);
			}
		}
		return "achatguidesuggestion.xhtml?faces-redirect=true";
	}
	

	public String afficherOverlayPlus(long typeProduitId) {
		overlayProduits = trierMoinsCherAuPlusCher(typeProduitId);
		overlayVisible = true;
		return null;
	}
	
	
	public String fermerOverlay() {
		overlayVisible = false;
		return null;
	}
	
	
	public String choisirProduit(Produit p) {
		fermerOverlay();
		panierBean.ajoutPanier(p);		
		System.out.println(" *** produit ADD ajouté : " + p.getType().getNom());
		if (p.getType().getId().equals(7L)){
			ecranIsSelected = true;
		}
		if (p.getType().getId().equals(6L)){
			clavierIsSelected = true;
		}
		if (p.getType().getId().equals(12L)){
			sourisIsSelected = true;
		}
		return null;
	}

	List<Produit> trierMoinsCherAuPlusCher(Long typeProduitId) {
		return produitService.trierMoinsCherAuPlusCher(typeProduitId);
	}

	List<Produit> filtrerUsageIntensif(List<Produit> listOrdi, String typeOrdi) {
		return produitService.filtrerUsageIntensif(listOrdi, typeOrdi);
	}

	List<Produit> filtrerUsageRegulier(List<Produit> listOrdi, String typeOrdi) {
		return produitService.filtrerUsageRegulier(listOrdi, typeOrdi);
	}

	List<Produit> filtrerUsageOccasionnel(List<Produit> listOrdi, String typeOrdi) {
		return produitService.filtrerUsageOccasionnel(listOrdi, typeOrdi);
	}
}
