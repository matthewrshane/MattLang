package instructions.load;

import emulator.Emulator;
import instructions.Instruction;
import instructions.InstructionManager;

public class InstructionLoadRegister extends Instruction {

    public InstructionLoadRegister(Emulator emulator, byte register, byte dataHigh, byte dataLow) {
        super(emulator, InstructionManager.OPCODE_LOAD_REGISTER, register, dataHigh, dataLow);
    }

    @Override
    public void execute() {
        int index = emulator.getRegisterValue(dataLow)[0];
        index = index << 8;
        index += emulator.getRegisterValue(dataLow)[1];
        emulator.setRegisterValue(register, 0, emulator.getMemoryByte(index));
    }
    
}
