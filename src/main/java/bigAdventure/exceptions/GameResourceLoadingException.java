package bigAdventure.exceptions;

public class GameResourceLoadingException extends Exception{

    public GameResourceLoadingException() {
        super("There was a problem while loading game resource");
    }

    public GameResourceLoadingException(String message) {
        super(message);
    }

    public GameResourceLoadingException(Throwable cause) {
        super(cause);
    }
}
