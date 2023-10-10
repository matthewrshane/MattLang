package instructions.misc;

import emulator.Emulator;
import instructions.Instruction;
import instructions.InstructionManager;

public class InstructionNoOp extends Instruction {

    public InstructionNoOp(Emulator emulator, byte register, byte dataHigh, byte dataLow) {
        super(emulator, InstructionManager.OPCODE_NO_OP, register, dataHigh, dataLow);
    }

    @Override
    public void execute() {
        // This instruction does nothing. No operation is performed.
    }
    
}
