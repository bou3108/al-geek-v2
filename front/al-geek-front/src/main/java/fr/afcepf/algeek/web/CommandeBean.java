package fr.afcepf.algeek.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

import fr.afcepf.algeek.dto.Commande;
import fr.afcepf.algeek.dto.InfosBancaires;
import fr.afcepf.algeek.dto.LigneCommande;
import fr.afcepf.algeek.web.panier.Panier;


import lombok.Getter;
import lombok.Setter;

@ManagedBean
@SessionScoped
public class CommandeBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Inject
	private PanierBean panierBean;
	
	@Inject
	private ConnectBean connectBean;
	
	@Getter @Setter
	private Commande commande;
	
	@Getter @Setter
	private InfosBancaires infosBank;
	
	
	// test
	@Getter @Setter
	private String login;
	
	
	@PostConstruct
	public void init() {
		infosBank = new InfosBancaires();
	}
	
	
//	@Getter
//	private String logoCB = "logoCB.jpg";
//	@Getter
//	private String logoVISA = "logoVISA.jpg";
//	@Getter
//	private String logoMC = "logoMC.jpeg";
	

	
	public String effectuerCommande() {	
		if (connectBean.checkClientConnecte()) {
			return "pageConnexion.xhtml?faces-redirect=true";
		} else {
			commande = new Commande();
			commande.setListLigneCommande(new ArrayList<LigneCommande>());
			commande.setListLigneCommande(panierBean.getPanier().getListLC());
			return "macommande.xhtml?faces-redirect=true";
		}
	}
	
	
	public String validerPaiement() {	
		// persistance des coord.bancaires ( !!! pas bien ...)
		ajouterInformationsBancaire(infosBank);
		
		commande.setClient(connectBean.getClient());
		commande.setDateDeLaCommande(new Date());
		commande.setPrix(panierBean.afficherPrixTotal());
		commande.setInfosBank(infosBank);
		ajouterCommande(commande);
		
		for (LigneCommande lc : commande.getListLigneCommande()) {
			lc.setCommande(commande);
			ajouterLigneDeCommande(lc);
		}
		// reinitialisation du panier
		panierBean.setPanier(new Panier());
		panierBean.getPanier().setListLC(new ArrayList<LigneCommande>());
		infosBank = new InfosBancaires();
		
		return "commandeeffectuee.xhtml?faces-redirect=true";
	}
	
	
	public String boutonDemoCodeBank() {
		infosBank.setTypeCarte("Visa");
		infosBank.setNumCarte("4970597258462195");
		infosBank.setDateCarte(new Date());
		infosBank.setCryptogramme("842");
		return "macommande.xhtml?faces-redirect=true";
		
	}

	// TODO: Remplace les appels à méthode des Services par un appel REST à order-manager
	private void ajouterInformationsBancaire(InfosBancaires infos) {
	}

	private void ajouterCommande(Commande commande) {
	}

	private void ajouterLigneDeCommande(LigneCommande ligneCommande) {

	}
}
