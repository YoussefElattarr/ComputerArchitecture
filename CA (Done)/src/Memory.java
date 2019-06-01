
public class Memory {
	boolean memInstruction;
	String opcode;
	String address;
	String fromMemory;
	String storeValue;

	public Memory(String opcode, boolean memInstruction, String address, String storeValue) {
		this.opcode = opcode;
		this.memInstruction = memInstruction;
		this.address = address;
		this.storeValue = storeValue;
	}

	public void memory() {
		if (memInstruction) {
			int add;
			switch (opcode) {
			case "10010":
				add = Integer.parseInt(this.address, 2);
				fromMemory = Main.memory[add];
				break;
			case "10011":
				add = Integer.parseInt(this.address, 2);
				Main.memory[add] = this.storeValue;
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n'
						+ "Memoray at address " + add + " = " + this.storeValue + '\n' + "CONTROL FLAGS:" + '\n'
						+ "ALU = false" + '\n' + "Branch = false" + '\n' + "memInstruction = " + memInstruction);
				// System.out.println("Memoray at address " + add + " = " +
				// this.storeValue);
				// System.out.println("ALU = false");
				// System.out.println("Branch = false");
				// System.out.println("memInstruction = " + memInstruction);
				break;
			}
		}
	}
}
