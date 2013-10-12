package tc.srm222;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import util.Pair;

// broken
public class WalkingHome {
  String[] map;
  public int fewestCrossings(String[] _map) {
    map = _map;
    Queue<Pair> q = new LinkedList<Pair>();
    Set<Pair> cache = new HashSet<Pair>();
    Map<Pair, Integer> distance = new HashMap<Pair, Integer>();

    Pair schl = school(map);
    Pair hm = home(map);

    distance.put(schl, 0);
    cache.add(schl);
    q.offer(schl);

    while (!q.isEmpty()) {
      Pair p = q.poll();
      List<Pair> crssngs = crossings(p, cache);
      for (Pair crssng : crssngs) {
        if (cache.contains(crssng))
          continue;
        if (crssng.equals(hm))
          return distance.get(p);
        if (istwoLaneCrossing(p, crssng)) 
          distance.put(crssng, distance.get(p));
        else 
          distance.put(crssng, distance.get(p) + 1);
        cache.add(crssng);
        q.offer(crssng);
      }
    }
    return -1;
  }

  private boolean istwoLaneCrossing(Pair p, Pair crssng) {
    return
      (p.x == crssng.x && 
       Math.abs(p.y - crssng.y) == 1 &&
       valueOf(p) == '|' &&
       valueOf(crssng) == '|') ||
       
       (p.y == crssng.y &&
        Math.abs(p.x - crssng.x) == 1 &&
        valueOf(p) == '-' &&
        valueOf(p) == '-');
  }

  private char valueOf(Pair p) {
    return map[p.x].charAt(p.y);
  }

  private Pair home(String[] map) {
    int row = map.length;
    int col = map[0].length();

    for (int i = 0; i < row; i++)
      for (int j = 0 ; j < col; j++)
        if (map[i].charAt(j) == 'H')
          return new Pair(i, j);

    return null;
  }

  private Pair school(String[] map) {
    int row = map.length;
    int col = map[0].length();

    for (int i = 0; i < row; i++)
      for (int j = 0 ; j < col; j++)
        if (map[i].charAt(j) == 'S')
          return new Pair(i, j);

    return null;
  }

  private List<Pair> crossings(Pair p, Set<Pair> cache) {
    List<Pair> crssngs = new ArrayList<Pair>();
    Queue<Pair> q = new LinkedList<Pair>();
    q.offer(p);
    while (!q.isEmpty()) {
      p = q.poll();
      List<Pair> nghbrs = neighbors(p);
      for (Pair nghbr: nghbrs) {
        if (valueOf(nghbr) == 'F' || valueOf(nghbr) == '*' || cache.contains(nghbr))
          continue;
        if (isCrossing(nghbr) || valueOf(nghbr) == 'H') 
          crssngs.add(nghbr);
        else {
          q.offer(nghbr);
          cache.add(nghbr);
        }
      }
    }
    return crssngs;
  }

  private List<Pair> neighbors(Pair p) {
    List<Pair> nghbrs = new ArrayList<Pair>();
    Pair nghbr = new Pair (p.x - 1, p.y);
    if (isValid(nghbr) && valueOf(nghbr) != '|' && valueOf(p) != '|')
      nghbrs.add(nghbr);

    nghbr = new Pair (p.x, p.y + 1);
    if (isValid(nghbr) && valueOf(nghbr) != '-' && valueOf(p) != '-') 
      nghbrs.add(nghbr);

    nghbr = new Pair (p.x + 1, p.y);
    if (isValid(nghbr) && valueOf(nghbr) != '|' && valueOf(p) != '|')
      nghbrs.add(nghbr);

    nghbr = new Pair (p.x, p.y - 1);
    if (isValid(nghbr) && valueOf(nghbr) != '-' && valueOf(p) != '-')
      nghbrs.add(nghbr);

    return nghbrs;
  }

  private boolean isValid(Pair p) {
    int row = map.length;
    int col = map[0].length();

    return
    p.x < row && p.y < col &&
    p.x >= 0 && p.y >= 0 &&     
    valueOf(p) != '*' && valueOf(p) != 'F'; 
  }

  private boolean isCrossing(Pair p) {
    return valueOf(p) == '|' || valueOf(p) == '-';
  }

