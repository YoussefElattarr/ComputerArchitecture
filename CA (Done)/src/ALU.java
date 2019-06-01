
public class ALU {
	String result;

	public ALU(String opcode, String rs, String rt) {
		int x, y, r;
		String temp;
		switch (opcode) {
		case "00000":
			x = Integer.parseInt(rs, 2);
			y = Integer.parseInt(rt, 2);
			r = x + y;
			result = Main.intToBinary("" + r, 32);
			break;
		case "00001":
			x = Integer.parseInt(rs, 2);
			y = Integer.parseInt(rt, 2);
			r = x + y;
			result = Main.intToBinary("" + r, 32);
			break;
		case "00010":
			x = Integer.parseInt(rs, 2);
			y = Integer.parseInt(rt, 2);
			r = x - y;
			result = Main.intToBinary("" + r, 32);
			;
			break;
		case "00101":
			x = Integer.parseInt(rs, 2);
			y = Integer.parseInt(rt, 2);
			r = (x < y) ? 1 : 0;
			result = Main.intToBinary("" + r, 32);
			;
			break;
		case "00110":
			x = Integer.parseInt(rs, 2);
			y = Integer.parseInt(rt, 2);
			r = (x == y) ? 1 : 0;
			result = Main.intToBinary("" + r, 32);
			;
			break;
		case "00111":
			x = Integer.parseInt(rs, 2);
			y = Integer.parseInt(rt, 2);
			r = (x == y) ? 1 : 0;
			result = Main.intToBinary("" + r, 32);
			;
			break;
		case "01000":
			temp = "";
			for (int i = 0; i < rs.length(); i++) {
				if (rs.charAt(i) == '1' && rt.charAt(i) == '1') {
					temp += "1";
				} else {
					temp += "0";
				}
			}
			result = temp;
			break;
		case "01001":
			temp = "";
			for (int i = 0; i < rs.length(); i++) {
				if (rs.charAt(i) == '0' && rt.charAt(i) == '0') {
					temp += "0";
				} else {
					temp += "1";
				}
			}
			result = temp;
			break;
		case "01010":
			temp = "";
			for (int i = 0; i < rs.length(); i++) {
				if (rs.charAt(i) == '0' && rt.charAt(i) == '0') {
					temp += "1";
				} else {
					temp += "0";
				}
			}
			result = temp;
			break;
		case "01011":
			temp = "";
			for (int i = 0; i < rs.length(); i++) {
				if (rs.charAt(i) == '0') {
					temp += "1";
				} else {
					temp += "0";
				}
			}
			result = temp;
			break;
		case "01100":
			y = Integer.parseInt(rt, 2);
			rs = rs.substring(y);
			while (y > 0) {
				rs += "0";
				y--;
			}
			result = rs;
			break;
		case "01101":
			y = Integer.parseInt(rt, 2);
			int tmp = y;
			while (y > 0) {
				rs = "0" + rs;
				y--;
			}
			rs = rs.substring(0, rs.length() - tmp);
			result = rs;
			break;
		case "01111":
			x = Integer.parseInt(rs, 2);
			y = Integer.parseInt(rt, 2);
			r = x * y;
			result = Main.intToBinary("" + r, 32);
			;
			break;
		case "00100":
			x = Integer.parseInt(rs, 2);
			y = Integer.parseInt(rt, 2);
			r = x / y;
			result = Main.intToBinary("" + r, 32);
			;
			break;
		case "00011":
			x = Integer.parseInt(rs, 2);
			y = Integer.parseInt(rt, 2);
			r = x % y;
			result = Main.intToBinary("" + r, 32);
			;
			break;
		case "11110":
			x = Integer.parseInt(rs);
			y = Integer.parseInt(rt);
			r = x + y;
			result = "" + r;
		case "11000":
			x = Integer.parseInt(rs);
			y = Integer.parseInt(rt);
			r = x + y;
			result = "" + r;
		}
	}
}
