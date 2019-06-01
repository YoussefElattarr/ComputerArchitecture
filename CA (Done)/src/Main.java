import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {
	static int pc;
	static String[] memory;
	static String[] regs;
	static Instruction[] instructionsMemory;
	static int instructionsMemoryIndex;
	static boolean memInstruction;
	static boolean branch;
	static boolean alu;
	static Queue<Instruction> fetchedInstructions;
	static int clockCycles;
	JPanel inst;
	JPanel registers;
	JPanel output;
	JPanel input;
	JButton add;
	JButton exec;
	JTextArea instInput;
	static JTextArea instOutput;
	JTextArea userInput;
	JTextArea regsInfo;
	JScrollPane spo;
	JScrollPane spi;
	JScrollPane spr;
	String[] IFID = new String[18];
	String[] IDEX = new String[18];
	String[] EXMEM = new String[18];
	String[] MEMWB = new String[18];

	public Main() {
		clockCycles = 0;
		pc = 0;
		memory = new String[134217728];
		for (int i = 0; i < memory.length; i++)
			memory[i] = "00000000000000000000000000000000";
		regs = new String[32];
		for (int i = 0; i < regs.length; i++)
			regs[i] = "00000000000000000000000000000000";
		instructionsMemory = new Instruction[134217728];
		instructionsMemoryIndex = 0;
		fetchedInstructions = new LinkedList<Instruction>();
		setTitle("Court Of Owls");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setBounds(50, 50, 1200, 600);
		inst = new JPanel();
		registers = new JPanel();
		output = new JPanel();
		input = new JPanel();
		add = new JButton("ADD");
		exec = new JButton("EXECUTE");
		regsInfo = new JTextArea(updateRegsValues());
		regsInfo.setEditable(false);
		instInput = new JTextArea("Your instructions here...");
		instInput.setEditable(false);
		instOutput = new JTextArea("Your output here...");
		instOutput.setEditable(false);
		userInput = new JTextArea();
		userInput.setEditable(true);
		userInput.addFocusListener(new FocusListener() {
			@Override
			public void focusLost(FocusEvent arg0) {
				if (userInput.getText().equals("Add your instructions")) {
					userInput.setText("");
				}
			}

			@Override
			public void focusGained(FocusEvent arg0) {
				if (userInput.getText().equals("")) {
					userInput.setText("Add your instructions");
				}
			}
		});
		inst.setPreferredSize(new Dimension(400, 500));
		output.setPreferredSize(new Dimension(400, 500));
		input.setPreferredSize(new Dimension(this.getWidth(), 100));
		userInput.setPreferredSize(new Dimension(800, 100));
		spi = new JScrollPane(instInput, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spi.setPreferredSize(new Dimension(350, 450));
		spo = new JScrollPane(instOutput, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spr = new JScrollPane(regsInfo, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		spr.setPreferredSize(new Dimension(350, 450));
		spo.setPreferredSize(new Dimension(350, 450));
		add(registers, BorderLayout.WEST);
		add(inst, BorderLayout.CENTER);
		add(output, BorderLayout.EAST);
		add(input, BorderLayout.SOUTH);
		registers.add(spr);
		inst.add(spi);
		output.add(spo);
		input.add(userInput, BorderLayout.WEST);
		input.add(add);
		input.add(exec);
		add.addActionListener(this);
		exec.addActionListener(this);
		setVisible(true);
	}

	public static String updateRegsValues() {
		String res = "$zero  | " + Main.regs[0] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$ld      | " + Main.regs[1] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$st      | " + Main.regs[2] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$v0     | " + Main.regs[3] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$v1     | " + Main.regs[4] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$a0    | " + Main.regs[5] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$a1    | " + Main.regs[6] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$a2    | " + Main.regs[7] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$a3    | " + Main.regs[8] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$t0     | " + Main.regs[9] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$t1     | " + Main.regs[10] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$t2     | " + Main.regs[11] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$t3     | " + Main.regs[12] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$t4     | " + Main.regs[13] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$t5     | " + Main.regs[14] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$t6     | " + Main.regs[15] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$t7     | " + Main.regs[16] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$t8     | " + Main.regs[17] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$t9     | " + Main.regs[18] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$s0    | " + Main.regs[19] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$s1    | " + Main.regs[20] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$s2    | " + Main.regs[21] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$s3    | " + Main.regs[22] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$s4    | " + Main.regs[23] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$s5    | " + Main.regs[24] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$s6    | " + Main.regs[25] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$s7    | " + Main.regs[26] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$s8    | " + Main.regs[27] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$s9    | " + Main.regs[28] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$sp    | " + Main.regs[29] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$gp    | " + Main.regs[30] + '\n'
				+ "---------------------------------------------------------------------------------------" + '\n'
				+ "$ra     | " + Main.regs[31];
		return res;
	}

	public static int getPC() {
		return Main.pc;
	}

	public static void setPC(int value) {
		Main.pc = value;
	}

	public static String getValueOfReg(String s) {
		String res = "";
		switch (s) {
		case "00000":
			res = Main.regs[0];
			break;
		case "00001":
			res = Main.regs[1];
			break;
		case "00010":
			res = Main.regs[2];
			break;
		case "00011":
			res = Main.regs[3];
			break;
		case "00100":
			res = Main.regs[4];
			break;
		case "00101":
			res = Main.regs[5];
			break;
		case "00110":
			res = Main.regs[6];
			break;
		case "00111":
			res = Main.regs[7];
			break;
		case "01000":
			res = Main.regs[8];
			break;
		case "01001":
			res = Main.regs[9];
			break;
		case "01010":
			res = Main.regs[10];
			break;
		case "01011":
			res = Main.regs[11];
			break;
		case "01100":
			res = Main.regs[12];
			break;
		case "01101":
			res = Main.regs[13];
			break;
		case "01110":
			res = Main.regs[14];
			break;
		case "01111":
			res = Main.regs[15];
			break;
		case "10000":
			res = Main.regs[16];
			break;
		case "10001":
			res = Main.regs[17];
			break;
		case "10010":
			res = Main.regs[18];
			break;
		case "10011":
			res = Main.regs[19];
			break;
		case "10100":
			res = Main.regs[20];
			break;
		case "10101":
			res = Main.regs[21];
			break;
		case "10110":
			res = Main.regs[22];
			break;
		case "10111":
			res = Main.regs[23];
			break;
		case "11000":
			res = Main.regs[24];
			break;
		case "11001":
			res = Main.regs[25];
			break;
		case "11010":
			res = Main.regs[26];
			break;
		case "11011":
			res = Main.regs[27];
			break;
		case "11100":
			res = Main.regs[28];
			break;
		case "11101":
			res = Main.regs[29];
			break;
		case "11110":
			res = Main.regs[30];
			break;
		case "11111":
			res = Main.regs[31];
			break;
		}
		return res;
	}

	public String getAddress(String s) {
		String res = "";
		switch (s) {
		case "$zero":
			res = Main.regs[0].substring(5);
			break;
		case "$ld":
			res = Main.regs[1].substring(5);
			break;
		case "$st":
			res = Main.regs[2].substring(5);
			break;
		case "$v0":
			res = Main.regs[3].substring(5);
			break;
		case "$v1":
			res = Main.regs[4].substring(5);
			break;
		case "$a0":
			res = Main.regs[5].substring(5);
			break;
		case "$a1":
			res = Main.regs[6].substring(5);
			break;
		case "$a2":
			res = Main.regs[7].substring(5);
			break;
		case "$a3":
			res = Main.regs[8].substring(5);
			break;
		case "$t0":
			res = Main.regs[9].substring(5);
			break;
		case "$t1":
			res = Main.regs[10].substring(5);
			break;
		case "$t2":
			res = Main.regs[11].substring(5);
			break;
		case "$t3":
			res = Main.regs[12].substring(5);
			break;
		case "$t4":
			res = Main.regs[13].substring(5);
			break;
		case "$t5":
			res = Main.regs[14].substring(5);
			break;
		case "$t6":
			res = Main.regs[15].substring(5);
			break;
		case "$t7":
			res = Main.regs[16].substring(5);
			break;
		case "$t8":
			res = Main.regs[17].substring(5);
			break;
		case "$t9":
			res = Main.regs[18].substring(5);
			break;
		case "$s0":
			res = Main.regs[19].substring(5);
			break;
		case "$s1":
			res = Main.regs[20].substring(5);
			break;
		case "$s2":
			res = Main.regs[21].substring(5);
			break;
		case "$s3":
			res = Main.regs[22].substring(5);
			break;
		case "$s4":
			res = Main.regs[23].substring(5);
			break;
		case "$s5":
			res = Main.regs[24].substring(5);
			break;
		case "$s6":
			res = Main.regs[25].substring(5);
			break;
		case "$s7":
			res = Main.regs[26].substring(5);
			break;
		case "$s8":
			res = Main.regs[27].substring(5);
			break;
		case "$s9":
			res = Main.regs[28].substring(5);
			break;
		case "$sp":
			res = Main.regs[29].substring(5);
			break;
		case "$gp":
			res = Main.regs[30].substring(5);
			break;
		case "$ra":
			res = Main.regs[31].substring(5);
			break;
		}
		return res;
	}

	public void addInstruction(Instruction instruction) {
		String res = "";
		StringTokenizer st = new StringTokenizer(instruction.statement);
		ArrayList<String> inst = new ArrayList<String>();
		while (st.hasMoreTokens()) {
			inst.add(st.nextToken());
		}
		String operation = inst.get(0);
		String opcode, R1, R2, R3, immediate, shft;
		switch (operation) {
		case "ADDI":
			opcode = "00000";
			R1 = getRegister(inst.get(1));
			R2 = getRegister(inst.get(2));
			if (R1 == null || R2 == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			immediate = intToBinary(inst.get(3), 17);
			res = opcode + R1 + R2 + immediate;
			break;
		case "ADD":
			opcode = "00001";
			R1 = getRegister(inst.get(1));
			R2 = getRegister(inst.get(2));
			R3 = getRegister(inst.get(3));
			if (R1 == null || R2 == null || R3 == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			shft = "00000";
			res = opcode + R1 + R2 + R3 + shft + "0000000";
			break;
		case "SUB":
			opcode = "00010";
			R1 = getRegister(inst.get(1));
			R2 = getRegister(inst.get(2));
			R3 = getRegister(inst.get(3));
			if (R1 == null || R2 == null || R3 == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			shft = "00000";
			res = opcode + R1 + R2 + R3 + shft + "0000000";
			break;
		case "LOAD":
			opcode = "10010";
			R1 = inst.get(1);
			if (this.getAddress(R1) == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			// R1 = getRegister(inst.get(1));
			res = opcode + this.getAddress(R1);
			break;
		case "STORE":
			opcode = "10011";
			R1 = inst.get(1);
			if (this.getAddress(R1) == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			// R1 = getRegister(inst.get(1));
			res = opcode + this.getAddress(R1);
			break;
		case "SLT":
			opcode = "00101";
			R1 = getRegister(inst.get(1));
			R2 = getRegister(inst.get(2));
			R3 = getRegister(inst.get(3));
			if (R1 == null || R2 == null || R3 == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			shft = "00000";
			res = opcode + R1 + R2 + R3 + shft + "0000000";
			break;
		case "SEQ":
			opcode = "00110";
			R1 = getRegister(inst.get(1));
			R2 = getRegister(inst.get(2));
			R3 = getRegister(inst.get(3));
			if (R1 == null || R2 == null || R3 == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			shft = "00000";
			res = opcode + R1 + R2 + R3 + shft + "0000000";
			break;
		case "SKI":
			opcode = "00111";
			R1 = getRegister(inst.get(1));
			R2 = getRegister(inst.get(2));
			R3 = getRegister(inst.get(3));
			if (R1 == null || R2 == null || R3 == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			shft = "00000";
			res = opcode + R1 + R2 + R3 + shft + "0000000";
			break;
		case "AND":
			opcode = "01000";
			R1 = getRegister(inst.get(1));
			R2 = getRegister(inst.get(2));
			R3 = getRegister(inst.get(3));
			if (R1 == null || R2 == null || R3 == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			shft = "00000";
			res = opcode + R1 + R2 + R3 + shft + "0000000";
			break;
		case "OR":
			opcode = "01001";
			R1 = getRegister(inst.get(1));
			R2 = getRegister(inst.get(2));
			R3 = getRegister(inst.get(3));
			if (R1 == null || R2 == null || R3 == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			shft = "00000";
			res = opcode + R1 + R2 + R3 + shft + "0000000";
			break;
		case "NOR":
			opcode = "01010";
			R1 = getRegister(inst.get(1));
			R2 = getRegister(inst.get(2));
			R3 = getRegister(inst.get(3));
			if (R1 == null || R2 == null || R3 == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			shft = "00000";
			res = opcode + R1 + R2 + R3 + shft + "0000000";
			break;
		case "NOT":
			opcode = "01011";
			R1 = getRegister(inst.get(1));
			R2 = getRegister(inst.get(2));
			if (R1 == null || R2 == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			R3 = "00000";
			shft = "00000";
			res = opcode + R1 + R2 + R3 + shft + "0000000";
			break;
		case "SHIFTL":
			opcode = "01100";
			R1 = getRegister(inst.get(1));
			R2 = getRegister(inst.get(2));
			if (R1 == null || R2 == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			R3 = "00000";
			shft = intToBinary(inst.get(3), 5);
			res = opcode + R1 + R2 + R3 + shft + "0000000";
			break;
		case "SHIFTR":
			opcode = "01101";
			R1 = getRegister(inst.get(1));
			R2 = getRegister(inst.get(2));
			if (R1 == null || R2 == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			R3 = "00000";
			shft = intToBinary(inst.get(3), 5);
			res = opcode + R1 + R2 + R3 + shft + "0000000";
			break;
		case "JAL":
			opcode = "11110";
			R1 = inst.get(1);
			if (this.getAddress(R1) == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			// R1 = getRegister(inst.get(1));
			res = opcode + this.getAddress(R1);
			break;
		case "MULT":
			opcode = "01111";
			R1 = getRegister(inst.get(1));
			R2 = getRegister(inst.get(2));
			R3 = getRegister(inst.get(3));
			if (R1 == null || R2 == null || R3 == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			shft = "00000";
			res = opcode + R1 + R2 + R3 + shft + "0000000";
			break;
		case "BUN":
			opcode = "11000";
			R1 = inst.get(1);
			if (this.getAddress(R1) == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			// R1 = getRegister(inst.get(1));
			res = opcode + this.getAddress(R1);
			break;
		case "DIV":
			opcode = "00100";
			R1 = getRegister(inst.get(1));
			R2 = getRegister(inst.get(2));
			R3 = getRegister(inst.get(3));
			if (R1 == null || R2 == null || R3 == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			shft = "00000";
			res = opcode + R1 + R2 + R3 + shft + "0000000";
			break;
		case "MOD":
			opcode = "00011";
			R1 = getRegister(inst.get(1));
			R2 = getRegister(inst.get(2));
			R3 = getRegister(inst.get(3));
			if (R1 == null || R2 == null || R3 == null) {
				instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
				BufferedImage image = null;
				try {
					image = ImageIO.read(new File("what.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JLabel picLabel = new JLabel(new ImageIcon(image));
				JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
				return;
			}
			shft = "00000";
			res = opcode + R1 + R2 + R3 + shft + "0000000";
			break;
		default:
			instOutput.setText(instOutput.getText() + '\n' + "You're speaking the language of Gods");
			BufferedImage image = null;
			try {
				image = ImageIO.read(new File("what.jpg"));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			JLabel picLabel = new JLabel(new ImageIcon(image));
			JOptionPane.showMessageDialog(null, picLabel, "What was that?", JOptionPane.QUESTION_MESSAGE, null);
			return;
		}
		Instruction instr = new Instruction(res);
		instructionsMemory[instructionsMemoryIndex] = instr;
		instructionsMemoryIndex++;
	}

	public static String intToBinary(String immediate, int bits) {
		int value = Integer.parseInt(immediate);
		String res = Integer.toBinaryString(value);
		while (res.length() < bits) {
			res = "0" + res;
		}
		if (res.length() > bits) {
			res = res.substring(res.length() - bits);
		}
		return res;
	}

	public String getRegister(String reg) {
		String res = "";
		switch (reg) {
		case "$zero":
			res = "00000";
			break;
		case "$ld":
			res = "00001";
			break;
		case "$st":
			res = "00010";
			break;
		case "$v0":
			res = "00011";
			break;
		case "$v1":
			res = "00100";
			break;
		case "$a0":
			res = "00101";
			break;
		case "$a1":
			res = "00110";
			break;
		case "$a2":
			res = "00111";
			break;
		case "$a3":
			res = "01000";
			break;
		case "$t0":
			res = "01001";
			break;
		case "$t1":
			res = "01010";
			break;
		case "$t2":
			res = "01011";
			break;
		case "$t3":
			res = "01100";
			break;
		case "$t4":
			res = "01101";
			break;
		case "$t5":
			res = "01110";
			break;
		case "$t6":
			res = "01111";
			break;
		case "$t7":
			res = "10000";
			break;
		case "$t8":
			res = "10001";
			break;
		case "$t9":
			res = "10010";
			break;
		case "$s0":
			res = "10011";
			break;
		case "$s1":
			res = "10100";
			break;
		case "$s2":
			res = "10101";
			break;
		case "$s3":
			res = "10110";
			break;
		case "$s4":
			res = "10111";
			break;
		case "$s5":
			res = "11000";
			break;
		case "$s6":
			res = "11001";
			break;
		case "$s7":
			res = "11010";
			break;
		case "$s8":
			res = "11011";
			break;
		case "$s9":
			res = "11100";
			break;
		case "$sp":
			res = "11101";
			break;
		case "$gp":
			res = "11110";
			break;
		case "$ra":
			res = "11111";
			break;
		default:
			res = null;
		}
		return res;
	}

	public Instruction instructionFetch() {
		Instruction instruction = Main.instructionsMemory[getPC()];
		setPC(getPC() + 1);
		return instruction;
	}

	public void run() {
		while (pc < instructionsMemoryIndex || !(fetchedInstructions.isEmpty())) {
			instOutput.setText(instOutput.getText() + '\n' + "Clock Cycle: " + clockCycles + '\n' + "PC: " + pc);
			// System.out.println("Clock Cycle: " + clockCycles);
			// System.out.println("PC: " + pc);
			if (!(fetchedInstructions.isEmpty())) {
				int initialSize = fetchedInstructions.size();
				for (int i = 0; i < initialSize; i++) {
					Instruction oldInstruction = fetchedInstructions.remove();
					int currentStage = oldInstruction.getCurrentStage();

					if (currentStage == 4) {
						WriteBack wb = new WriteBack(MEMWB[9], MEMWB[11], MEMWB[15],
								MEMWB[14].equals("1") ? true : false, MEMWB[12].equals("1") ? true : false,
								MEMWB[13].equals("1") ? true : false, Integer.parseInt(MEMWB[10]),
								MEMWB[17].equals("1") ? true : false);
						wb.writeBack();
						oldInstruction.stages[4] = true;
					}

					if (currentStage == 3) {
						Memory m = new Memory(EXMEM[0], EXMEM[12].equals("1") ? true : false, EXMEM[6], EXMEM[16]);
						m.memory();
						MEMWB[0] = EXMEM[0];
						MEMWB[1] = EXMEM[1];
						MEMWB[2] = EXMEM[2];
						MEMWB[3] = EXMEM[3];
						MEMWB[4] = EXMEM[4];
						MEMWB[5] = EXMEM[5];
						MEMWB[6] = EXMEM[6];
						MEMWB[7] = EXMEM[7];
						MEMWB[8] = EXMEM[8];
						MEMWB[9] = EXMEM[9];
						MEMWB[10] = EXMEM[10];
						MEMWB[11] = EXMEM[11];
						MEMWB[12] = EXMEM[12];
						MEMWB[13] = EXMEM[13];
						MEMWB[14] = EXMEM[14];
						MEMWB[15] = m.fromMemory;
						MEMWB[16] = EXMEM[16];
						MEMWB[17] = EXMEM[17];
						oldInstruction.stages[3] = true;
						fetchedInstructions.add(oldInstruction);
					}

					if (currentStage == 2) {

						Execute e = new Execute(IDEX[0], IDEX[7], IDEX[8], IDEX[5], IDEX[6], IDEX[4],
								Integer.parseInt(IDEX[10]));
						e.execute();
						EXMEM[0] = IDEX[0];
						EXMEM[1] = IDEX[1];
						EXMEM[2] = IDEX[2];
						EXMEM[3] = IDEX[3];
						EXMEM[4] = IDEX[4];
						EXMEM[5] = IDEX[5];
						EXMEM[6] = IDEX[6];
						EXMEM[7] = IDEX[7];
						EXMEM[8] = IDEX[8];
						EXMEM[9] = IDEX[9];
						EXMEM[10] = "" + e.pc;
						EXMEM[11] = e.result;
						EXMEM[12] = (e.memInstruction ? "1" : "0");
						EXMEM[13] = (e.branch ? "1" : "0");
						EXMEM[14] = (e.alu ? "1" : "0");
						EXMEM[15] = "";
						EXMEM[16] = IDEX[16];
						EXMEM[17] = (e.ski ? "1" : "0");
						oldInstruction.stages[2] = true;
						fetchedInstructions.add(oldInstruction);
					}

					if (currentStage == 1) {

						Decode d = new Decode(IFID[0], IFID[3], IFID[1], IFID[2]);
						d.decode();
						IDEX[0] = IFID[0];
						IDEX[1] = IFID[1];
						IDEX[2] = IFID[2];
						IDEX[3] = IFID[3];
						IDEX[4] = IFID[4];
						IDEX[5] = IFID[5];
						IDEX[6] = IFID[6];
						IDEX[7] = d.readData1;
						IDEX[8] = d.readData2;
						IDEX[9] = d.writeReg;
						IDEX[10] = IFID[10];
						IDEX[11] = "";
						IDEX[12] = "";
						IDEX[13] = "";
						IDEX[14] = "";
						IDEX[15] = "";
						IDEX[16] = Main.regs[2];
						IDEX[17] = "";
						oldInstruction.stages[1] = true;
						fetchedInstructions.add(oldInstruction);
					}
				}
			}

			Instruction inst = null;
			if (pc < instructionsMemoryIndex) {
				inst = this.instructionFetch();
				IFID[0] = inst.statement.substring(0, 5);
				IFID[1] = inst.statement.substring(10, 15);
				IFID[2] = inst.statement.substring(15, 20);
				IFID[3] = inst.statement.substring(5, 10);
				IFID[4] = inst.statement.substring(20, 25);
				IFID[5] = inst.statement.substring(15);
				IFID[6] = inst.statement.substring(5);
				IFID[7] = "";
				IFID[8] = "";
				IFID[9] = "";
				IFID[10] = Main.getPC() + "";
				IFID[11] = "";
				IFID[12] = "";
				IFID[13] = "";
				IFID[14] = "";
				IFID[15] = "";
				IFID[16] = "";
				IFID[17] = "";
				inst.stages[0] = true;
				fetchedInstructions.add(inst);
			}
			clockCycles++;
		}
		Main.setPC(0);
		clockCycles = 0;

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Main m = new Main();
//		m.addInstruction(new Instruction("ADDI $s1 $zero 3"));
//		m.addInstruction(new Instruction("ADDI $s2 $zero 2"));
//		m.run();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton b = (JButton) e.getSource();
		if (b.getText().equals("ADD")) {
			if (userInput.getText().equalsIgnoreCase("show me what you got")) {
				instInput.setText(instInput.getText() + '\n' + userInput.getText());
				JOptionPane.showMessageDialog(null, getInstructionsPart1(), "All Instructions",
						JOptionPane.INFORMATION_MESSAGE);
				JOptionPane.showMessageDialog(null, getInstructionsPart2(), "All Instructions",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				this.addInstruction(new Instruction(userInput.getText()));
				instInput.setText(instInput.getText() + '\n' + userInput.getText());
			}
		} else {
			this.run();
			regsInfo.setText(updateRegsValues());
		}

	}

	public static String getInstructionsPart1() {
		String res = "ADDING A REGISTER AND A VALUE:" + '\n' + "ADDI $r1 $r2 value" + '\n'
				+ "------------------------------------" + '\n' + "ADDING TWO REGISTERS:" + '\n' + "ADD $r1 $r2 $r3"
				+ '\n' + "------------------------------------" + '\n' + "SUBTRACTING TWO REGISTERS:" + '\n'
				+ "SUB $r1 $r2 $r3" + '\n' + "------------------------------------" + '\n' + "LOADING FROM MEMORY:"
				+ '\n' + "LOAD $r1" + '\n' + "------------------------------------" + '\n' + "STORING INTO MEMORY"
				+ '\n' + "STORE $r1" + '\n' + "------------------------------------" + '\n' + "SET IF LESS THAN:" + '\n'
				+ "SLT $r1 $r2 $r3" + '\n' + "------------------------------------" + '\n' + "SET IF EQUALS:" + '\n'
				+ "SEQ $r1 $r2 $r3" + '\n' + "------------------------------------" + '\n'
				+ "SKIP NEXT INSTRUCTION IF EQUALS:" + '\n' + "SKI $r1 $r2 $r3" + '\n'
				+ "------------------------------------" + '\n' + "ANDING TWO REGISTERS:" + '\n' + "AND $r1 $r2 $r3"
				+ '\n' + "------------------------------------" + '\n' + "ORING TWO REGISTERS:" + '\n'
				+ "OR $r1 $r2 $r3";
		return res;
	}

	public static String getInstructionsPart2() {
		String res = "NOR OPERATION BETWEEN TWO REGISTERS:" + '\n' + "NOR $r1 $r2 $r3" + '\n'
				+ "------------------------------------" + '\n' + "NOT OPERATION ON A REGISTER:" + '\n' + "NOT $r1 $r2"
				+ '\n' + "------------------------------------" + '\n' + "SHIFT LEFT REGISTER BY VALUE:" + '\n'
				+ "SHIFTL $r1 $r2 value" + '\n' + "------------------------------------" + '\n'
				+ "SHIFT ROGHT REGISTER BY VALUE:" + '\n' + "SHIFTLR $r1 $r2 value" + '\n'
				+ "------------------------------------" + '\n' + "JUMP TO ADDRESS AND SAVE PC:" + '\n' + "JAL $r1"
				+ '\n' + "------------------------------------" + '\n' + "MULTIPLY TWO REGISTERS:" + '\n'
				+ "MULT $r1 $r2 $r3" + '\n' + "------------------------------------" + '\n' + "BRANCH TO ADDRESS:"
				+ '\n' + "BUN $r1" + '\n' + "------------------------------------" + '\n' + "DIVIDE TWO REGISTERS:"
				+ '\n' + "DIV $r1 $r2 $r3" + '\n' + "------------------------------------" + '\n'
				+ "MODULUS OPERATION BETWEEN TWO REGISTERS:" + '\n' + "MOD $r1 $r2 $r3";
		return res;
	}
}
