package instructions.branch;

import emulator.Emulator;
import instructions.Instruction;
import instructions.InstructionManager;

public class InstructionBranchGreaterThan extends Instruction {

    public InstructionBranchGreaterThan(Emulator emulator, byte register, byte dataHigh, byte dataLow) {
        super(emulator, InstructionManager.OPCODE_BRANCH_GREATER_THAN, register, dataHigh, dataLow);
    }

    @Override
    public void execute() {
        // Ensure the GT flag is true.
        if(!emulator.getFlag("GT")) return;

        int addr = dataHigh;
        addr = addr << 8;
        addr += dataLow;

        // Set the address to 4 bytes before the desired address, as the emulator will move to the next 4 bytes after this instruction.
        emulator.setCurrentAddress(addr - 4);
    }
    
}
