package fr.afcepf.algeek.web;

import fr.afcepf.algeek.dto.Produit;
import fr.afcepf.algeek.dto.TypeProduit;
import fr.afcepf.algeek.service.ProduitService;
import fr.afcepf.algeek.web.configurateur.ChoixComposant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
@Getter @Setter @NoArgsConstructor
public class ConfigurateurBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<TypeProduit> typesComposants;
	
	private boolean overlayVisible;
	
	private TypeProduit overlayTypeProduit;
	
	private List<Produit> overlayProduits;
	
	private List<ChoixComposant> selection;
	
	private String messageComposantsManquants;

	@Inject
	private PanierBean panierBean;

	@Autowired
	ProduitService produitService;
	
	@PostConstruct
	public void init() {
		typesComposants = getTypesComposants();
		
		overlayProduits = new ArrayList<Produit>();
		
		this.selection = new ArrayList<>();
		
		for (TypeProduit tp : typesComposants) {
			ChoixComposant choix = new ChoixComposant(tp);
			choix.setObligatoire(! tp.getNom().equals("Carte réseau"));
			this.selection.add(choix);
		}
		
		messageComposantsManquants = "";
	}
	
	public String afficherOverlay(long typeProduitId) {
		try {
			List<Produit> produits = getProduitsParType(typeProduitId, true);

			overlayProduits = new ArrayList<Produit>();

			for (Produit p : produits) {
				boolean compatible = true;


				for (ChoixComposant choix : selection) {
					if (choix == null) {
						System.out.println("CHOIX NULL!!!!!!!!!!!!!!!!!!!");
					}
					if (!estCompatibleAvec(p, choix.getProduit())) {
						compatible = false;
						break;
					}
				}

				if (compatible)
					overlayProduits.add(p);
			}

			overlayVisible = true;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public String fermerOverlay() {
		overlayVisible = false;
		return null;
	}
	
	public String choisirProduit(Produit p) {
		fermerOverlay();
		
		for (ChoixComposant choix : selection) {
			if (choix.getTypeProduit().getId().equals(p.getType().getId())) {
				choix.setProduit(p);
				break;
			}
		}

		try {
			controlerCompatibilite();
		} catch (Exception ex) {
			System.out.println("ERREUR controlerCompatibilite!!!!!!!!!!!!!!!!!!!!!!");
		}
		
		return null;
	}
	
	private void controlerCompatibilite() throws Exception {
		String msgManquants = "Il manque les composants suivants : ";
		int numManquants = 0;
		
		for (ChoixComposant choix1 : selection) {
			if (choix1.getProduit() == null && choix1.isObligatoire()) {
				if (numManquants > 0) {
					msgManquants += ", ";
				}
				
				msgManquants += choix1.getTypeProduit().getNom();				
				numManquants++;
				
				continue;
			}
			
			boolean error = false;
			String message = "Problème de compatibilité avec : ";
			
			for (ChoixComposant choix2 : selection) {
				
				if (! estCompatibleAvec(choix1.getProduit(), choix2.getProduit())) {
					if (error) {
						message += ", ";
					}
					
					message += choix2.getTypeProduit().getNom();
					
					error = true;
				}
			}
			
			choix1.setMessage(error ? message : "");
		}
		
		messageComposantsManquants = (isSelectionVide() || numManquants == 0) ? "" : msgManquants;
	}

	public double getPrixTotal() {
		double prix = 0;
		
		for (ChoixComposant choix : selection) {
			if (choix.getProduit() != null) {
				prix += choix.getProduit().getPrix();
			}
		}
		
		return prix;
	}
	
	public Produit getProduitChoisi(TypeProduit type) {
		for (ChoixComposant choix : selection) {
			if (choix.getProduit() == null)
				continue;
			
			if (choix.getProduit().getType().getId().equals(type.getId())) {
				return choix.getProduit();
			}
		}
		
		return null;
	}
	
	public boolean isProduitChoisi(TypeProduit type) {
		return getProduitChoisi(type) != null;
	}
	
	public String retirerProduit(TypeProduit type) {
		for (ChoixComposant choix : selection) {
			if (choix.getTypeProduit().getId().equals(type.getId())) {
				choix.setProduit(null);
			}				
		}

		try {
			controlerCompatibilite();
		} catch (Exception ex) {
			System.out.println("ERREUR controlerCompatibilite !!!!!!!!!!!!!!!!!!!!!!");
		}
		
		return null;
	}
	
	public boolean isSelectionVide() {
		for (ChoixComposant choix : selection)
			if (choix.getProduit() != null)
				return false;
		
		return true;
	}
	
	public String ajouterAuPanier() {
		List<Produit> produits = new ArrayList<Produit>();
		
		for (ChoixComposant choix : selection) {
			if (choix.getProduit() != null) {
				produits.add(choix.getProduit());
			}
		}
		
		for (Produit p : produits) {
			p.setQuantite(1);
			panierBean.ajoutPanier(p);
		}
		
		init();	
		
		return "votrepanier.xhtml?faces-redirect=true&includeViewParams=true";
	}
	
	public String boutonDemo() {
		selection.get(0).setProduit(getProduitAvecCaracteristiques(1006l));
		selection.get(1).setProduit(getProduitAvecCaracteristiques(1401l));
		selection.get(2).setProduit(getProduitAvecCaracteristiques(307l));
		selection.get(3).setProduit(getProduitAvecCaracteristiques(1105l));
		selection.get(4).setProduit(getProduitAvecCaracteristiques(1201l));
		selection.get(5).setProduit(getProduitAvecCaracteristiques(205l));
		selection.get(6).setProduit(getProduitAvecCaracteristiques(102l));
		selection.get(7).setProduit(getProduitAvecCaracteristiques(3l));
		selection.get(8).setProduit(getProduitAvecCaracteristiques(405l));

		try {
			controlerCompatibilite();
		} catch (Exception ex) {
			System.out.println("ERREUR controlerCompatibilite !!!!!!!!!!!!!!!!!!!!!!");
		}
		
		return null;
	}

    private Produit getProduitAvecCaracteristiques(Long id) {
        return produitService.getProduitAvecCaracteristiques(id);
    }

    private List<Produit> getProduitsParType(Long id, boolean chargerCaracteristiques) {
	    return produitService.getProduitsParType(id, chargerCaracteristiques);
    }

    private boolean estCompatibleAvec(Produit premier, Produit second) throws Exception {
		try {
			Boolean result = produitService.estCompatibleAvec(premier, second);
			if (result == null) {
				throw new  NullPointerException("Mon résultat est NULL");
			}
			return result;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new  Exception(ex);
		}
    }

    private List<TypeProduit> getTypesComposants() {
		return produitService.getTypesComposants();
    }


}
