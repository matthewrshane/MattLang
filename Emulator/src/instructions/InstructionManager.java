package instructions;

import emulator.Emulator;
import exceptions.InstructionNotFoundException;
import instructions.branch.InstructionBranchAlways;
import instructions.branch.InstructionBranchEqual;
import instructions.branch.InstructionBranchGreaterThan;
import instructions.branch.InstructionBranchLessThan;
import instructions.branch.InstructionBranchNotEqual;
import instructions.compare.InstructionCompareRegister;
import instructions.compare.InstructionCompareValue;
import instructions.load.InstructionLoadMemory;
import instructions.load.InstructionLoadRegister;
import instructions.load.InstructionLoadValue;
import instructions.math.InstructionAddRegister;
import instructions.math.InstructionAddValue;
import instructions.math.InstructionMultiplyRegister;
import instructions.math.InstructionMultiplyValue;
import instructions.math.InstructionSubtractRegister;
import instructions.math.InstructionSubtractValue;
import instructions.misc.InstructionHalt;
import instructions.misc.InstructionNoOp;
import instructions.store.InstructionStoreMemory;
import instructions.store.InstructionStoreMemoryHigh;
import instructions.store.InstructionStoreMemoryLow;
import instructions.store.InstructionStoreRegister;
import instructions.store.InstructionStoreRegisterHigh;
import instructions.store.InstructionStoreRegisterLow;

public class InstructionManager {
    
    public static final byte OPCODE_LOAD_VALUE = 0x00;
    public static final byte OPCODE_LOAD_REGISTER = 0x01;
    public static final byte OPCODE_LOAD_MEMORY = 0x02;
    public static final byte OPCODE_STORE_MEMORY = 0x10;
    public static final byte OPCODE_STORE_MEMORY_LOW = 0x11;
    public static final byte OPCODE_STORE_MEMORY_HIGH = 0x12;
    public static final byte OPCODE_STORE_REGISTER = 0x13;
    public static final byte OPCODE_STORE_REGISTER_LOW = 0x14;
    public static final byte OPCODE_STORE_REGISTER_HIGH = 0x15;
    public static final byte OPCODE_COMPARE_REGISTER = 0x20;
    public static final byte OPCODE_COMPARE_VALUE = 0x21;
    public static final byte OPCODE_BRANCH_EQUAL = 0x30;
    public static final byte OPCODE_BRANCH_GREATER_THAN = 0x31;
    public static final byte OPCODE_BRANCH_LESS_THAN = 0x32;
    public static final byte OPCODE_BRANCH_ALWAYS = 0x33;
    public static final byte OPCODE_BRANCH_NOT_EQUAL = 0x34;
    public static final byte OPCODE_ADD_VALUE = 0x40;
    public static final byte OPCODE_SUBTRACT_VALUE = 0x41;
    public static final byte OPCODE_MULTIPLY_VALUE = 0x42;
    public static final byte OPCODE_ADD_REGISTER = 0x43;
    public static final byte OPCODE_SUBTRACT_REGISTER = 0x44;
    public static final byte OPCODE_MULTIPLY_REGISTER = 0x45;
    public static final byte OPCODE_HALT = (byte) 0xFE;
    public static final byte OPCODE_NO_OP = (byte) 0xFF;

    public static Instruction createInstruction(Emulator emulator, byte opcode, byte register, byte dataHigh, byte dataLow) {
        switch(opcode) {
        case OPCODE_LOAD_VALUE:
            return new InstructionLoadValue(emulator, register, dataHigh, dataLow);
        case OPCODE_LOAD_REGISTER:
            return new InstructionLoadRegister(emulator, register, dataHigh, dataLow);
        case OPCODE_LOAD_MEMORY:
            return new InstructionLoadMemory(emulator, register, dataHigh, dataLow);
        case OPCODE_STORE_MEMORY:
            return new InstructionStoreMemory(emulator, register, dataHigh, dataLow);
        case OPCODE_STORE_MEMORY_LOW:
            return new InstructionStoreMemoryLow(emulator, register, dataHigh, dataLow);
        case OPCODE_STORE_MEMORY_HIGH:
            return new InstructionStoreMemoryHigh(emulator, register, dataHigh, dataLow);
        case OPCODE_STORE_REGISTER:
            return new InstructionStoreRegister(emulator, register, dataHigh, dataLow);
        case OPCODE_STORE_REGISTER_LOW:
            return new InstructionStoreRegisterLow(emulator, register, dataHigh, dataLow);
        case OPCODE_STORE_REGISTER_HIGH:
            return new InstructionStoreRegisterHigh(emulator, register, dataHigh, dataLow);
        case OPCODE_COMPARE_REGISTER:
            return new InstructionCompareRegister(emulator, register, dataHigh, dataLow);
        case OPCODE_COMPARE_VALUE:
            return new InstructionCompareValue(emulator, register, dataHigh, dataLow);
        case OPCODE_BRANCH_EQUAL:
            return new InstructionBranchEqual(emulator, register, dataHigh, dataLow);
        case OPCODE_BRANCH_GREATER_THAN:
            return new InstructionBranchGreaterThan(emulator, register, dataHigh, dataLow);
        case OPCODE_BRANCH_LESS_THAN:
            return new InstructionBranchLessThan(emulator, register, dataHigh, dataLow);
        case OPCODE_BRANCH_ALWAYS:
            return new InstructionBranchAlways(emulator, register, dataHigh, dataLow);
        case OPCODE_BRANCH_NOT_EQUAL:
            return new InstructionBranchNotEqual(emulator, register, dataHigh, dataLow);
        case OPCODE_ADD_VALUE:
            return new InstructionAddValue(emulator, register, dataHigh, dataLow);
        case OPCODE_SUBTRACT_VALUE:
            return new InstructionSubtractValue(emulator, register, dataHigh, dataLow);
        case OPCODE_MULTIPLY_VALUE:
            return new InstructionMultiplyValue(emulator, register, dataHigh, dataLow);
        case OPCODE_ADD_REGISTER:
            return new InstructionAddRegister(emulator, register, dataHigh, dataLow);
        case OPCODE_SUBTRACT_REGISTER:
            return new InstructionSubtractRegister(emulator, register, dataHigh, dataLow);
        case OPCODE_MULTIPLY_REGISTER:
            return new InstructionMultiplyRegister(emulator, register, dataHigh, dataLow);
        case OPCODE_HALT:
            return new InstructionHalt(emulator, register, dataHigh, dataLow);
        case OPCODE_NO_OP:
            return new InstructionNoOp(emulator, register, dataHigh, dataLow);
        default:
            throw new InstructionNotFoundException(opcode);
        }
    }

}
