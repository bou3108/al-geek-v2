package fr.afcepf.algeek.web;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.service.ProduitService;
import fr.afcepf.algeek.service.VenteService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Slf4j
@ManagedBean
@ViewScoped
@Getter @Setter @NoArgsConstructor
public class HomeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Produit> meilleuresVentes;
	
	private List<Produit> nouveautes;

	@Autowired
	ProduitService produitService;

	@Autowired
	VenteService venteService;

	@PostConstruct
	public void init() {
		meilleuresVentes = getBestSales(10);
		nouveautes = getLatestProducts();
	}

	private List<Produit> getBestSales(int size) {
		return venteService.getMeilleuresVentes(size);
	}

	private List<Produit> getLatestProducts() {
		return  produitService.getNouveautes();
	}


}
