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
import fr.afcepf.algeek.service.CommandeService;
import fr.afcepf.algeek.web.panier.LignePanier;
import fr.afcepf.algeek.web.panier.Panier;


import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

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

	@Autowired
	CommandeService commandeService;
	
	
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
			commande.setListLigneCommande(panierBean.getPanier().getListLigneCommande());
			return "macommande.xhtml?faces-redirect=true";
		}
	}
	
	
	public String validerPaiement() {	
		commande.setClientId(connectBean.getClient().getId());
		commande.setDateDeLaCommande(new Date());
		commande.setPrix(panierBean.afficherPrixTotal());
		commande.setInfosBank(infosBank);
		Commande commandeAjoutee = ajouterCommande(commande);

		// reinitialisation du panier
		panierBean.setPanier(new Panier());
		panierBean.getPanier().setListLignePanier(new ArrayList<LignePanier>());
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

	private void ajouterInformationsBancaire(InfosBancaires infos) {
		commandeService.ajouterInformationsBancaire(infos);
	}

	private Commande ajouterCommande(Commande commande) {
		return commandeService.ajouterCommande(commande);
	}

	private void ajouterLigneDeCommande(LigneCommande ligneCommande) {
		commandeService.ajouterLigneDeCommande(ligneCommande);
	}
}
