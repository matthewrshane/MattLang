package instructions.store;

import emulator.Emulator;
import instructions.Instruction;
import instructions.InstructionManager;

public class InstructionStoreRegisterLow extends Instruction {

    public InstructionStoreRegisterLow(Emulator emulator, byte register, byte dataHigh, byte dataLow) {
        super(emulator, InstructionManager.OPCODE_STORE_REGISTER_LOW, register, dataHigh, dataLow);
    }

    @Override
    public void execute() {
        int index = emulator.getRegisterValue(dataLow)[0];
        index = index << 8;
        index += emulator.getRegisterValue(dataLow)[1];
        emulator.setMemoryByte(index, emulator.getRegisterValue(register)[1]);
        // TODO: System.out.printf("[DEBUG] InstructionStoreRegisterLow.execute(): Memory byte %d set to %d. Register has %s.\n", index, emulator.getMemoryByte(index), Arrays.toString(emulator.getRegisterValue(register)));
    }
    
}
