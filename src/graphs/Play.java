package graphs;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import graphs.Graph;

public class Play {
  List<int[][]> shortestPaths(byte[] bitmap, Set<Integer> originPoints) {
    Graph G = build(bitmap);
    List<int[][]> result = new ArrayList<int[][]>();
    for (Integer i : originPoints) {
      Vertex s = new Vertex(i);
      GraphMethods.initializeSingleSource(G, s);
      GraphMethods.Dijkstra(G, s);
      result.add(toArray(G));
    }
    return result;
  }

  private int[][] toArray(Graph g) {
    return null;
  }

  private Graph build(byte[] bitmap) {
    return null;
  }
}
