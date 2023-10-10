package instructions.math;

import emulator.Emulator;
import instructions.Instruction;
import instructions.InstructionManager;

public class InstructionAddRegister extends Instruction {

    public InstructionAddRegister(Emulator emulator, byte register, byte dataHigh, byte dataLow) {
        super(emulator, InstructionManager.OPCODE_ADD_REGISTER, register, dataHigh, dataLow);
    }

    @Override
    public void execute() {
        int valueRegister = emulator.getRegisterValue(register)[0];
        valueRegister = valueRegister << 8;
        valueRegister += emulator.getRegisterValue(register)[1];

        int valueRegister2 = emulator.getRegisterValue(dataLow)[0];
        valueRegister2 = valueRegister2 << 8;
        valueRegister2 += emulator.getRegisterValue(dataLow)[1];

        valueRegister += valueRegister2;
        // TODO: Unsigned values? Also flag/exception for overflow?

        emulator.setRegisterValue(register, (byte) (valueRegister >> 8), (byte) (valueRegister & 0xFF));
    }
    
}
