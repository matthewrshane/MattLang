package exceptions;

public class MemoryOutOfBoundsException extends RuntimeException {

    public MemoryOutOfBoundsException(int index) {
        super(String.format("Memory location %d is out of the bounds of the memory!", index));
    }
    
}
