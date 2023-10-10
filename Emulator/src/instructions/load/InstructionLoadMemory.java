package instructions.load;

import emulator.Emulator;
import instructions.Instruction;
import instructions.InstructionManager;

public class InstructionLoadMemory extends Instruction {

    public InstructionLoadMemory(Emulator emulator, byte register, byte dataHigh, byte dataLow) {
        super(emulator, InstructionManager.OPCODE_LOAD_MEMORY, register, dataHigh, dataLow);
    }

    @Override
    public void execute() {
        int index = dataHigh;
        index = index << 8;
        index += dataLow;
        emulator.setRegisterValue(register, 0, emulator.getMemoryByte(index));
    }
    
}
