package africa.semicolon.BookFinder.exception;

public class UserAlreadyExistException extends BookFinderException {
    public UserAlreadyExistException(String message) {
        super(message);
    }
}
