package tc.misc.dp;

public class Books {
  int sortMoves(String[] titles) {
    int l = titles.length;
    int [] s = new int[l];
    
    s[0] = 1;
    for (int i = 1; i < l; i++) {
      s[i] = 1;
      for (int j = i - 1; j >= 0; j--)
        if (titles[i].compareTo(titles[j]) >= 0 &&
            s[j] + 1 > s[i])
            s[i] = s[j] + 1;
    }
    
    int max = 0;
    for (int i = 0; i < l; i++) {
      int _max = s[i];
      if (_max > max)
        max = _max;
    }

    return l - max;
  }

  public static void main(String[] _) {
    System.out.println(
        new Books().sortMoves(
            new String[] { "Algorithms", 
                "Purely Functional Data Structures",
                "Intro to C", 
            "Automata and Computability" }));

    System.out.println(
        new Books().sortMoves(
            new String[] {"the fellowship of the ring",
                "the return of the king",
            "The two towers"}));

    System.out.println(
        new Books().sortMoves(
            new String[] {"A", "B", "A", "A", "B"}));       

    System.out.println(
        new Books().sortMoves(
            new String[] {"This Book Has No Title", " This Book Does Have A Title"}));

    System.out.println(
        new Books().sortMoves(
            new String[] {"What Is The", "What Is The ", "What Is The Title Of This Book"}));

    System.out.println(
        new Books().sortMoves(
            new String[] {"Basic Engineering Circuit Analysis", "A Course in Combinatorics",
                "Artificial Intelligence", "Asimovs Guide to Shakespeare",
                "The Nature of Space and Time", "A Time for Trumpets",
                "Essentials of Artificial Intelligence", "Life by the Numbers",
                "Cognitive Psychology", "ColdFusion"}));
  }
}
