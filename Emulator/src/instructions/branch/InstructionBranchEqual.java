package instructions.branch;

import emulator.Emulator;
import instructions.Instruction;
import instructions.InstructionManager;

public class InstructionBranchEqual extends Instruction {

    public InstructionBranchEqual(Emulator emulator, byte register, byte dataHigh, byte dataLow) {
        super(emulator, InstructionManager.OPCODE_BRANCH_EQUAL, register, dataHigh, dataLow);
    }

    @Override
    public void execute() {
        // Ensure the Z flag is true.
        if(!emulator.getFlag("Z")) return;

        int addr = dataHigh;
        addr = addr << 8;
        addr += dataLow;

        // Set the address to 4 bytes before the desired address, as the emulator will move to the next 4 bytes after this instruction.
        emulator.setCurrentAddress(addr - 4);
    }
    
}
