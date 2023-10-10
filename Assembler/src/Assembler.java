import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Assembler {
    public static void main(String[] args) throws Exception {
        // Open and read a text file line by line
        // For each line, tokenize the string, read the first byte (opcode), use switch statement
        // In each case, create a byte[4] with the correct opcode, reg#, and 2 data bytes
        // When done with every line, create a new byte[], storing everything in order
        // Save the byte[] to a file (in bytes)

        new Assembler();
    }

    public Assembler() throws URISyntaxException, IOException {
        // ----- Open the source assembly code file (.mlassembly). -----

        // Create and open a FileChooser to allow the user to select a file.
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Open an ml assembly code file.");
        fileChooser.setFileFilter(new FileNameExtensionFilter("MattLang Assembly Files (.mlassembly)", "mlassembly"));
        if(fileChooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) System.exit(0);

        // Get the file that was opened.
        File assemblyFile = fileChooser.getSelectedFile();

        // Create a Scanner to read the file.
        Scanner scanner = new Scanner(assemblyFile);

        // Create an ArrayList of Instruction objects.
        ArrayList<Instruction> instructions = new ArrayList<Instruction>();

        // Create an ArrayList of Label objects.
        ArrayList<Label> labels = new ArrayList<Label>();

        // Store the current line.
        String currentScannedLine = "";

        // Store the lineNum.
        int lineNum = 0;

        // Create an ArrayList of lines.
        ArrayList<String> lines = new ArrayList<String>();

        // Loop through every line.
        while(scanner.hasNextLine()) {
            currentScannedLine = scanner.nextLine();   
            
            // Check if this line is a comment or blank.
            if(currentScannedLine.startsWith(";") || currentScannedLine.isBlank()) continue;

            // Check if this line is a label.
            if(currentScannedLine.startsWith(".")) {
                // Add the label to the list.
                labels.add(new Label(lineNum, currentScannedLine.substring(1)));

                System.out.printf("[DEBUG] Assembler(): Adding label \"%s\".\n", currentScannedLine);

                // Continue to the next line.
                continue;
            }

            // Add the line to the list.
            lines.add(currentScannedLine);

            lineNum++;
        }

        // Close the scanner.
        scanner.close();

        // Store the previous line of instructions.
        String prevLine = "";

        // Store the last size of the instructions.
        int prevSize = instructions.size();

        // Create an ArrayList of instruction lines.
        ArrayList<String> instrLines = new ArrayList<String>();

        // Loop through every line of instructions.
        for(String currentLine : lines) {
            // Tokenize the current line.
            String[] tokens = currentLine.split("[\\s,]+");

            // Check if the current size of instructions is larger than the previous loop iteration.
            if(instructions.size() > prevSize) {
                // The previous line was added as an instruction, so we will add it to the list.
                instrLines.add(prevLine);
            }

            // Set the prevLine to this line.
            prevLine = currentLine;

            // Set the prevSize to the current size of instructions.
            prevSize = instructions.size();

            // Switch on the first token (OP code).
            switch(tokens[0].toUpperCase()) {
                case "LD":
                    if(tokens[2].startsWith("R")) {
                        // 0x01 Load from register
                        instructions.add(new Instruction(0x01, getRegNum(tokens[1]), getRegNum(tokens[2])));
                    } else if(tokens[2].startsWith("$")) {
                        // 0x02 Load from memory
                        instructions.add(new Instruction(0x02, getRegNum(tokens[1]), tokens[2]));
                    } else {
                        // 0x00 Load value
                        instructions.add(new Instruction(0x00, getRegNum(tokens[1]), tokens[2]));
                    }
                    break;
                case "ST":
                    if(tokens[2].startsWith("$")) {
                        // 0x10 Store register in memory
                        instructions.add(new Instruction(0x10, getRegNum(tokens[1]), tokens[2]));
                    } else {
                        // 0x13 Store register in other register
                        instructions.add(new Instruction(0x13, getRegNum(tokens[1]), getRegNum(tokens[2])));
                    }
                    break;
                case "STL":
                    if(tokens[2].startsWith("$")) {
                        // 0x11 Store register in memory (LOW)
                        instructions.add(new Instruction(0x11, getRegNum(tokens[1]), tokens[2]));
                    } else {
                        // 0x14 Store register in other register (LOW)
                        instructions.add(new Instruction(0x14, getRegNum(tokens[1]), getRegNum(tokens[2])));
                    }
                    break;
                case "STH":
                    if(tokens[2].startsWith("$")) {
                        // 0x12 Store register in memory (HIGH)
                        instructions.add(new Instruction(0x12, getRegNum(tokens[1]), tokens[2]));
                    } else {
                        // 0x15 Store register in other register (HIGH)
                        instructions.add(new Instruction(0x15, getRegNum(tokens[1]), getRegNum(tokens[2])));
                    }
                    break;
                case "CMP":
                    if(tokens[2].startsWith("R")) {
                        // 0x20 Compare register to other register
                        instructions.add(new Instruction(0x20, getRegNum(tokens[1]), getRegNum(tokens[2])));
                    } else {
                        // 0x21 Compare register to value
                        instructions.add(new Instruction(0x21, getRegNum(tokens[1]), tokens[2]));
                    }
                    break;
                case "BEQ":
                    // 0x30 Branch if equal to
                    instructions.add(new Instruction(0x30, findLabel(labels, tokens[1]).getLineNum()));
                    break;
                case "BGT":
                    // 0x31 Branch if greater than
                    instructions.add(new Instruction(0x31, findLabel(labels, tokens[1]).getLineNum()));
                    break;
                case "BLT":
                    // 0x32 Branch if less than
                    instructions.add(new Instruction(0x32, findLabel(labels, tokens[1]).getLineNum()));
                    break;
                case "BRA":
                    // 0x33 Branch always
                    instructions.add(new Instruction(0x33, findLabel(labels, tokens[1]).getLineNum()));
                    break;
                case "BNE":
                    // 0x34 Branch if not equal to
                    instructions.add(new Instruction(0x34, findLabel(labels, tokens[1]).getLineNum()));
                    break;
                case "ADD":
                    if(tokens[2].startsWith("R")) {
                        // 0x43 Add register and other register
                        instructions.add(new Instruction(0x43, getRegNum(tokens[1]), getRegNum(tokens[2])));
                    } else {
                        // 0x40 Add register and value
                        instructions.add(new Instruction(0x40, getRegNum(tokens[1]), tokens[2]));
                    }
                    break;
                case "SUB":
                    if(tokens[2].startsWith("R")) {
                        // 0x44 Subtract register and other register
                        instructions.add(new Instruction(0x44, getRegNum(tokens[1]), getRegNum(tokens[2])));
                    } else {
                        // 0x41 Subtract register and value
                        instructions.add(new Instruction(0x41, getRegNum(tokens[1]), tokens[2]));
                    }
                    break;
                case "MULT":
                    if(tokens[2].startsWith("R")) {
                        // 0x45 Multiply register and other register
                        instructions.add(new Instruction(0x45, getRegNum(tokens[1]), getRegNum(tokens[2])));
                    } else {
                        // 0x42 Multiply register and value
                        instructions.add(new Instruction(0x42, getRegNum(tokens[1]), tokens[2]));
                    }
                    break;
                case "HALT":
                    // 0xFE Halt operations
                    instructions.add(new Instruction(0xFE, 0xFF, 0xFE, 0xFF));
                    break;
                case "NOOP":
                    // 0xFF No operation
                    instructions.add(new Instruction(0xFF, 0xFF, 0xFF, 0xFF));
                    break;
                default:
                    // Throw error
            }
        }

        // Create a byte[] to store all the instructions.
        byte[] data = new byte[lineNum * 4];

        // Loop through every instruction and set the instruction bytes into the array.
        for(int i = 0; i < instructions.size(); i++) {
            int[] instructionData = instructions.get(i).getInfo();

            data[(i * 4) + 0] = (byte) instructionData[0];
            data[(i * 4) + 1] = (byte) instructionData[1];
            data[(i * 4) + 2] = (byte) instructionData[2];
            data[(i * 4) + 3] = (byte) instructionData[3];
        }

        // Setup the FileChooser.
        fileChooser.setDialogTitle("Save ml byte code file.");
        fileChooser.setSelectedFile(new File("*.mlbyte"));
        fileChooser.setFileFilter(new FileNameExtensionFilter("MattLang Bytecode Files (.mlbyte)", "mlbyte"));

        // Open the FileChooser to allow the user to save a file.
        if(fileChooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) System.exit(0);

        // Get the file that was saved.
        File byteCodeFile = fileChooser.getSelectedFile();

        // Write the data to the file (.mlbyte)
        try (FileOutputStream outputStream = new FileOutputStream(byteCodeFile)) {
            outputStream.write(data);
        }

        // Print out the entire program with accurate line numbers for debug purposes.
        System.out.println("\n----- [DEBUG] Code printed below. -----");

        // Store an instruction counter.
        int instrNum = 0;

        // Loop through every instruction line and print it.
        for(String instrLine : instrLines) {
            System.out.printf("[%4d] %s\n", instrNum, instrLine);
            instrNum++;
        }
    }

    private byte getRegNum(String token) {
        if(token.length() != 2 || !token.startsWith("R")) return (byte) 0xFF;
        return Byte.parseByte(token.substring(1));
    }

    private Label findLabel(ArrayList<Label> labels, String labelName) {
        for(Label label : labels) {
            if(label.getLabel().equals(labelName)) return label;
        }
        System.out.printf("[Compile Error] Assembler.findLabel(): Cannot find label \"%s\"!\n", labelName);
        return null;
    }
}
