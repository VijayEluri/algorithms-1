package tc.misc;

public class HowEasy {
	private boolean encounteredPeriod = false;
	
	public int pointVal(String problemStatement) {
		int wordCount = 0;
		int totalWordsLength = 0;
		String[] tokens = problemStatement.split(" +");
		
		for (String token: tokens) {
			if (isWord(token)) {
				wordCount += 1;
				if(encounteredPeriod) {
					totalWordsLength += token.length() - 1;
					encounteredPeriod = false;
				} else {
					totalWordsLength += token.length();
				}
			}
		}
		
		if (totalWordsLength == 0) 
			return 250;
		int averageWordLength = totalWordsLength / wordCount;
		if (averageWordLength <= 3)
			return 250;
		else if (averageWordLength == 4 || averageWordLength == 5)
			return 500;
		else 
			return 1000;
	}
	
	private boolean isWord(String token) {
		if(token.equals(""))
			return false;
		for(int i=0; i < token.length(); i++) {
			if ((token.charAt(i) >= 'A' && token.charAt(i) <= 'Z') ||
			    (token.charAt(i) >= 'a' && token.charAt(i) <= 'z')) 
				; //continue
			else if (token.charAt(i) == '.' && i == (token.length() - 1) && i != 0) {
				encounteredPeriod = true;	
				return true;
			}
			else 
				return false;
		}
		return true;
	}
	
	public static void main(String[] _) {
		System.out.println(new HowEasy().pointVal(" . . . . . . . . abcdefg"));
		//System.out.println(pointVal());
		//System.out.println(pointVal());
	}
}
