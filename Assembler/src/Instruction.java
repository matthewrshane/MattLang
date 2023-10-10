public class Instruction {
    
    private int[] info;

    public Instruction(int opcode, int register, String address) {
        if(address.startsWith("$")) address = address.substring(1);

        int addressInteger = 0;
        if(address.startsWith("0x")) addressInteger = Integer.parseInt(address.substring(2), 16);
        else addressInteger = Integer.parseInt(address);
        
        info = new int[] {
            opcode,
            register,
            (addressInteger >> 8),
            (addressInteger & 0xFF)
        };
    }

    public Instruction(int opcode, int register, int register2) {
        info = new int[] {
            opcode,
            register,
            0x00,
            register2
        };
    }

    public Instruction(int opcode, int lineNum) {
        int memoryAddress = lineNum * 4;

        info = new int[] {
            opcode,
            0x00,
            (memoryAddress >> 8),
            (memoryAddress & 0xFF)
        };
    }

    public Instruction(int opcode, int register, int dataHigh, int dataLow) {
        info = new int[] {
            opcode,
            register,
            dataHigh,
            dataLow
        };
    }

    public int[] getInfo() {
        return info;
    }

}
