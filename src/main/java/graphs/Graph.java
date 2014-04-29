package graphs;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;


public class Graph {
  List<Vertex> V;
  List<Edge> E;
  Map<Vertex, List<Vertex>> adj;

  double w(Vertex u, Vertex v) {
    return 0;
  }
}

class Edge {
  Vertex u;
  Vertex v;
  double w;
}

class GraphMethods {
  static void Dijkstra(Graph G, Vertex s) {
    s.d = 0;
    final Set<Vertex> S = new HashSet<Vertex>();
    final Queue<Vertex> Q = new PriorityQueue<Vertex>(G.V.size(), new Comparator<Vertex>() {
      @Override
      public int compare(Vertex o1, Vertex o2) {
        if (o1.d < o2.d) {
          return -1;
        }
        return 1;
      }
    });

    while (!Q.isEmpty()) {
      final Vertex u = Q.poll();
      S.add(u);
      for (final Vertex v : G.adj.get(u)) {
        GraphMethods.relax(u, v, G.w(u, v));
      }
    }
  }

  static void initializeSingleSource(Graph G, Vertex s) {
    for (final Vertex v : G.V) {
      v.d = Integer.MAX_VALUE;
      v.p = null;
    }
  }

  static void relax(Vertex u, Vertex v, double w) {
    if (v.d > u.d + w) {
      v.d = u.d + w;
      v.p = u;
    }
  }
}

class Vertex {
  public Vertex(Object id) {
  }

  Object id;
  double d;
  Vertex p;
}
