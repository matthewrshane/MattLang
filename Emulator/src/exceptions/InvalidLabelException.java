package exceptions;

public class InvalidLabelException extends RuntimeException {
    
    public InvalidLabelException(String labelKey) {
        super(String.format("There is no label with the key %s!", labelKey));
    }

}
