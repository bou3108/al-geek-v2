package fr.afcepf.algeek.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="nom")
	private String nom;
	
	@Column(name="prenom")
	private String prenom;
	
	@Column(name="email")
	private String email;
	
	@Column(name="login")
	private String login;
	
	@Column(name="hashed_password", length=5000)
	private String hashedPassword;
	
	@Column(name="salt")
	private String salt;
	
	@Column(name="adresse")
	private String adresse;
	
	@Column(name = "codePostal")
	private String codePostal;
	
	@Column(name = "ville")
	private String ville;
	
	
	
	
	
	
	public Client(String nom, String prenom,
                  String email, String login) {
	this.nom = nom;
	this.prenom = prenom;
	this.email = email;
	this.login = login;
	}
	
	

	
	
	public String getVille() {
		return ville;
	}





	public void setVille(String ville) {
		this.ville = ville;
	}





	public Client(String nom, String prenom, String email, String login, String hashedPassword, String salt,
                  String adresse, String codePostal, String ville) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.login = login;
		this.hashedPassword = hashedPassword;
		this.salt = salt;
		this.adresse = adresse;
		this.codePostal = codePostal;
		this.ville = ville;
	}


	public Client() {
	}



	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	
//	public String getClearPassword() {
//		return clearPassword;
//	}
//	public void setClearPassword(String clearPassword) {
//		this.clearPassword = clearPassword;
//	}
	
	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getCodePostal() {
		return codePostal;
	}
	public void setCodePostal(String codePostal) {
		this.codePostal = codePostal;
	}
	public String getHashedPassword() {
		return hashedPassword;
	}
	public void setHashedPassword(String hashedPassword) {
		this.hashedPassword = hashedPassword;
	}
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}

	
	
}
