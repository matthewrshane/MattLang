package exceptions;

public class RegisterOutOfBoundsException extends RuntimeException {

    public RegisterOutOfBoundsException(int index) {
        super(String.format("Register index %d is out of the bounds of the valid registers!", index));
    }
    
}
