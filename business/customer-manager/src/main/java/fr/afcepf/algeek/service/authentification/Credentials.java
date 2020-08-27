package fr.afcepf.algeek.service.authentification;

public class Credentials {
	private String login;
	private String salt;
	private String hashedPassword;
	
	
	public Credentials() {
		
	}
	
	public Credentials(String login) {
		setLogin(login);
	}
	
	public Credentials(String login, String hashedPassword, String salt) {
		this(login);
		setHashedPassword(hashedPassword);
		setSalt(salt);
	}
	
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
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
