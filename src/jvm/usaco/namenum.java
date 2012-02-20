package usaco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/*
ID: rdsr.me1
PROG: namenum
LANG: JAVA
*/

public class namenum {
	Map<String, Boolean> dict = new HashMap<String, Boolean>();
	
	char[][] mapping = {
			{}, //0
			{}, //1
			{'A', 'B', 'C'},
			{'D', 'E', 'F'},
			{'G', 'H', 'I'},
			{'J', 'K', 'L'},
			{'M', 'N', 'O'},
			{'P', 'R', 'S'},
			{'T', 'U', 'V'},
			{'X', 'Y', 'Z'},
	};

	private static boolean noneMatch = true;
	
	public static void main(
			String[] _) 
		throws Exception {
		namenum nn = new namenum();
		
		nn.fillDictionary("dict.txt");	
		
		BufferedReader br = new BufferedReader(
							   new FileReader("src/main/resources/namenum.in"));
		PrintWriter pw = new PrintWriter("src/main/resources/namenum.out");
		
		char[] number = br.readLine().toCharArray();
		nn.printNames(
			number,
			0,
			new char[number.length],
			pw);
		
		if (noneMatch)
			pw.println("NONE");
		
		br.close();
		pw.flush();
		pw.close();
	}
	
	private void fillDictionary(
			String name)
		throws Exception {
		
		BufferedReader br = new BufferedReader(
								new FileReader(name));
		String word;
		while ((word = br.readLine()) != null) 
			dict.put(word, true);
	}

	private void printNames(
			char[] number,
			int index,
			char[] word,
			PrintWriter pw) {
		
		if (index >= number.length) {
			if (dict.containsKey(new String(word))) {
				noneMatch = false;
				pw.println(word);
			}
		} else { 		
			for (char c : mapping[Character.digit(number[index], 10)]) {
				word[index] = c;
				printNames(number, index + 1, word, pw);
			}
		}
	}
}