package fr.afcepf.algeek.web;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.service.ProduitService;
import fr.afcepf.algeek.service.VenteService;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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
		meilleuresVentes = getMeilleuresVentes(10);
		nouveautes = getNouveautes();
	}

	private List<Produit> getMeilleuresVentes(int size) {
		return venteService.getMeilleuresVentes(size);
	}

	private List<Produit> getNouveautes() {
		return  produitService.getNouveautes();
	}
}
