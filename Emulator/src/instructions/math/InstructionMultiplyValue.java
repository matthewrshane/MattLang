package instructions.math;

import emulator.Emulator;
import instructions.Instruction;
import instructions.InstructionManager;

public class InstructionMultiplyValue extends Instruction {

    public InstructionMultiplyValue(Emulator emulator, byte register, byte dataHigh, byte dataLow) {
        super(emulator, InstructionManager.OPCODE_MULTIPLY_VALUE, register, dataHigh, dataLow);
    }

    @Override
    public void execute() {
        int valueRegister = emulator.getRegisterValue(register)[0];
        valueRegister = valueRegister << 8;
        valueRegister += emulator.getRegisterValue(register)[1];

        int value = dataHigh;
        value = value << 8;
        value += dataLow;

        valueRegister *= value;
        // TODO: Unsigned values? Also flag/exception for overflow?

        emulator.setRegisterValue(register, (byte) (valueRegister >> 8), (byte) (valueRegister & 0xFF));
    }
    
}
