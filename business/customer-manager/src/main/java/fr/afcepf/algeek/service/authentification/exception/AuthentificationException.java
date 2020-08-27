package fr.afcepf.algeek.service.authentification.exception;


import java.io.Serializable;

public class AuthentificationException extends Exception implements Serializable{
	
	private static final long serialVersionUID = 1L;
		
		private String login;
	
		
		public AuthentificationException(String msg, Exception cause, String login){
			super(msg, cause);
			this.login = login;
		}
	
		public String getLogin() {
			return login;
		}
}
