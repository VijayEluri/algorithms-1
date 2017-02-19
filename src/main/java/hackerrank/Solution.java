package hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

class NYGraph {
  int n, m;
  final Node[] nodes;
  final private Collection<Node>[] adjList;

  public NYGraph(int n, int m) {
    this.n = n;
    this.m = m;
    nodes = new Node[n];
    adjList = new Collection[n];
  }

  boolean timestep() {
    for (int i = 0; i < n; i++)
      calculateIncomingZombies(i);
    if (saturated())
      return false;
    for (int i = 0; i < n; i++)
      nodes[i].outGoingZombies = nodes[i].incomingZombies;
    return true;
  }

  private boolean saturated() {
    for (int i = 0; i < n; i++)
      if (!nodes[i].saturated())
        return false;
    return true;
  }

  private void calculateIncomingZombies(int nodeIndex) {
    final Node node = nodes[nodeIndex];
    final Collection<Node> incidentNodes = adjList[nodeIndex];
    float incomingZombies = 0f;
    for (final Node incidentNode : incidentNodes) {
      incomingZombies += incidentNode.outGoingZombiesPerNode();
    }
    node.incomingZombies = incomingZombies;
  }

  public void addEdge(int i, int j) {
    if (nodes[i] == null)
      nodes[i] = new Node(i);
    if (nodes[j] == null)
      nodes[j] = new Node(j);
    if (adjList[i] == null)
      adjList[i] = new ArrayList<Node>();
    if (adjList[j] == null)
      adjList[j] = new ArrayList<Node>();
    adjList[i].add(nodes[j]);
    adjList[j].add(nodes[i]);
  }

  public void addZombies(int i, int noOfZombies) {
    nodes[i].outGoingZombies = noOfZombies;
  }

  public void printTopNZombies(int k) {
    final PriorityQueue<Integer> q =
        new PriorityQueue<Integer>(n, Collections.reverseOrder());
    for (int i = 0; i < n; i++) {
      q.offer((int) (0.5 + nodes[i].outGoingZombies));
    }
    for (int i = 0; i < k; i++) {
      System.out.print(q.poll());
      System.out.print(" ");
    }
    System.out.println();
  }

  final class Node {
    final int i;
    float incomingZombies;
    float outGoingZombies;

    public Node(int i) {
      this.i = i;
    }

    float outGoingZombiesPerNode() {
      return outGoingZombies / adjList[i].size();
    }

    boolean saturated() {
      return Math.abs(outGoingZombies - incomingZombies) < 0.01;
    }
  }
}

class Solution {
  public static void main(String[] args) {
    final BufferedReader br =
        new BufferedReader(new InputStreamReader(System.in));
    final Scanner s = new Scanner(br);
    int T = s.nextInt();
    for (int t = 0; t < T; t++) {
      final int n = s.nextInt();
      final int m = s.nextInt();
      final int k = s.nextInt();
      final NYGraph g = new NYGraph(n, m);
      for (int i = 0; i < m; i++) {
        g.addEdge(s.nextInt(), s.nextInt());
      }
      for (int i = 0; i < n; i++) {
        g.addZombies(i, s.nextInt());
      }
      for (int i = 0; i < k; i++) {
        if (!g.timestep()) {
          break;
        }
      }
      g.printTopNZombies(5);
    }
  }
}
