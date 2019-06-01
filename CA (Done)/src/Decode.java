
public class Decode {
	String opcode, rd, rs, rt, readData1, readData2, writeReg;

	public Decode(String opcode, String rd, String rs, String rt) {
		this.opcode = opcode;
		this.rd = rd;
		this.rs = rs;
		this.rt = rt;
	}

	public void decode() {
		switch (opcode) {
		case "00001":
		case "00010":
		case "00101":
		case "00110":
		case "00111":
		case "01000":
		case "01001":
		case "01010":
		case "01011":
		case "01100":
		case "01101":
		case "01111":
		case "00100":
		case "00011":
			this.readData1 = Main.getValueOfReg(this.rs);
			this.readData2 = Main.getValueOfReg(this.rt);
			this.writeReg = this.rd;
			break;
		case "00000":
			this.readData1 = Main.getValueOfReg(this.rs);
			this.writeReg = this.rd;
			break;
		case "10010":
			this.writeReg = "00001";
			break;
		case "11110":
			this.writeReg = "11111";
			break;
		}
 	}
}
