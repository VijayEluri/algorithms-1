//package usaco;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.PriorityQueue;

/**
ID: rdsr.me1 
PROG: comehome 
LANG: JAVA
*/
public class comehome {
    Map<Character, Collection<Node>> adjL;
    char pasture;
    int minDistance = Integer.MAX_VALUE;

    comehome() {
        adjL = new HashMap<Character, Collection<Node>>();
    }

    public static void main(String[] args) throws IOException {
        final FastScanner s = new FastScanner("comehome.in");
        final int nEdges = s.nextInt();

        final comehome ch = new comehome();
        for (int i = 0; i < nEdges; i++) {
            final char c = s.next().charAt(0);
            final char d = s.next().charAt(0);
            final int distance = s.nextInt();

            ch.addEdge(c, d, distance);
            ch.addEdge(d, c, distance);
        }
        s.close();

        ch.shortestPaths();
        final PrintWriter pw = new PrintWriter(new File("comehome.out"));
        pw.println(ch.pasture + " " + ch.minDistance);
        pw.close();
    }

    void shortestPaths() {
        final Map<Character, Integer> distanceFromSource = new HashMap<Character, Integer>();
        final PriorityQueue<Node> heap = new PriorityQueue<Node>();

        for (final Character c : adjL.keySet()) {
            int d;
            if (c.equals('Z')) {
                d = 0;
            } else {
                d = Integer.MAX_VALUE;
            }

            heap.offer(new Node(c, d));
            distanceFromSource.put(c, d);
        }

        int nNodes = 0;
        while (nNodes < adjL.size()) {
            final Node node = heap.poll();
            for (final Node nieghbor : adjL.get(node.v)) {
                final int d = distanceFromSource.get(node.v) + nieghbor.distance;
                if (d < distanceFromSource.get(nieghbor.v)) {
                    final Node update = new Node(nieghbor.v, d);
                    heap.remove(nieghbor);
                    heap.add(update);
                    distanceFromSource.put(nieghbor.v, d);
                }
            }
            nNodes += 1;
        }

        for (final Entry<Character, Integer> e : distanceFromSource.entrySet()) {
            if (e.getKey() >= 'A' && e.getKey() < 'Z' && e.getValue() < minDistance) {
                pasture = e.getKey();
                minDistance = e.getValue();
            }
        }
    }

    private void addEdge(char c, char d, int distance) {
        Collection<Node> nodes = adjL.get(c);
        if (nodes == null) {
            adjL.put(c, nodes = new LinkedList<Node>());
        }
        nodes.add(new Node(d, distance));
    }
}


class Node implements Comparable<Node> {
    Character v;
    int distance;

    public Node(Character v, int distance) {
        this.v = v;
        this.distance = distance;
    }

    @Override
    public int compareTo(Node o) {
        return distance - o.distance;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + distance;
        result = prime * result + ((v == null) ? 0 : v.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Node other = (Node) obj;
        if (distance != other.distance) {
            return false;
        }
        if (v == null) {
            if (other.v != null) {
                return false;
            }
        } else if (!v.equals(other.v)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Node [v=" + v + ", distance=" + distance + "]";
    }
}

class FastScanner implements Closeable {
    private final BufferedReader reader;
    private StringTokenizer tokenizer;

    public FastScanner(String filename) throws IOException {
        reader = new BufferedReader(new FileReader(filename));
        tokenizer = new StringTokenizer("");
    }

    public String next() throws IOException {
        while (!tokenizer.hasMoreTokens()) {
            tokenizer = new StringTokenizer(reader.readLine());
        }
        return tokenizer.nextToken();
    }

    public int nextInt() throws IOException {
        return Integer.parseInt(next());
    }


    public double nextDouble() throws IOException {
        return Double.parseDouble(next());
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}

