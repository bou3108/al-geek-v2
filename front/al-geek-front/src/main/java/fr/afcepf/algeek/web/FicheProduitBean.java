package fr.afcepf.algeek.web;

import fr.afcepf.algeek.dto.Client;
import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.service.LocaleService;
import fr.afcepf.algeek.service.ProduitService;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.annotation.ManagedBean;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

@Slf4j
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

	@Autowired
	private LocaleService localeService;

	@Autowired
	private ProduitService produitService;

	public String afficherProduit(Long idProduit) {
		produit = getProduitAvecCaracteristiques(idProduit);
		return "ficheproduit.xhtml?faces-redirect=true";
	}
	
	public String traduireCaracteristique(String cle) {
		return getLocaleString(cle);
	}

	public Produit getProduitAvecCaracteristiques(Long productId) {
		return produitService.getProduitAvecCaracteristiques(productId);
	}

	public String getLocaleString(String cle) {
		return localeService.getLocaleString(cle);
	}

}
