package instructions.compare;

import emulator.Emulator;
import instructions.Instruction;
import instructions.InstructionManager;

public class InstructionCompareValue extends Instruction {

    public InstructionCompareValue(Emulator emulator, byte register, byte dataHigh, byte dataLow) {
        super(emulator, InstructionManager.OPCODE_COMPARE_VALUE, register, dataHigh, dataLow);
    }

    @Override
    public void execute() {
        int valueR1 = emulator.getRegisterValue(register)[0];
        valueR1 = valueR1 << 8;
        valueR1 += emulator.getRegisterValue(register)[1];

        int value = dataHigh;
        value = value << 8;
        value += dataLow;

        // How does R1 compare to the value?
        int compare = valueR1 - value;

        emulator.setFlag("Z", compare == 0);
        emulator.setFlag("GT", compare > 0);
        emulator.setFlag("LT", compare < 0);
    }
    
}
