package fr.afcepf.algeek.web;

import fr.afcepf.algeek.dto.Produit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@ManagedBean
@ViewScoped
@Getter @Setter @NoArgsConstructor
public class HomeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Produit> meilleuresVentes;
	
	private List<Produit> nouveautes;

	@PostConstruct
	public void init() {
		meilleuresVentes = getMeilleuresVentes(10);
		nouveautes = getNouveautes();
	}

	// TODO: Remplace l'appel à CatalogueService par un appel à best-sales-orchestrator
	private List<Produit> getMeilleuresVentes(int size) {
		return null;
	}

	// TODO: Remplace l'appel à CatalogueService par un appel à product-manager
	private List<Produit> getNouveautes() {
		return null;
	}
}
