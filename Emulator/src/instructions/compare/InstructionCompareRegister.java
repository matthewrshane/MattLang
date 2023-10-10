package instructions.compare;

import emulator.Emulator;
import instructions.Instruction;
import instructions.InstructionManager;

public class InstructionCompareRegister extends Instruction {

    public InstructionCompareRegister(Emulator emulator, byte register, byte dataHigh, byte dataLow) {
        super(emulator, InstructionManager.OPCODE_COMPARE_REGISTER, register, dataHigh, dataLow);
    }

    @Override
    public void execute() {
        int valueR1 = emulator.getRegisterValue(register)[0];
        valueR1 = valueR1 << 8;
        valueR1 += emulator.getRegisterValue(register)[1];

        int valueR2 = emulator.getRegisterValue(dataLow)[0];
        valueR2 = valueR2 << 8;
        valueR2 += emulator.getRegisterValue(dataLow)[1];

        // How does R1 compare to R2?
        int compare = valueR1 - valueR2;

        emulator.setFlag("Z", compare == 0);
        emulator.setFlag("GT", compare > 0);
        emulator.setFlag("LT", compare < 0);
    }
    
}
