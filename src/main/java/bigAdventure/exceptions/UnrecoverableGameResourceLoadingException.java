package bigAdventure.exceptions;

public class UnrecoverableGameResourceLoadingException extends Exception{
    public UnrecoverableGameResourceLoadingException() {
        super("There was a critical error while trying to load game resources. Aborting!");
    }

    public UnrecoverableGameResourceLoadingException(String message) {
        super("There was a critical error while trying to load game resources. Aborting!: " + message);
    }
}
