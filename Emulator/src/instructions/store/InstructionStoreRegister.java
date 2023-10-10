package instructions.store;

import emulator.Emulator;
import instructions.Instruction;
import instructions.InstructionManager;

public class InstructionStoreRegister extends Instruction {

    public InstructionStoreRegister(Emulator emulator, byte register, byte dataHigh, byte dataLow) {
        super(emulator, InstructionManager.OPCODE_STORE_REGISTER, register, dataHigh, dataLow);
    }

    @Override
    public void execute() {
        int index = emulator.getRegisterValue(dataLow)[0];
        index = index << 8;
        index += emulator.getRegisterValue(dataLow)[1];
        emulator.setMemoryBytes(index, emulator.getRegisterValue(register));
    }
    
}
