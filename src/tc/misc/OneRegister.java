package tc.misc;

// Topcoder SRM - 486 Div 2, Level 2

public class OneRegister {
	public String getProgram(int s, int t) {
		if (s == t)
			return "";

		if (t == 0)
			return "-";
		
		if (t == 1)
			return "/";

		if (perfectSquare(t)) {
			String r = getProgram(s, (int) (t/Math.sqrt(t)));
			if (r != ":-(")
				return r + "*";
		}

		if (t%2 == 0) {
			String r = getProgram (s, t/2);
			if (r == ":-(")
				return r;
			return  r + "+";
		}

		return ":-(";
	}

	private boolean perfectSquare(int t) {
		int sqrt = (int) Math.sqrt(t);
		return sqrt * sqrt == t;
	}


	private static boolean KawigiEdit_RunTest(int testNum, int p0, int p1, boolean hasAnswer, String p2) {
		System.out.print("Test " + testNum + ": [" + p0 + "," + p1);
		System.out.println("]");
		OneRegister obj;
		String answer;
		obj = new OneRegister();
		long startTime = System.currentTimeMillis();
		answer = obj.getProgram(p0, p1);
		long endTime = System.currentTimeMillis();
		boolean res;
		res = true;
		System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
		if (hasAnswer) {
			System.out.println("Desired answer:");
			System.out.println("\t" + "\"" + p2 + "\"");
		}
		System.out.println("Your answer:");
		System.out.println("\t" + "\"" + answer + "\"");
		if (hasAnswer) {
			res = answer.equals(p2);
		}
		if (!res) {
			System.out.println("DOESN'T MATCH!!!!");
		} else if ((endTime - startTime) / 1000.0 >= 2) {
			System.out.println("FAIL the timeout");
			res = false;
		} else if (hasAnswer) {
			System.out.println("Match :-)");
		} else {
			System.out.println("OK, but is it right?");
		}
		System.out.println("");
		return res;
	}

	public static void main(String[] args) {
		boolean all_right;
		all_right = true;

		int p0;
		int p1;
		String p2;

		// ----- test 0 -----
		p0 = 7;
		p1 = 392;
		p2 = "+*+";
		all_right = KawigiEdit_RunTest(0, p0, p1, true, p2) && all_right;
		// ------------------

		// ----- test 1 -----
		p0 = 7;
		p1 = 256;
		p2 = "/+***";
		all_right = KawigiEdit_RunTest(1, p0, p1, true, p2) && all_right;
		// ------------------

		// ----- test 2 -----
		p0 = 4;
		p1 = 256;
		p2 = "**";
		all_right = KawigiEdit_RunTest(2, p0, p1, true, p2) && all_right;
		// ------------------

		// ----- test 3 -----
		p0 = 7;
		p1 = 7;
		p2 = "";
		all_right = KawigiEdit_RunTest(3, p0, p1, true, p2) && all_right;
		// ------------------

		// ----- test 4 -----
		p0 = 7;
		p1 = 9;
		p2 = ":-(";
		all_right = KawigiEdit_RunTest(4, p0, p1, true, p2) && all_right;
		// ------------------

		// ----- test 5 -----
		p0 = 50;
		p1 = 838860800;
		p2 = "++++++++++++++++++++++++";
		all_right = KawigiEdit_RunTest(5, p0, p1, true, p2) && all_right;
		// ------------------

		if (all_right) {
			System.out.println("You're a stud (at least on the example cases)!");
		} else {
			System.out.println("Some of the test cases had errors.");
		}
	}
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
