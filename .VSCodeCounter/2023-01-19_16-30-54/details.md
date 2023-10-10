# Details

Date : 2023-01-19 16:30:54

Directory c:\\Users\\mattd\\Documents\\Programming\\Java\\MattLang

Total : 37 files,  949 codes, 75 comments, 279 blanks, all 1303 lines

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)

## Files
| filename | language | code | comment | blank | total |
| :--- | :--- | ---: | ---: | ---: | ---: |
| [Assembler/README.md](/Assembler/README.md) | Markdown | 10 | 0 | 9 | 19 |
| [Assembler/bin/Assembler.class](/Assembler/bin/Assembler.class) | Java | 121 | 0 | 1 | 122 |
| [Assembler/bin/Instruction.class](/Assembler/bin/Instruction.class) | Java | 20 | 0 | 0 | 20 |
| [Assembler/bin/Label.class](/Assembler/bin/Label.class) | Java | 7 | 0 | 0 | 7 |
| [Assembler/src/Assembler.java](/Assembler/src/Assembler.java) | Java | 134 | 49 | 29 | 212 |
| [Assembler/src/Instruction.java](/Assembler/src/Instruction.java) | Java | 43 | 0 | 11 | 54 |
| [Assembler/src/Label.java](/Assembler/src/Label.java) | Java | 14 | 0 | 6 | 20 |
| [Emulator/README.md](/Emulator/README.md) | Markdown | 10 | 0 | 9 | 19 |
| [Emulator/src/emulator/Emulator.java](/Emulator/src/emulator/Emulator.java) | Java | 98 | 11 | 32 | 141 |
| [Emulator/src/exceptions/InstructionNotFoundException.java](/Emulator/src/exceptions/InstructionNotFoundException.java) | Java | 6 | 0 | 4 | 10 |
| [Emulator/src/exceptions/InvalidFlagException.java](/Emulator/src/exceptions/InvalidFlagException.java) | Java | 6 | 0 | 4 | 10 |
| [Emulator/src/exceptions/InvalidLabelException.java](/Emulator/src/exceptions/InvalidLabelException.java) | Java | 6 | 0 | 4 | 10 |
| [Emulator/src/exceptions/MemoryOutOfBoundsException.java](/Emulator/src/exceptions/MemoryOutOfBoundsException.java) | Java | 6 | 0 | 4 | 10 |
| [Emulator/src/exceptions/RegisterOutOfBoundsException.java](/Emulator/src/exceptions/RegisterOutOfBoundsException.java) | Java | 6 | 0 | 4 | 10 |
| [Emulator/src/instructions/Instruction.java](/Emulator/src/instructions/Instruction.java) | Java | 14 | 0 | 5 | 19 |
| [Emulator/src/instructions/InstructionManager.java](/Emulator/src/instructions/InstructionManager.java) | Java | 92 | 0 | 6 | 98 |
| [Emulator/src/instructions/branch/InstructionBranchAlways.java](/Emulator/src/instructions/branch/InstructionBranchAlways.java) | Java | 16 | 1 | 7 | 24 |
| [Emulator/src/instructions/branch/InstructionBranchEqual.java](/Emulator/src/instructions/branch/InstructionBranchEqual.java) | Java | 17 | 2 | 8 | 27 |
| [Emulator/src/instructions/branch/InstructionBranchGreaterThan.java](/Emulator/src/instructions/branch/InstructionBranchGreaterThan.java) | Java | 17 | 2 | 8 | 27 |
| [Emulator/src/instructions/branch/InstructionBranchLessThan.java](/Emulator/src/instructions/branch/InstructionBranchLessThan.java) | Java | 17 | 2 | 8 | 27 |
| [Emulator/src/instructions/compare/InstructionCompareRegister.java](/Emulator/src/instructions/compare/InstructionCompareRegister.java) | Java | 22 | 1 | 9 | 32 |
| [Emulator/src/instructions/compare/InstructionCompareValue.java](/Emulator/src/instructions/compare/InstructionCompareValue.java) | Java | 22 | 1 | 9 | 32 |
| [Emulator/src/instructions/load/InstructionLoadMemory.java](/Emulator/src/instructions/load/InstructionLoadMemory.java) | Java | 16 | 0 | 6 | 22 |
| [Emulator/src/instructions/load/InstructionLoadRegister.java](/Emulator/src/instructions/load/InstructionLoadRegister.java) | Java | 13 | 0 | 6 | 19 |
| [Emulator/src/instructions/load/InstructionLoadValue.java](/Emulator/src/instructions/load/InstructionLoadValue.java) | Java | 13 | 0 | 6 | 19 |
| [Emulator/src/instructions/math/InstructionAddRegister.java](/Emulator/src/instructions/math/InstructionAddRegister.java) | Java | 20 | 1 | 9 | 30 |
| [Emulator/src/instructions/math/InstructionAddValue.java](/Emulator/src/instructions/math/InstructionAddValue.java) | Java | 20 | 1 | 9 | 30 |
| [Emulator/src/instructions/math/InstructionSubtractRegister.java](/Emulator/src/instructions/math/InstructionSubtractRegister.java) | Java | 20 | 1 | 9 | 30 |
| [Emulator/src/instructions/math/InstructionSubtractValue.java](/Emulator/src/instructions/math/InstructionSubtractValue.java) | Java | 20 | 1 | 9 | 30 |
| [Emulator/src/instructions/misc/InstructionHalt.java](/Emulator/src/instructions/misc/InstructionHalt.java) | Java | 12 | 1 | 6 | 19 |
| [Emulator/src/instructions/misc/InstructionNoOp.java](/Emulator/src/instructions/misc/InstructionNoOp.java) | Java | 12 | 1 | 6 | 19 |
| [Emulator/src/instructions/store/InstructionStoreMemory.java](/Emulator/src/instructions/store/InstructionStoreMemory.java) | Java | 16 | 0 | 6 | 22 |
| [Emulator/src/instructions/store/InstructionStoreMemoryHigh.java](/Emulator/src/instructions/store/InstructionStoreMemoryHigh.java) | Java | 16 | 0 | 6 | 22 |
| [Emulator/src/instructions/store/InstructionStoreMemoryLow.java](/Emulator/src/instructions/store/InstructionStoreMemoryLow.java) | Java | 16 | 0 | 6 | 22 |
| [Emulator/src/instructions/store/InstructionStoreRegister.java](/Emulator/src/instructions/store/InstructionStoreRegister.java) | Java | 17 | 0 | 6 | 23 |
| [Emulator/src/instructions/store/InstructionStoreRegisterHigh.java](/Emulator/src/instructions/store/InstructionStoreRegisterHigh.java) | Java | 17 | 0 | 6 | 23 |
| [Emulator/src/instructions/store/InstructionStoreRegisterLow.java](/Emulator/src/instructions/store/InstructionStoreRegisterLow.java) | Java | 17 | 0 | 6 | 23 |

[Summary](results.md) / Details / [Diff Summary](diff.md) / [Diff Details](diff-details.md)