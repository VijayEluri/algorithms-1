package usaco;

/**
 ID: rdsr.me1
 PROB: contact
 LANG: JAVA
 */

import java.io.*;
import java.util.*;

/**
 * The cows have developed a new interest in scanning the universe outside their farm with radiotelescopes. Recently, they noticed a very curious microwave pulsing emission sent right from the centre of the galaxy. They wish to know if the emission is transmitted by some extraterrestrial form of intelligent life or if it is nothing but the usual heartbeat of the stars.

 Help the cows to find the Truth by providing a tool to analyze bit patterns in the files they record. They are seeking bit patterns of length A through B inclusive (1 <= A <= B <= 12) that repeat themselves most often in each day's data file. They are looking for the patterns that repeat themselves most often. An input limit tells how many of the most frequent patterns to output.

 Pattern occurrences may overlap, and only patterns that occur at least once are taken into account.

 PROGRAM NAME: contact

 INPUT FORMAT

 Line 1:	Three space-separated integers: A, B, N; (1 <= N <= 50)
 Lines 2 and beyond:	A sequence of as many as 200,000 characters, all 0 or 1; the characters are presented 80 per line, except potentially the last line.
 SAMPLE INPUT (file contact.in)

 2 4 10
 01010010010001000111101100001010011001111000010010011110010000000

 In this example, pattern 100 occurs 12 times, and pattern 1000 occurs 5 times. The most frequent pattern is 00, with 23 occurrences.

 OUTPUT FORMAT

 Lines that list the N highest frequencies (in descending order of frequency) along with the patterns that occur in those frequencies. Order those patterns by shortest-to-longest and increasing binary number for those of the same frequency. If fewer than N highest frequencies are available, print only those that are.

 Print the frequency alone by itself on a line. Then print the actual patterns space separated, six to a line (unless fewer than six remain).

 SAMPLE OUTPUT (file contact.out)

 23
 00
 15
 01 10
 12
 100
 11
 11 000 001
 10
 010
 8
 0100
 7
 0010 1001
 6
 111 0000
 5
 011 110 1000
 4
 0001 0011 1100
 */

public class contact {
  public static void main(String[] args) throws IOException {
    final Scanner in = new Scanner(new File("contact.in"));
    int a = in.nextInt();
    int b = in.nextInt();
    int n = in.nextInt();

    StringBuilder sb = new StringBuilder();
    String s;
    try {
      while ((s = in.nextLine()) != null) {
        sb.append(s);
      }
    } catch (NoSuchElementException e) {
    }
    in.close();

    final PrintWriter pw = new PrintWriter(
        new BufferedWriter(new FileWriter("contact.out")));

    final SortedMap<Integer, List<String>> r = solve(sb.toString().toCharArray(), a, b);
    for (int i = 0; i < n && r.size() > 0; i++) {
      final Integer k = r.firstKey();
      final List<String> v = r.get(k);
      r.remove(k);

      pw.println(k);
      for (int j = 0; j < v.size(); j++) {
        pw.print(v.get(j));
        if ((j == v.size() - 1) ||
            ((j + 1) % 6 == 0)) {
          pw.println();
        } else {
          pw.print(" ");
        }
      }
    }
    pw.close();
  }

  private static SortedMap<Integer, List<String>> solve(char[] seq, int a, int b) {
    // determine pattern frequency
    final Map<String, Integer> pttrnFrq = new HashMap<>();
    for (int i = 0; i < seq.length; i++) {
      for (int len = a; len <= b; len++) {
        // count pattern
        if (i + len > seq.length) {
          break;
        }

        final char[] sseq = Arrays.copyOfRange(seq, i, i + len);
        String k = new String(sseq);
        pttrnFrq.put(k, pttrnFrq.getOrDefault(k, 0) + 1);
      }
    }

    // Group by freq.
    SortedMap<Integer, List<String>> r = new TreeMap<>(Collections.reverseOrder());
    pttrnFrq.forEach((p, freq) -> {
      List<String> ps;
      if ((ps = r.get(freq)) == null) {
        ps = new ArrayList<>();
        r.put(freq, ps);
      }
      ps.add(p);
    });

    // sort the values per freq. group
    final Comparator<String> pttrnCmprtr = (p1, p2) -> {
      int r1 = p1.length() - p2.length();
      if (r1 == 0) {
        r1 = Integer.parseInt(p1, 2) - Integer.parseInt(p2, 2);
      }
      return r1;
    };
    r.replaceAll((k, v) -> {
      v.sort(pttrnCmprtr);
      return v;
    });
    return r;
  }
}
