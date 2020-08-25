package fr.afcepf.algeek.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import fr.afcepf.algeek.dto.Client;
import fr.afcepf.algeek.service.ClientService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;


@Named
@SessionScoped
public class InscriptionBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter	@Setter
	private String nom;
	@Getter	@Setter
	private String prenom;
	@Getter	@Setter
	private String email;
	@Getter	@Setter
	private String adresse;
	@Getter	@Setter
	private String codePostal;
	@Getter	@Setter
	private String ville;
	@Getter	@Setter
	private String password;
	
	@Autowired
	ConnectBean connectBean;

	@Autowired
	ClientService clientService;

	public String doInscription() {

		Client client = new Client();
		client.setNom(nom);
		client.setPrenom(prenom);
		client.setEmail(email);
		client.setAdresse(adresse);
		client.setCodePostal(codePostal);
		client.setVille(ville);

		if (!clientService.doInscription(client, password)) {
			return null;
		}

		connectBean.setClient(client);
		return "home.xhtml?faces-redirect=true";
	}
	
	public String boutonDemoNewClient() {
		 nom = "Durand";
		 prenom = "Jean";
		 adresse = "125 avenue Clémenceau";
		 codePostal = "33000";
		 ville = "Bordeaux";
		 email = "jeandurand@yahoo.fr";
		 password = "toto123";
		return "formulaireinscription.xhtml?faces-redirect=true";
		
	}
}
