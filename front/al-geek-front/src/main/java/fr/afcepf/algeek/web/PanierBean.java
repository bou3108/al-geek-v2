package fr.afcepf.algeek.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.web.panier.LignePanier;
import fr.afcepf.algeek.web.panier.Panier;
import lombok.Getter;
import lombok.Setter;

@ManagedBean
@SessionScoped
public class PanierBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Getter @Setter
	private Panier panier;
	
	@Getter @Setter
	private Long idProdSelected;
	
	private boolean badgeVisible;
	
//	@Getter @Setter
//	private Integer quantite;
	
	@Inject @Getter
	private FicheProduitBean ficheProduitBean;
	
	
	@Getter @Setter
	private String titrePanier = "Panier vide";
	
	
	@Getter @Setter
	private Produit ordiSelected; // ordi selected depuis AchatGuide

	
	@Getter @Setter
	private boolean typeOrdi;  // true = ordi bureau  -  false = ordi portable
	
	
	@PostConstruct
	public void init() {
		panier = new Panier();
		panier.setListLignePanier(new ArrayList<LignePanier>());
	}
	
	
	public String afficherPanier() {
		return "votrepanier.xhtml?faces-redirect=true";
	}
	
	public void ajoutPanier(Produit p) {
		titrePanier = "Votre panier";
		for (LignePanier lc : panier.getListLignePanier()) {
			if(lc.getProduitId().equals(p.getId())) {
				lc.incrementeQuantite(p.getQuantite());
			}
		}
		if(!verifierDansPanier(p)) {
			LignePanier newlp = new LignePanier(p);
			panier.getListLignePanier().add(newlp);
		}
	}
	
	
	public String ajouterAuPanierDepuisCatalogue(int nb, Produit p) {
		p.setQuantite(nb);
		ajoutPanier(p);
		return "catalogue.xhtml?faces-redirect=true&includeViewParams=true";
	}
	
	
	
	public String ajouterAuPanierDepuisAchatGuide() {
		ajoutPanier(ordiSelected);
		return "votrepanier.xhtml?faces-redirect=true";
	}
	
	
	
	
	public String selectionnezOrdiAchatGuide(Produit p) {
//		p.setQuantite(1);
//		ajoutPanier(p);
		typeOrdi = typeOrdiSelected(p);
		ordiSelected = p;
		ajoutPanier(ordiSelected);
		return "achatguideconfirm.xhtml?faces-redirect=true";
	}
	
	
	
	public String ajouterAuPanierDepuisFiche() {
		Produit prodSelected = ficheProduitBean.getProduit(); // null
		ajoutPanier(prodSelected);
		return "ficheproduit.xhtml";
		
	}
	
	public boolean verifierDansPanier(Produit p) {
		boolean b = false;
		for (LignePanier lp : panier.getListLignePanier()) {
			if (lp.getProduitId().equals(p.getId())) {
				return true;
			} 
		}
		return b;
	}	
	
	
	public String retirerProduitDuPanier(Produit p) {
//		for (LigneCommande lcc : panier.getListLC()) {
//			System.out.println(" *** contenu du panier AVANT : " + lcc.getProduit().getNom() + " ( " + lcc.getQuantite() + " ) ");
//		}	
		
		try {
			for (LignePanier lp : panier.getListLignePanier()) {
				if (lp.getProduitId().equals(p.getId())) {

					if (lp.getQuantite() > 1) {
						lp.decrementeQuantite();
					} else {					
						this.panier.getListLignePanier().remove(lp);
						if (panier.getListLignePanier().size() == 0) {
							this.titrePanier = "Panier vide";			
						}
					}
				}
			}
		} catch (ConcurrentModificationException e) {
			e.printStackTrace();
		}
		
//		for (LigneCommande lccc : panier.getListLC()) {
//			System.out.println(" *** contenu du panier APRES : " + lccc.getProduit().getNom() + " ( " + lccc.getQuantite() + " ) ");
//		}	
		
		return "votrepanier.xhtml?faces-redirect=true";
	}
	
	public void viderPanier() {
		panier = new Panier();
		panier.setListLignePanier(new ArrayList<LignePanier>());
	}
	
	public int afficherQuantiteProduitsDansPanier() {
		int qte = 0;
		for (LignePanier lp : panier.getListLignePanier()) {
			qte += lp.getQuantite();
		}
		return qte;
	}
	
	public String badgeQuantiteProduitsDansPanier() {
		Integer qte = afficherQuantiteProduitsDansPanier();
		return qte.toString();
	}

	// TODO: Fix sur le DTO de LigneCommande pour retourner le prix de la ligne
	public double afficherPrixTotal() {
		double somme = 0;
		for (LignePanier lp : panier.getListLignePanier()) {
			somme += lp.getPrixLigne();
		}
		return somme;
	}

	
	// methode utile pour AchatGuide...
	public boolean typeOrdiSelected(Produit p) {
		
		if (p.getType().getId() == 8L) {
			return true;
		} else {
			return false;
		}
	}
	
}
