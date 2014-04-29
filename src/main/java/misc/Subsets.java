package misc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Subsets {
  Set<Set<Integer>> powerSet(List<Integer> s) {
    if (s == null) {
      throw new NullPointerException("input is null");
    }

    Set<Set<Integer>> partial = null;
    if (s.isEmpty()) {
      partial = new HashSet<Set<Integer>>();
      partial.add(new HashSet<Integer>());
      return partial;
    }

    Integer a = s.get(0);
    partial = powerSet(s.subList(1, s.size()));
    Set<Set<Integer>> powerSet = new HashSet<Set<Integer>>();
    for (Set<Integer> element : partial) {
      powerSet.add(new HashSet<Integer>(element));
      element.add(a);
      powerSet.add(element);
    }
    return powerSet;
  }

  public static void main(String[] _) {
    System.out.println(new Subsets().powerSet(null));
  }
}
