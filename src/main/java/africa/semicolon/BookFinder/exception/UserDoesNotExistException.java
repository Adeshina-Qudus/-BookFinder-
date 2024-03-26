package africa.semicolon.BookFinder.exception;

public class UserDoesNotExistException extends BookFinderException {
    public UserDoesNotExistException(String message) {
        super(message);
    }
}
