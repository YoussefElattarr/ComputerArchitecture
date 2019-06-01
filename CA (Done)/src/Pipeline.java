
public class Pipeline {
	String rs, rt, rd, opcode, shft, immediate, address, readData1, readData2, writeReg, pc, aluRes, memInstruction,
			branch, alu, load, store;

	public Pipeline(String opcode, String rs, String rt, String rd, String shft, String immediate, String address,
			String readData1, String readData2, String writeReg, String pc, String aluRes, String memInstruction,
			String branch, String alu, String load, String store) {
		this.opcode = opcode;
		this.rs = rs;
		this.rt = rt;
		this.rd = rd;
		this.shft = shft;
		this.immediate = immediate;
		this.address = address;
		this.readData1 = readData1;
		this.readData2 = readData2;
		this.writeReg = writeReg;
		this.pc = pc;
		this.aluRes = aluRes;
		this.memInstruction = memInstruction;
		this.branch = branch;
		this.alu = alu;
		this.load = load;
		this.store = store;
	}
}
