package fr.afcepf.algeek.web;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import fr.afcepf.algeek.dto.Client;
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

	// TODO: faire un appel REST à customer-manager pour réaliser l'insciption
	public String doInscription() {

//		Client cl= new Client();
//		cl.setNom(nom);
//		cl.setPrenom(prenom);
//		cl.setEmail(email);
//		cl.setAdresse(adresse);
//		cl.setCodePostal(codePostal);
//		cl.setVille(ville);
//
//		Credentials cred = new Credentials();
//
//		try {
//			Authentification.initializeCredentials(cred, password);
//			cl.setSalt(cred.getSalt());
//			cl.setHashedPassword(cred.getHashedPassword());
//			if (cldao.findByEmail(cl.getEmail()) == null) {
//				cldao.save(cl);
//			}
//			else {
//				System.out.println("CETTE EMAIL EXISTE DEJA");
//			}
//		} catch (AuthentificationException e) {
//			return null;
//		}
//
//		connectBean.setClient(cl);
//		return "home.xhtml?faces-redirect=true";

		return null;
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
