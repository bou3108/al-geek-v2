package fr.afcepf.algeek.exception;

public class EntityAlreadyExistingException extends RuntimeException {

    public EntityAlreadyExistingException(){}

    public EntityAlreadyExistingException(String message) {
        super(message);
    }
}
