
public class Execute {
	String opcode, rs, rt, immediate, address, shft, result;
	int pc;
	boolean memInstruction, branch, alu, ski;

	public Execute(String opcode, String rs, String rt, String immediate, String address, String shft, int pc) {
		this.opcode = opcode;
		this.rs = rs;
		this.rt = rt;
		this.immediate = immediate;
		this.address = address;
		this.shft = shft;
		this.memInstruction = false;
		this.branch = false;
		this.pc = pc;
	}

	public void execute() {
		ALU alu;
		switch (opcode) {
		case "00000":
			alu = new ALU(opcode, rs, immediate);
			result = alu.result;
			this.ski = false;
			this.alu = true;
			this.memInstruction = false;
			this.branch = false;
			break;
		case "01100":
		case "01101":
			alu = new ALU(opcode, rs, shft);
			result = alu.result;
			this.ski = false;
			this.alu = true;
			this.memInstruction = false;
			this.branch = false;
			break;
		case "00001":
		case "00010":
		case "00101":
		case "00110":
		case "01000":
		case "01001":
		case "01010":
		case "01011":
		case "01111":
		case "00100":
		case "00011":
			alu = new ALU(opcode, rs, rt);
			result = alu.result;
			this.ski = false;
			this.alu = true;
			this.memInstruction = false;
			this.branch = false;
			break;
		case "00111":
			alu = new ALU(opcode, rs, rt);
			result = alu.result;
			if (result.equals("00000000000000000000000000000001")) {
				this.pc = this.pc + 1;
				// Main.setPC(this.pc + 1);
				this.ski = true;
				this.alu = true;
				this.memInstruction = false;
				this.branch = true;
			} else {
				this.ski = false;
				this.alu = true;
				this.memInstruction = false;
				this.branch = false;
			}
			break;
		case "10010":
		case "10011":
			this.alu = false;
			this.ski = false;
			this.memInstruction = true;
			this.branch = false;
			break;
		case "11110":
			alu = new ALU("11110", "" + Integer.parseInt(this.address, 2), "0");
			result = alu.result;
			String ra = "" + this.pc;
			this.alu = true;
			this.ski = false;
			this.branch = true;
			this.memInstruction = false;
			this.pc = Integer.parseInt(result);
			// Main.setPC(Integer.parseInt(result));
			result = Main.intToBinary(ra, 32);
			break;
		case "11000":
			alu = new ALU("11000", "" + Integer.parseInt(this.address, 2), "0");
			result = alu.result;
			this.pc = Integer.parseInt(result);
			// Main.setPC(Integer.parseInt(result));
			this.alu = false;
			this.ski = false;
			this.memInstruction = false;
			this.branch = true;
			break;

		}
	}
}
