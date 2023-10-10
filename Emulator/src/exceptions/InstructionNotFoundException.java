package exceptions;

public class InstructionNotFoundException extends RuntimeException {

    public InstructionNotFoundException(byte opcode) {
        super(String.format("No instruction with opcode %d exists!", opcode));
    }
    
}
