package instructions.load;

import emulator.Emulator;
import instructions.Instruction;
import instructions.InstructionManager;

public class InstructionLoadValue extends Instruction {

    public InstructionLoadValue(Emulator emulator, byte register, byte dataHigh, byte dataLow) {
        super(emulator, InstructionManager.OPCODE_LOAD_VALUE, register, dataHigh, dataLow);
    }

    @Override
    public void execute() {
        emulator.setRegisterValue(register, dataHigh, dataLow);
    }
    
}
