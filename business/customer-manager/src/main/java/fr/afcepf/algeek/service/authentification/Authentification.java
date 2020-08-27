package fr.afcepf.algeek.service.authentification;

import fr.afcepf.algeek.service.authentification.exception.AuthentificationException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

public class Authentification {
	private static final int SALT_LENGTH = 20;
	private static final int MIN_SALT_CHAR_INDEX = '0';
	private static final int MAX_SALT_CHAR_INDEX = 'z';

	private static Random rand = new Random();

	private static Logger log = LogManager.getLogger();


	private static String createSalt() {
		String chars = "abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder(chars.length());
		for (int i = 0 ; i < SALT_LENGTH ; i++) {
			int x = (int) (Math.random() * chars.length());
			sb.append(chars.charAt(x));
		}
		log.info("Salt crÃ©Ã© avec succÃ¨s");
		return sb.toString();
	}



	private static String generateHash(String toHash) throws AuthentificationException {
		String result = "";
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte [] hashInBytes = md.digest(toHash.getBytes(StandardCharsets.UTF_8));
			StringBuilder sb = new StringBuilder();
			for (byte b : hashInBytes) {
				result += sb.append(String.format("%02x", b));
			}
			return result;
		} catch (NoSuchAlgorithmException e) {
			throw new AuthentificationException("ProblÃ¨me de connexion", e, null);
		}
	}



	public static void initializeCredentials(Credentials cred, String clearPassword) throws AuthentificationException {
		String salt = createSalt();
		cred.setSalt(salt);
		String saltedPassword = clearPassword + salt;
		String hashedPassword = generateHash(saltedPassword);
		cred.setHashedPassword(hashedPassword);
		log.info("Credentials initiÃ© avec succÃ¨s");
	}



	public static boolean authentificate(Credentials cred, String clearPassword) throws AuthentificationException {
		String verif = generateHash(clearPassword + cred.getSalt());
		Boolean success = verif.equals(cred.getHashedPassword());
		log.info(cred.getLogin() + " s'est connÃ©ctÃ© avec succes : " + success);
		if(!success) {
			throw new AuthentificationException("Failure", null, cred.getLogin());
		} 
		return success;

	}

}
