package instructions.misc;

import emulator.Emulator;
import instructions.Instruction;
import instructions.InstructionManager;

public class InstructionHalt extends Instruction {

    public InstructionHalt(Emulator emulator, byte register, byte dataHigh, byte dataLow) {
        super(emulator, InstructionManager.OPCODE_HALT, register, dataHigh, dataLow);
    }

    @Override
    public void execute() {
        // TODO: Halt execution until an external interrupt (these don't exist in the emulator yet)
    }
    
}
