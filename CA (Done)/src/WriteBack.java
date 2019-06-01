
public class WriteBack {
	boolean writeBackALU, writeBackLoad, branch, ski;
	String writeReg, aluRes, load;
	int pc;

	public WriteBack(String writeReg, String aluRes, String load, boolean writeBackALU, boolean writeBackLoad,
			boolean branch, int pc, boolean ski) {
		this.writeReg = writeReg;
		this.aluRes = aluRes;
		this.writeBackALU = writeBackALU;
		this.load = load;
		this.writeBackLoad = writeBackLoad;
		this.branch = branch;
		this.pc = pc;
		this.ski = ski;
	}

	public void writeBack() {
		if (writeBackALU) {
			switch (this.writeReg) {
			case "00000":
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "ERROR: Cannot modify this register");
				Main.setPC(pc);
				// System.err.println("Cannot modify this register");
				break;
			case "$00001":
				Main.regs[1] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$ld = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$ld = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "00010":
				Main.regs[2] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$st = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$st = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "00011":
				Main.regs[3] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$v0 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$v0 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "00100":
				Main.regs[4] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$v1 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$v1 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "00101":
				Main.regs[5] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$a0 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$a0 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "00110":
				Main.regs[6] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$a1 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$a1 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "00111":
				Main.regs[7] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$a2 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$a2 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "01000":
				Main.regs[8] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$a3 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$a3 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "01001":
				Main.regs[9] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$t0 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$t0 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "01010":
				Main.regs[10] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$t1 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$t1 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "01011":
				Main.regs[11] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$t2 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$t2 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "01100":
				Main.regs[12] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$t3 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$t3 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "01101":
				Main.regs[13] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$t4 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$t4 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "01110":
				Main.regs[14] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$t5 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$t5 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "01111":
				Main.regs[15] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$t6 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$t6 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "10000":
				Main.regs[16] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$t7 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$t7 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "10001":
				Main.regs[17] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$t8 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$t8 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "10010":
				Main.regs[18] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$t9 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$t9 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "10011":
				Main.regs[19] = aluRes;
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$s0 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$s0 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "10100":
				Main.regs[20] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$s1 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$s1 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "10101":
				Main.regs[21] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$s2 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$s2 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "10110":
				Main.regs[22] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$s3 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$s3 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "10111":
				Main.regs[23] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$s4 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$s4 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "11000":
				Main.regs[24] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$s5 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$s5 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "11001":
				Main.regs[25] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$s6 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$s6 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "11010":
				Main.regs[26] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$s7 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$s7 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "11011":
				Main.regs[27] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$s8 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$s8 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "11100":
				Main.regs[28] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$s9 = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$s9 = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "11101":
				Main.regs[29] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$sp = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$sp = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "11110":
				Main.regs[30] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$gp = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$gp = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			case "11111":
				Main.regs[31] = aluRes;
				Main.setPC(pc);
				Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$ra = " + aluRes
						+ '\n' + "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n'
						+ "Branch = " + branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
				// System.out.println("$ra = " + aluRes);
				// System.out.println("ALU = " + writeBackALU);
				// System.out.println("Branch = " + branch);
				// System.out.println("memInstruction = " + writeBackLoad);
				break;
			}
		}
		if (writeBackLoad) {
			Main.regs[1] = load;
			Main.setPC(pc);
			Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "$ld = " + load + '\n'
					+ "PC = " + pc + '\n' + "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n' + "Branch = "
					+ branch + '\n' + "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
			// System.out.println("$ld = " + Main.regs[1]);
			// System.out.println("ALU = " + writeBackALU);
			// System.out.println("Branch = " + branch);
			// System.out.println("memInstruction = " + writeBackLoad);
		}
		if (branch) {
			Main.setPC(pc);
			Main.instOutput.setText(Main.instOutput.getText() + '\n' + "UPDATED VALUE:" + '\n' + "PC = " + pc + '\n'
					+ "CONTROL FLAGS:" + '\n' + "ALU = " + writeBackALU + '\n' + "Branch = " + branch + '\n'
					+ "memInstruction = " + writeBackLoad + '\n' + "ski = " + ski);
		}
	}
}
