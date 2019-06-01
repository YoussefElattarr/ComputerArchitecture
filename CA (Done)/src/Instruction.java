
public class Instruction {
	boolean[] stages;
	String statement;
	String decodedStatement;
	String executeResult;

	public Instruction(String statement) {
		this.statement = statement;
		stages = new boolean[5];
		stages[0] = true;
		for (int i = 1; i < stages.length; i++) {
			stages[i] = false;
		}
	}

	public boolean hasNextStage() {
		for (int i = 0; i < stages.length; i++) {
			if (!stages[i])
				return true;
		}
		return false;
	}

	public int getCurrentStage() {
		int i = 1;
		while (i < stages.length && stages[i] == true) {
			i++;
		}
		return i;
	}

}
