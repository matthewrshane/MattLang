package instructions;

import emulator.Emulator;

public abstract class Instruction {
    protected Emulator emulator;
    protected int opcode, register, dataHigh, dataLow;

    public Instruction(Emulator emulator, byte opcode, byte register, byte dataHigh, byte dataLow) {
        this.emulator = emulator;
        this.opcode = (opcode & 0xFF);
        this.register = (register & 0xFF);
        this.dataHigh = (dataHigh & 0xFF);
        this.dataLow = (dataLow & 0xFF);
    }

    public abstract void execute();
}
