package emulator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import exceptions.InvalidFlagException;
import exceptions.MemoryOutOfBoundsException;
import exceptions.RegisterOutOfBoundsException;
import instructions.InstructionManager;

public class Emulator {

    private static Emulator emulator;

    // Declare constants.
    public static final int MEMORY_SIZE = 65536;
    public static final int NUMBER_OF_REGISTERS = 8;
    public static final String[] FLAG_TYPES = { "Z", "GT", "LT" };

    // Set up global variables.
    private byte[] memory = new byte[MEMORY_SIZE];
    private byte[][] registers = new byte[NUMBER_OF_REGISTERS][2];
    private Map<String, Boolean> flags = new HashMap<String, Boolean>();
    private int currentAddr = 0;

    public static void main(String[] args) throws Exception {
        emulator = new Emulator();
        emulator.runEmulator();
    }

    private void runEmulator() throws IOException {
        // Initialize each flag to false.
        for(String flagKey : FLAG_TYPES) {
            flags.put(flagKey, false);
        }

        // Open and read a binary file (.mlbyte)
        // Read 4 byte chunks to determine the opcode
        // Perform checks on each instruction to ensure everything is within operating parameters (throw custom runtime errors if something is wrong)
        // Execute methods based on the opcode of the instruction

        // Create and open a FileChooser to allow the user to select a file.
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open an ml byte code file.");
        fileChooser.setFileFilter(new FileNameExtensionFilter("MattLang Bytecode Files (.mlbyte)", "mlbyte"));
        if(fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) System.exit(0);

        // Get the file that was opened.
        File byteCodeFile = fileChooser.getSelectedFile();

        // Check to see if the filename is assembler.mlbyte
        if(byteCodeFile.getName().equals("assembler.mlbyte")) {
            // Create and open a FileChooser to allow the user to select a file.
            fileChooser.setDialogTitle("Open an ml assembly file.");
            fileChooser.setFileFilter(new FileNameExtensionFilter("MattLang Assembly Files (.mlassembly)", "mlassembly"));
            if(fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) System.exit(0);

            // Get the file that was opened.
            File assemblyFile = fileChooser.getSelectedFile();

            // Read and store every byte.
            byte[] assemblyText = Files.readAllBytes(assemblyFile.toPath());

            // Set data to the end of the file to stop the program.
            setMemoryByte(assemblyText.length + 0, 13);
            setMemoryByte(assemblyText.length + 1, 10);
            setMemoryByte(assemblyText.length + 2, 3);

            // Loop through every byte and set it into memory.
            for(int i = 0; i < assemblyText.length; i++) {
                setMemoryByte(i, assemblyText[i]);
            }

            for(int i = 0; i < assemblyText.length + 3; i++) {
                // TODO: System.out.printf("Byte: %d, Data: %d\n", i, getMemoryByte(i));
            }
        }

        // Read and store every byte.
        byte[] byteCode = Files.readAllBytes(byteCodeFile.toPath());

        // TODO: Temp testing remove later
        // int countVariable = 0;
        // TODO: END

        // Create and execute each instruction.
        while(currentAddr < byteCode.length) {
            InstructionManager.createInstruction(emulator, byteCode[currentAddr + 0], byteCode[currentAddr + 1], byteCode[currentAddr + 2], byteCode[currentAddr + 3]).execute();
            // TODO: System.out.printf("[DEBUG] Emulator(): Running line %d. [%02x %02x %02x %02x]\n", currentAddr / 4, byteCode[currentAddr + 0] & 0xFF, byteCode[currentAddr + 1] & 0xFF, byteCode[currentAddr + 2] & 0xFF, byteCode[currentAddr + 3] & 0xFF);
            
            // TODO: Temp testing remove later
            // if(currentAddr / 4 >= 236 && currentAddr / 4 <= 239) {
            //     countVariable++;
            // } else {
            //     countVariable = 0;
            // }
            // if(countVariable > 50) break;
            // TODO: END
            
            currentAddr += 4;
        }

        for(int i = 0; i < registers.length; i++) {
            System.out.printf("Register %d: %02x %02x\n", i, registers[i][0], registers[i][1]);
        }

        System.out.print("Hex dump of the system memory: [32768 - 36863]");
        for(int i = 32768; i < 36864; i++) {
            if(i % 16 == 0) {
                System.out.printf("\n[%08x] ", i);
            }

            System.out.printf("%02x ", memory[i]);

            if(i % 4 == 3) {
                System.out.print(" ");
            }
        }
        System.out.println();

        System.out.print("Hex dump of the label table: [36864 - 40959]");
        for(int i = 36864; i < 40960; i++) {
            if(i % 16 == 0) {
                System.out.printf("\n[%08x] ", i);
            }

            System.out.printf("%02x ", memory[i]);

            if(i % 4 == 3) {
                System.out.print(" ");
            }
        }
        System.out.println();

        System.out.print("Hex dump of the stuff: [40960 - 40961]");
        for(int i = 40960; i < 40962; i++) {
            if(i % 16 == 0) {
                System.out.printf("\n[%08x] ", i);
            }

            System.out.printf("%02x ", memory[i]);

            if(i % 4 == 3) {
                System.out.print(" ");
            }
        }

        // Check again to see if the assembler was being run
        if(byteCodeFile.getName().equals("assembler.mlbyte")) {
            // Save the output of the assembler to a file
            byte[] output = Arrays.copyOfRange(memory, 32768, 36864);

            // Setup the FileChooser.
            fileChooser.setDialogTitle("Save ml byte code file.");
            fileChooser.setSelectedFile(new File("*.mlbyte"));
            fileChooser.setFileFilter(new FileNameExtensionFilter("MattLang Bytecode Files (.mlbyte)", "mlbyte"));

            // Open the FileChooser to allow the user to save a file.
            if(fileChooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) System.exit(0);

            // Get the file that was saved.
            File byteCodeFileNew = fileChooser.getSelectedFile();

            // Write the data to the file (.mlbyte)
            try (FileOutputStream outputStream = new FileOutputStream(byteCodeFileNew)) {
                outputStream.write(output);
            }
        }
    }

