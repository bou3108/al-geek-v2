package fr.afcepf.algeek.web;

import fr.afcepf.algeek.dto.Produit;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@ManagedBean
@SessionScoped
public class FicheProduitBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private long idProduit;
	
	@Getter @Setter
	private Produit produit;
	
	@Getter @Setter
	private int quantite;

	
	public String afficherProduit(Long idProduit) {
		produit = getProduitAvecCaracteristiques(idProduit);
		return "ficheproduit.xhtml?faces-redirect=true";
	}
	
	public String traduireCaracteristique(String cle) {
		return getLocaleString(cle);
	}


	// TODO: Remplace l'appel à getProduitAvecCaracteristiques de ProduitService par un appel REST à product-manager
	public Produit getProduitAvecCaracteristiques(Long productId) {
		return null;
	}

	// TODO: Remplace l'appel à getLocaleString de LocaleService par un appel REST à ???
	public String getLocaleString(String cle) {
		return null;
	}

}
