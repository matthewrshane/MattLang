package instructions.store;

import emulator.Emulator;
import instructions.Instruction;
import instructions.InstructionManager;

public class InstructionStoreMemory extends Instruction {

    public InstructionStoreMemory(Emulator emulator, byte register, byte dataHigh, byte dataLow) {
        super(emulator, InstructionManager.OPCODE_STORE_MEMORY, register, dataHigh, dataLow);
    }

    @Override
    public void execute() {
        int index = dataHigh;
        index = index << 8;
        index += dataLow;
        emulator.setMemoryBytes(index, emulator.getRegisterValue(register));
    }
    
}