    public int getMemoryByte(int index) {
        if(index < 0 || index >= MEMORY_SIZE) throw new MemoryOutOfBoundsException(index);
        return (memory[index] & 0xFF);
    }

    public int[] getMemoryBytes(int index, int length) {
        if(index < 0) throw new MemoryOutOfBoundsException(index);
        else if((index + length - 1) >= MEMORY_SIZE) throw new MemoryOutOfBoundsException(index + length - 1);

        int[] data = new int[length];

        for(int i = 0; i < length; i++) {
            data[i] = (memory[index + i] & 0xFF);
        }

        return data;
    }

    public void setMemoryByte(int index, int value) {
        if(index < 0 || index >= MEMORY_SIZE) throw new MemoryOutOfBoundsException(index);
        memory[index] = (byte) value;
    }

    public void setMemoryBytes(int index, int... data) {
        if(index < 0) throw new MemoryOutOfBoundsException(index);
        else if((index + data.length - 1) >= MEMORY_SIZE) throw new MemoryOutOfBoundsException(index + data.length - 1);

        for(int i = 0; i < data.length; i++) {
            memory[index + i] = (byte) data[i];
        }
    }

    public int[] getRegisterValue(int index) {
        if(index < 0 || index >= NUMBER_OF_REGISTERS) throw new RegisterOutOfBoundsException(index);
        int[] out = new int[registers[index].length];
        out[0] = registers[index][0] & 0xFF;
        out[1] = registers[index][1] & 0xFF;
        return out;
    }

    public void setRegisterValue(int index, int... value) {
        if(index < 0 || index >= NUMBER_OF_REGISTERS) throw new RegisterOutOfBoundsException(index);
        if(value.length != 2) return;
        registers[index][0] = (byte) value[0];
        registers[index][1] = (byte) value[1];

        // TODO: if(index == 7) System.out.printf("[DEBUG] Emulator.setRegisterValue(): Setting register R%d to %04x.\n", index, (value[0] << 8) + (value[1] & 0xFF));
    }

    public boolean getFlag(String flagKey) {
        if(!flags.containsKey(flagKey)) throw new InvalidFlagException(flagKey);
        return flags.get(flagKey);
    }

    public void setFlag(String flagKey, boolean value) {
        if(!flags.containsKey(flagKey)) throw new InvalidFlagException(flagKey);
        flags.put(flagKey, value);
    }

    public int getCurrentAddress() {
        return currentAddr;
    }

    public void setCurrentAddress(int currentAddr) {
        this.currentAddr = currentAddr;
    }
}
