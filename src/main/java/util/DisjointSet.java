package util;

import java.util.HashMap;
import java.util.Map;


public class DisjointSet<T> {
  private Map<T, Member<T>> map = new HashMap<T, Member<T>>();

  public void makeSet(T x) {
    map.put(x, Member.init(x));
  }

  public T findSet(T x) {
    return findSet(map.get(x)).data;
  }

  public void union(T x, T y) {
    link(findSet(map.get(x)), findSet(map.get(y)));
  }

  private Member<T> findSet(Member<T> m) {
    if (m != m.parent) {
      m.parent = findSet(m.parent);
    }
    return m.parent;
  }

  private void link(Member<T> x, Member<T> y) {
    if (x.rank > y.rank) {
      y.parent = x;
    } else {
      x.parent = y;
      if (x.rank == y.rank) {
        y.rank += 1;
      }
    }
  }

  private static final class Member<T> {
    T data;
    int rank;
    Member<T> parent;

    Member(T _data) {
      data = _data;
    }

    static <T> Member<T> init(T _data) {
      Member<T> m = new Member<T>(_data);
      m.parent = m;
      return m;
    }
  }

  public static void main(String[] _) {
    DisjointSet<Integer> djs = new DisjointSet<Integer>();
    int[] a = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9};

    for (int i : a) {
      djs.makeSet(i);
    }

    System.out.println(djs.findSet(0));

    djs.union(0, 1);
    djs.union(1, 2);
    System.out.println(djs.findSet(0));
    System.out.println(djs.findSet(1));
    System.out.println(djs.findSet(2));
  }
}

