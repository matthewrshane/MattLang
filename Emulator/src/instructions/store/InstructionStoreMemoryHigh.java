package instructions.store;

import emulator.Emulator;
import instructions.Instruction;
import instructions.InstructionManager;

public class InstructionStoreMemoryHigh extends Instruction {

    public InstructionStoreMemoryHigh(Emulator emulator, byte register, byte dataHigh, byte dataLow) {
        super(emulator, InstructionManager.OPCODE_STORE_MEMORY_HIGH, register, dataHigh, dataLow);
    }

    @Override
    public void execute() {
        int index = dataHigh;
        index = index << 8;
        index += dataLow;
        emulator.setMemoryByte(index, emulator.getRegisterValue(register)[0]);
    }
    
}