  // BEGIN KAWIGIEDIT TESTING
  // Generated by KawigiEdit 2.1.4 (beta) modified by pivanof
  private static boolean KawigiEdit_RunTest(int testNum, String[] p0, boolean hasAnswer, int p1) {
    System.out.print("Test " + testNum + ": [" + "{");
    for (int i = 0; p0.length > i; ++i) {
      if (i > 0) {
        System.out.print(",");
      }
      System.out.print("\"" + p0[i] + "\"");
    }
    System.out.print("}");
    System.out.println("]");
    WalkingHome obj;
    int answer;
    obj = new WalkingHome();
    long startTime = System.currentTimeMillis();
    answer = obj.fewestCrossings(p0);
    long endTime = System.currentTimeMillis();
    boolean res;
    res = true;
    System.out.println("Time: " + (endTime - startTime) / 1000.0 + " seconds");
    if (hasAnswer) {
      System.out.println("Desired answer:");
      System.out.println("\t" + p1);
    }
    System.out.println("Your answer:");
    System.out.println("\t" + answer);
    if (hasAnswer) {
      res = answer == p1;
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

    String[] p0;
    int p1;

    // ----- test 0 -----
    p0 = new String[] {"|...F...F...F...F....||||........-.....|.......|||", "..F...F...F...|.|.F...||....-.-.......||..........", ".S.|||||||||||||||||||||||||||-||||||||||||||||||-", "FFFFFFFFFFFFFFFFFFFFFFFFFFFF-.-...F............F..", "||||||||||||||||||||||||||.|...FFF.............F--", "...................FFFFFFFFFFFFF...............F.|", "...............................................F.|", "...............................................F-.", "...............................................F..", "...............................................F--", "...............................................FF-", "..||||||||||||||||||||||||||||||||||||||||||||||..", "...............................................FFF", "FFFFFFFFFFFFFFF|FF-FFFFFFFFFFFFFFFFFFFFFFFFFFF--..", ".|...|............-................F..............", "..|.|.|..........|.||..F....F..F...F..............", "||||...|.........||||...F..F....F......FFFFFFFFFFF", "FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF--------------", "...........|||||....................---...........", ".............|.|....................-F-...........", ".............|.|....................---...........", ".............|.|....................-.-...........", "--------------F|....................|.-...........", "FFFFFFFFFFFF-FF|....................-.-...........", "...........|.|F.....................-.-...........", "FF-FFFFFFFFF-FF.....................||.||||.......", "||...|.F....|..FF.....................|....FFFFFFF", ".|||...F.........F.....FF..FF..F.F.F.F.F.F.F......", "......F...........FF-FF..FF..FF.F.F.F.F.F.F.......", "......F............F..............................", ".....F.............F..............................", ".....F.F...........FFFFFFFFFFFFFFFFFFFFFFF-FFF....", ".....FFFF.................................-.......", "........FF................................-.......", "........F.FF..............................-.......", "........FF................................-.......", "........F...F.............................-.......", "........F....F............................-.......", ".......FF.....F..........................F-.....FF", "........F......F..........................-||||||.", "........F.................................-F.H.|..", ".........FFFFFFFFFF.......................-.|||...", ".........F.|-......F......................-..F....", "...........|-.......FFFFFFFFFFFFFFFFFFFFFFFFF...F.", ".........F.|-................F....F.....||||||||.-", ".........F.--.................F...F....F..........", ".........F.-.......FF.......F.....F...F...........", ".-.....--F.||||||||..F.....F....F----F...........-", "....-----F.-..........F......-...F..F............-", ".--------F.-.......................F...........||-"};
                    
    p1 = 20;
    all_right = KawigiEdit_RunTest(0, p0, true, p1) && all_right;
    // ------------------

    // ----- test 1 -----
    p0 = new String[]{"S.|..","..|.H","..|..","....."};
    p1 = 0;
    all_right = KawigiEdit_RunTest(1, p0, true, p1) && all_right;
    // ------------------

    // ----- test 2 -----
    p0 = new String[]{"S.||...","..||...","..||...","..||..H"};
    p1 = 1;
    all_right = KawigiEdit_RunTest(2, p0, true, p1) && all_right;
    // ------------------

    // ----- test 3 -----
    p0 = new String[]{"S.....","---*--","...|..","...|.H"};
    p1 = 1;
    all_right = KawigiEdit_RunTest(3, p0, true, p1) && all_right;
    // ------------------

    // ----- test 4 -----
    p0 = new String[]{"S.F..","..F..","--*--","..|..","..|.H"};
    p1 = 2;
    all_right = KawigiEdit_RunTest(4, p0, true, p1) && all_right;
    // ------------------

    // ----- test 5 -----
    p0 = new String[]{"H|.|.|.|.|.|.|.|.|.|.|.|.|.","F|F|F|F|F|F|F|F|F|F|F|F|F|-","S|.|.|.|.|.|.|.|.|.|.|.|.|."};
    p1 = 27;
    all_right = KawigiEdit_RunTest(5, p0, true, p1) && all_right;
    // ------------------

    // ----- test 6 -----
    p0 = new String[]{"S-H"};
    p1 = -1;
    all_right = KawigiEdit_RunTest(6, p0, true, p1) && all_right;
    // ------------------

    if (all_right) {
      System.out.println("You're a stud (at least on the example cases)!");
    } else {
      System.out.println("Some of the test cases had errors.");
    }
  }
  // END KAWIGIEDIT TESTING
}
//Powered by KawigiEdit 2.1.4 (beta) modified by pivanof!
