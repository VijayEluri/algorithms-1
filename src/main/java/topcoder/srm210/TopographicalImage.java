package topcoder.srm210;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;


class Node implements Comparable<Node> {
  int i, j;
  char height;

  public Node(int i, int j, char height) {
    this.i = i;
    this.j = j;
    this.height = height;
  }

  @Override
  public int compareTo(Node o) {
    if (height > o.height) {
      return -1;
    }
    if (height < o.height) {
      return 1;
    }
    return 0;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Node) {
      final Node o = (Node) obj;
      return i == o.i && j == o.j && height == o.height;
    }
    return false;
  }

  @Override
  public int hashCode() {
    int result = 17;
    result = 31 * result + i;
    result = 31 * result + j;
    result = 31 * result + height;
    return result;
  }
}

public class TopographicalImage {
  Set<Node> cache;
  int N, M;
  String[] topoData;

  public int[] calcPeakAreas(String[] topoData) {
    N = topoData.length;
    M = topoData[0].length();
    this.topoData = topoData;
    cache = new HashSet<Node>();
    final List<Node> nodes = new ArrayList<Node>();

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        nodes.add(new Node(i, j, topoData[i].charAt(j)));
      }
    }

    Collections.sort(nodes);

    final int[] areas = new int[nodes.size()];
    int i = 0;
    for (final Node node : nodes) {
      if (!cache.contains(node)) {
        areas[i++] = bfs(node);
      }
    }

    return Arrays.copyOf(areas, i);
  }

  private int bfs(Node startNode) {
    final Queue<Node> queue = new LinkedList<Node>();
    queue.add(startNode);
    cache.add(startNode);

    int count = 1;
    while (!queue.isEmpty()) {
      final Node node = queue.poll();
      final List<Node> neighbours = neighbours(node);
      for (final Node neighbour : neighbours) {
        if (!cache.contains(neighbour)) {
          count += 1;
          queue.offer(neighbour);
          cache.add(neighbour);
        }
      }
    }

    return count;
  }

  private List<Node> neighbours(Node node) {
    final List<Node> nodes = new ArrayList<Node>();

    final int i = node.i;
    final int j = node.j;
    final char height = node.height;

    if (withinBounds(i - 1, j - 1) && topoData[i - 1].charAt(j - 1) <= height) {
      nodes.add(new Node(i - 1, j - 1, topoData[i - 1].charAt(j - 1)));
    }

    if (withinBounds(i - 1, j) && topoData[i - 1].charAt(j) <= height) {
      nodes.add(new Node(i - 1, j, topoData[i - 1].charAt(j)));
    }

    if (withinBounds(i - 1, j + 1) && topoData[i - 1].charAt(j + 1) <= height) {
      nodes.add(new Node(i - 1, j + 1, topoData[i - 1].charAt(j + 1)));
    }

    if (withinBounds(i, j - 1) && topoData[i].charAt(j - 1) <= height) {
      nodes.add(new Node(i, j - 1, topoData[i].charAt(j - 1)));
    }

    if (withinBounds(i, j) && topoData[i].charAt(j) <= height) {
      nodes.add(new Node(i, j, topoData[i].charAt(j)));
    }

    if (withinBounds(i, j + 1) && topoData[i].charAt(j + 1) <= height) {
      nodes.add(new Node(i, j + 1, topoData[i].charAt(j + 1)));
    }

    if (withinBounds(i + 1, j - 1) && topoData[i + 1].charAt(j - 1) <= height) {
      nodes.add(new Node(i + 1, j - 1, topoData[i + 1].charAt(j - 1)));
    }

    if (withinBounds(i + 1, j) && topoData[i + 1].charAt(j) <= height) {
      nodes.add(new Node(i + 1, j, topoData[i + 1].charAt(j)));
    }

    if (withinBounds(i + 1, j + 1) && topoData[i + 1].charAt(j + 1) <= height) {
      nodes.add(new Node(i + 1, j + 1, topoData[i + 1].charAt(j + 1)));
    }

    return nodes;
  }

  private boolean withinBounds(int i, int j) {
    return i < N && i >= 0 && j < M && j >= 0;
  }

  public static void main(String[] args) {
    boolean all_right;
    all_right = true;

    String[] p0;
    int[] p1;

    // ----- test 0 -----
    p0 =
        new String[]{"............", "....i..i....", "....i..i....", ".o..i..i..o.", ".o........o.", "..oooooooo..", "............"};
    p1 = new int[]{78, 3, 3};
    all_right = KawigiEdit_RunTest(0, p0, true, p1) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 =
        new String[]{"............", "....i..i....", "....i..i....", ".S..i..i..Y.", ".M........E.", "..ILEYSMIL..", "............"};
    p1 = new int[]{69, 3, 2, 5, 3, 1, 1};
    all_right = KawigiEdit_RunTest(1, p0, true, p1) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 =
        new String[]{"zzzzzzzzzzzzz", "z...........z", "z...c.b.c...z", "z....bab.b..z", "z...c.b.c...z", "z...........z", "zzzzzzzzzzzzz"};
    p1 = new int[]{81, 6, 2, 1, 1};
    all_right = KawigiEdit_RunTest(2, p0, true, p1) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = new String[]{"!"};
    p1 = new int[]{1};
    all_right = KawigiEdit_RunTest(3, p0, true, p1) && all_right;
    // ------------------

    // ----- test 4 -----
    p0 =
        new String[]{"AAAAAAABBBBCCCDEFGHHIIJIIHGFEDDCCCBBBBBBBBBBAAAAAA", "AAAAABBBBBCCDDEEFGHIJJJJIIHGFEDDCCCCCCCCCBBBBBAAAA", "AAAABBBBCCCDDEEFGHIIJJJJJIHGFEDDDDDDDDDCCCCBBBBAAA", "AAABBBBCCDDEEFFGHHIJJJJJJIHGFEEDDDDDEEDDDDCCBBBBAA", "AABBBCCDDEEFFGGHHIIJJJJJIHHGFEEEEEEEFFFEEDDCCBBBAA", "BBBBCCDDEFFGHHHIIIIJJJIIIHGFFEEEEFFGGGGGFEEDCCBBBA", "BBBCCDEEFGHIIIJJJJIIIIIHHGGFFEEFFGGHHHHHGGFEDCCBBB", "BBCCDEEGHIJJKKKKJJJIIHHGGFFEEEEFGGHIIJJIIHGFEDCCBB", "CCCDEEFHIJKLMMMLKKJIHHGGFFEEEEFFGHIJJKKJJIHGFEDCBB", "CDDEEFHIJLMNNNNMLKJIHGFFEEEDEEFFGIJKKLLLKJIHFEDCCB", "DDEFFGIJLMNOPPONMLJIHGFEEDDDDEFGHIJKLMMMLKJIGFEDCB", "EEFFGHIKMNOQQQPONLKIHFEEDDDDDEFGHIKLMMNMMLKIHGEDCC", "FFGGHIJLMOPQRRQPNMKIGFEDDCCDDEFGHIKLMNNNNMLJIGFEDC", "GHHHIJKLNOQRRRQPOMKIGFEDDCCDDEFGHIKLMNNNNMLKIHFEDC", "HIIIJJKLNOPQRRQPNLKIGFEDDCCDDEFGHJKLMNOONNMKJHGFDC", "IJJJJJKLMOPQQQPONLJHGFEDDDDDEEFGIJKLMNOONNMLJIGFED", "JJJJJKKLMNOOPPONMKJHGFEDDDDEEFGHIJKLMNNONNMLJIGFED", "JKKJJJKKLMMNNNNMLJIHFFEEEEEFGGHIJKLMMNNNNMMKJIGFED",
            "KKKJJJJJKKLLMLLKJIHGFFEEEFFGHIJKKLMMNNNNNMLKJH"
                + "GFED", "JJJJIIIIIJJJKKJJIIHGFFFFFGHIJKLMMNNNNNNMMLKJIHGEDC", "JJJIIHHHHHHIIIIIHHGGGGGGHIJKLMNOOOOONNMMLKJIHGFEDC", "IIIHHGGGGGGGGHHHGGGGGGHIIJLMNOPQQQQPONMLKJIHGFEDDC", "HHHGGFFFFFFFFFGGGGGGHHIJKMNOQRSSSSRQPNMLKIHGFFEDCC", "GGGFFEEEEEEEEFFFGGGHIJKLMOPRSTUUUTSRPNMKJHGFFEDCCB", "FFFEEEEDDDDEEEEFGGHIJKLNOQRTUVWWWVTRPNLJIHFEEDCCBB", "EEEEDDDDDDDDEEEFGHIJKLNOQRTVWXYYXWUSPNLJHGFEDCCBBB", "DDDDDDDDDDDEEEFFGHIKLNOQRTVWXYZYYWURPMKIGFEDCCBBBB", "CDDDDDDEEEEEEFFGHIJKMOPRSUWXYZZZXWTROMJHGEDCCBBBBA", "CCDDDEEEFFFFFGGHHJKLNOQRTVWXYZZYXVTQNLIGFEDCBBBAAA", "CCDDEFFGGGGHHHHIIJKMNPQSTVWXYYYXVURPMKIGEDCBBBAAAA", "CDDEFGGHIIIIIIIJJKLMOPQSTUVWWXWVUSQNLJHFECCBBBAAAA", "CDEFGHIJKKKKKKKKKLMNOPQRSTUVVVUTSQOMJHGEDCBBBAAAAA", "CDEGHIKLMMMMMMLLLMMNOPQRSSTTTTSRQOMKIGFDCCBBAAAAAA", "DEFGIKLMNOOOONNMMMNNOPQQRRRRRRQPNMKIHFEDCBBBAAAAAA", "DEGHJLMOPQQQPPOONNNOOPPPQQQPPONMLKIHFEDCBBBAAAAAAA", "DEGIKMNPQRRRRQPOOOOOOOPPPOOONMLKJIHFEDCCBBAAAAAAAA", "DFGIKMOQRSSSRRQPOOOOOOOOONMMLKJIHGFEDCCBBBAAAAAAAA",
            "DFGIKMOQRSSSRRQPOOOOONNNMMLKJIIHGFEDCCBB"
                + "BAAAAAAAAA", "DEGIJLNPQRRRRQPOONNNNNMMLLKJIHGFEEDCCBBBAAAAAAAAAA", "DEFHJKMOPQQQQPOONNMMMMLLKJIHGGFEDDCCBBBAAAAAAAAAAA", "CDFGIJLMNOOOONNMMLLLLLKKJIHGFEEDCCCBBBAAAAAAAAAAAA", "CDEFGIJKLMMMMMLLKKKKKJJIIHGFEDDCCBBBBAAAAAAAAAAAAA", "CCDEFGHIJKKKKKJJJIIIIIHHGGFEDDCCBBBBAAAAAAAAAAAAAA", "BCCDEFGHHIIIIIHHHHHHHGGGFFEDDCCBBBAAAAAAAAAAAAAAAA", "BBCCDEEFFGGGGGGFFFFFFFFEEDDCCCBBBAAAAAAAAAAAAAAAAA", "BBBCCDDEEEEEEEEEEEEEEEEDDDCCBBBBAAAAAAAAAAAAAAAAAA", "ABBBCCCCDDDDDDDDDDDDDDDCCCCBBBBAAAAAAAAAAAAAAAAAAA", "AABBBBBCCCCCCCCCCCCCCCCCCBBBBBAAAAAAAAAAAAAAAAAAAA", "AAABBBBBBBBBBBBBBBBBBBBBBBBBAAAAAAAAAAAAAAAAAAAAAA", "AAAAAABBBBBBBBBBBBBBBBBBBBAAAAAAAAAAAAAAAAAAAAAAAA"};
    p1 = new int[]{1918, 65, 483, 5, 5, 24};
    all_right = KawigiEdit_RunTest(4, p0, true, p1) && all_right;
    // ------------------

    if (all_right) {
      System.out.println("You're a stud (at least on the example cases)!");
    } else {
      System.out.println("Some of the test cases had errors.");
    }
  }

  // BEGIN KAWIGIEDIT TESTING
  // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
  private static boolean KawigiEdit_RunTest(int testNum, String[] p0, boolean hasAnswer, int[] p1) {
    System.out.print("Test " + testNum + ": [" + "{");
    for (int i = 0; p0.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print("\"" + p0[i] + "\"");
    }
    System.out.print("}");
    System.out.println("]");
    TopographicalImage obj;
    int[] answer;
    obj = new TopographicalImage();
    final long startTime = System.currentTimeMillis();
    answer = obj.calcPeakAreas(p0);
    final long endTime = System.currentTimeMillis();
    boolean res;
    res = true;
    System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
    if (hasAnswer) {
      System.out.println("Desired answer:");
      System.out.print("\t" + "{");
      for (int i = 0; p1.length > i; ++i) {
        if (i > 0) {
          System.out.print(",");
        }
        System.out.print(p1[i]);
      }
      System.out.println("}");
    }
    System.out.println("Your answer:");
    System.out.print("\t" + "{");
    for (int i = 0; answer.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print(answer[i]);
    }
    System.out.println("}");
    if (hasAnswer) {
      if (answer.length != p1.length) {
        res = false;
      } else {
        for (int i = 0; answer.length > i; ++i) {
          if (answer[i] != p1[i]) {
            res = false;
          }
        }
      }
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
}

// Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
