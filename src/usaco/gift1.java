package usaco;

/*
ID: rdsr.me1
PROG: gift1
LANG: JAVA
*/

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class gift1 {
	public static void main(String[] _) throws Exception {
		PrintWriter w = null;
		BufferedReader f = null;

		try {
			f = new BufferedReader(new FileReader("src/main/resources/gift1.in"));
			w = new PrintWriter(new BufferedWriter(new FileWriter("src/main/resources/gift1.out")));
			new gift1().printGain(f, w);
		} finally {
			if(f != null) f.close();
			if(w != null) w.close();
		}
	}

	private void printGain(BufferedReader f, PrintWriter w) throws Exception {
		Map<String, Integer> initial = new HashMap<String, Integer>();
		Map<String, Integer> gain = new HashMap<String, Integer>();
		List<String> names = new ArrayList<String>();

		int np = Integer.valueOf(f.readLine());
		for (int i = np; i > 0; i--) {
			String name = f.readLine();
			names.add(name);
			gain.put(name, 0);
		}

		for (int i = np; i > 0; i--) {
			String giver = f.readLine();
			String figures = f.readLine();

			int sum = Integer.valueOf((figures.split(" "))[0]);
			initial.put(giver, sum);
			gain.put(giver, gain.get(giver) + sum);

			int noOfReceivers = Integer.valueOf(figures.split(" ")[1]);
			for (int j = noOfReceivers; j > 0; j--) {
				String receiver = f.readLine();
				gain.put(receiver, gain.get(receiver) + sum/noOfReceivers);
				gain.put(giver, gain.get(giver) - sum/noOfReceivers);
			}
		}

		for (String name : names)
			w.println(name + " " + (gain.get(name) - initial.get(name)));
	}
}
