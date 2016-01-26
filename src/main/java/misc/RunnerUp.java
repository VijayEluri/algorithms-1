package misc;

interface Node {
  Node r();

  Node l();
}

public class RunnerUp {
  Node runnerUp(Node winner) {
    return runnerUp(winner.l(), winner.r());
  }

  private Node runnerUp(Node winner, Node potentialRunnerUp) {
    if (winner == null) {
      return potentialRunnerUp;
    }
    if (greater(winner.r(), potentialRunnerUp)) {
      return runnerUp(winner.l(), potentialRunnerUp);
    } else {
      return runnerUp(winner);
    }
  }

  private boolean greater(Node a, Node b) {
    return false;
  }

  Node mkTree(Node[] nodes, int i, int j) {
    if (i == j) {
      return nodes[i];
    }
    if (i < j) {
      int m = (i + 1)/2;
      Node a = mkTree(nodes, i, m - 1);
      Node b = mkTree(nodes, m, j);

      if (a == null) return b;
      if (b == null) return a;

      else {
        return new Node(min(a, b).val, min(a, b), max(a, b));
      }

    }
    return null;
  }
}
