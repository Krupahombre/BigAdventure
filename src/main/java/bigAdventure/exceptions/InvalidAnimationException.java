package bigAdventure.exceptions;

public class InvalidAnimationException extends RuntimeException{

    public InvalidAnimationException() {
        super("Provided animation name is invalid");
    }

    public InvalidAnimationException(String message) {
        super(message);
    }
}
