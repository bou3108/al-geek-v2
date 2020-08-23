package fr.afcepf.algeek.web;

import java.io.Serializable;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;

import fr.afcepf.algeek.dto.Client;
import fr.afcepf.algeek.dto.Commande;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.Getter;
import lombok.Setter;

@ManagedBean
@ViewScoped
public class EspacePersoBean implements Serializable {

	private static final long serialVersionUID = 1L;


	@Getter @Setter
	private List<Commande> historique;
	
	@Getter @Setter
	private String msgModif;

	@Autowired
	ConnectBean connectBean;
	
	
	@PostConstruct
	public void init(){
		historique = getCommandesPourClient(connectBean.getClient().getId());
		for (Commande commande : historique) {
			System.out.println(commande.getDateDeLaCommande());
		}
		
	}
	
	public void modifInfosClient() {
		modifierClient(connectBean.getClient());
		msgModif = "Modifications enregistrées";
	}


	// TODO: Remplace l'appel à getCommandesPourClient de CommandeService par un appel REST à customer-manager
	private List<Commande> getCommandesPourClient(Long clientId) {
		return null;
	}

	// TODO: Remplace l'appel à modifier de ClientService par un appel REST à customer-manager
	private void modifierClient(Client clientId) {
	}
}
