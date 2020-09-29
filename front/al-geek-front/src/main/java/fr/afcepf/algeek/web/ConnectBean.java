package fr.afcepf.algeek.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.afcepf.algeek.dto.Client;
import fr.afcepf.algeek.dto.Meteo;
import fr.afcepf.algeek.service.ClientService;
import fr.afcepf.algeek.service.MeteoService;
import org.springframework.beans.factory.annotation.Autowired;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.client.RestTemplate;

@Named
@SessionScoped
public class ConnectBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private boolean overlayVisible;
	private boolean clientVide;
	
	private Client client = new Client();
	//private static Logger log = LogManager.getLogger();
	private String messageErreur;
	@Getter @Setter
	private Meteo customerMeteo;
	
	@Inject
	private PanierBean panierBean;
	
	public String getMessageErreur() {
		return messageErreur;
	}

	public void setMessageErreur(String messageErreur) {
		this.messageErreur = messageErreur;
	}


	@Getter @Setter
	private String email;
	
	@Getter @Setter
	private String password;

	@Autowired
	ClientService clientService;
	@Autowired
	MeteoService meteoService;

	public String doConnecter() {
		System.out.println("tentative connexion");
		client = connect(email, password);
		if(client==null) {
			System.out.println("erreur du mot de passe");
			return "/home.xhtml?faces-redirect=true";
		}
		System.out.println("Client connecté : " + client.getNom());

		customerMeteo = meteoService.getMeteoByZipCode(client.getCodePostal());
		hideOverlay();
		email = "";
		password= "";
		return  "/home.xhtml?faces-redirect=true";
	}
	
	/*public void testmethode() {
		System.out.println("test methode TOTO");
		Client clientTest = clientService.doConnecter(email, password);
		System.out.println(clientTest.getPrenom());
		System.out.println("fin du test");
	}*/

	public String doDeconnecter() {
		this.client = new Client();
		this.panierBean.viderPanier();
		return "/home.xhtml?faces-redirect=true";
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public String showOverlay() {
		System.out.println("encadré connexion");
		overlayVisible = true;
		return null;
	}
	
	public String hideOverlay() {
		overlayVisible = false;
		return null;
	}

	public boolean isOverlayVisible() {
		return overlayVisible;
	}

	public void setOverlayVisible(boolean overlayVisible) {
		this.overlayVisible = overlayVisible;
	}

	public boolean checkClientConnecte() {
		if(null == client || null == client.getId()) {
			clientVide = true;
		}
		else {
			clientVide = false;
		}
		return clientVide;
	}
	
	public String goToEspacePerso() {
		return "/espaceperso.xhtml?faces-redirect=true";
	}

	public String goToFormulaireInscription() {
		hideOverlay();
		return "/formulaireinscription.xhtml?faces-redirect=true";
		
	}

	public void boutonDemoConnect() {
		email = "adupont@gmail.com";
		//password = "azerty";
		//return "home.xhtml?faces-redirect=true";
	}

	public Client connect(String email, String password) {
		return clientService.connect(email, password);
	}
}
