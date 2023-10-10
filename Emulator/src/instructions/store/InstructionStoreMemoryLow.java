package instructions.store;

import emulator.Emulator;
import instructions.Instruction;
import instructions.InstructionManager;

public class InstructionStoreMemoryLow extends Instruction {

    public InstructionStoreMemoryLow(Emulator emulator, byte register, byte dataHigh, byte dataLow) {
        super(emulator, InstructionManager.OPCODE_STORE_MEMORY_LOW, register, dataHigh, dataLow);
    }

    @Override
    public void execute() {
        int index = dataHigh;
        index = index << 8;
        index += dataLow;
        emulator.setMemoryByte(index, emulator.getRegisterValue(register)[1]);
    }
    
}
