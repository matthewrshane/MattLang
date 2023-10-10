package exceptions;

public class InvalidFlagException extends RuntimeException {
    
    public InvalidFlagException(String flagKey) {
        super(String.format("There is no flag with the key %s!", flagKey));
    }

}
