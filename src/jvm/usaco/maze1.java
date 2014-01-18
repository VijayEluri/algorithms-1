package usaco;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * ID: rdsr.me1 PROG: maze1 LANG: JAVA
 */
public class maze1 {
    int h, w;
    PointInMaze[][] maze;

    // exits
    int i1, j1, i2, j2;
    Dir dir1, dir2;

    maze1(int h, int w) {
        this.h = h;
        this.w = w;
        maze = new PointInMaze[h][w];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                maze[i][j] = new PointInMaze();
            }
        }
    }

    void openFence(int i, int j, Dir d) {
        maze[i][j].addFence(d);
    }

    void exit(int i, int j, Dir d) {
        if (dir1 == null) {
            i1 = i;
            j1 = j;
            dir1 = d;
        } else {
            i2 = i;
            j2 = j;
            dir2 = d;
        }
    }

    void print() {
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                System.out.println(i + " " + j + " " + maze[i][j]);
            }
        }
        System.out.println("exit1: " + i1 + j1 + dir1);
        System.out.println("exit2: " + i2 + j2 + dir2);
    }

    public static void main(String[] args) throws IOException {
        final BufferedReader br = new BufferedReader(new FileReader("maze1.in"));
        final String[] nos = br.readLine().split(" ");
        final int w = Integer.valueOf(nos[0]);
        final int h = Integer.valueOf(nos[1]);

        char[][] input = new char[2 * h + 1][2 * w + 1];
        for (int i = 0; i < input.length; i++) {
            final String l = br.readLine();
            for (int j = 0; j < l.length(); j++) {
                input[i][j] = l.charAt(j);
            }
        }
        br.close();

        final maze1 m = new maze1(h, w);
        int ii = 0;
        for (int i = 1; i < input.length - 1; i += 2) {
            int jj = 0;
            for (int j = 1; j < input[i].length - 1; j += 2) {
                if (input[i - 1][j] == ' ') {
                    m.openFence(ii, jj, Dir.UP);
                    if (ii == 0) {
                        m.exit(ii, jj, Dir.UP);
                    }
                }
                if (input[i][j + 1] == ' ') {
                    m.openFence(ii, jj, Dir.LEFT);
                    if (jj == w - 1) {
                        m.exit(ii, jj, Dir.LEFT);
                    }
                }
                if (input[i + 1][j] == ' ') {
                    m.openFence(ii, jj, Dir.DOWN);
                    if (ii == h - 1) {
                        m.exit(ii, jj, Dir.DOWN);
                    }
                }
                if (input[i][j - 1] == ' ') {
                    m.openFence(ii, jj, Dir.RIGHT);
                    if (jj == 0) {
                        m.exit(ii, jj, Dir.RIGHT);
                    }
                }
                jj += 1;
            }
            ii += 1;
        }
        input = null;

        final PrintWriter pw = new PrintWriter("maze1.out");
        pw.println(m.stepsFromEitherExit());
        pw.close();
    }

    private int stepsFromEitherExit() {
        final Map<State, Integer> distance1 = minSteps(new State(i1, j1));
        final Map<State, Integer> distance2 = minSteps(new State(i2, j2));

        int largetMinDistance = -1;
        for (final State s : distance1.keySet()) {
            int minDistance = 0;
            if (distance1.get(s) < distance2.get(s)) {
                minDistance = distance1.get(s);
            } else {
                minDistance = distance2.get(s);
            }
            if (minDistance > largetMinDistance) {
                largetMinDistance = minDistance;
            }
        }
        return largetMinDistance;
    }

    private Map<State, Integer> minSteps(State state) {
        final Map<State, Integer> distance = new HashMap<State, Integer>();
        final Queue<State> q = new LinkedList<State>();
        final Set<State> cache = new HashSet<State>();

        distance.put(state, 1);
        q.add(state);
        cache.add(state);

        while (!q.isEmpty()) {
            final State s = q.poll();
            for (final State n : neighbors(s)) {
                if (cache.add(n)) {
                    distance.put(n, distance.get(s) + 1);
                    q.offer(n);
                }
            }
        }
        return distance;
    }

    private Collection<State> neighbors(State s) {
        final Collection<State> r = new ArrayList<State>(4);
        for (final Dir d : maze[s.i][s.j].openFences) {
            if (d == Dir.UP && s.i - 1 >= 0) {
                r.add(new State(s.i - 1, s.j));
            }
            if (d == Dir.LEFT && s.j + 1 < w) {
                r.add(new State(s.i, s.j + 1));
            }
            if (d == Dir.DOWN && s.i + 1 < h) {
                r.add(new State(s.i + 1, s.j));
            }
            if (d == Dir.RIGHT && s.j - 1 >= 0) {
                r.add(new State(s.i, s.j - 1));
            }
        }
        return r;
    }
}


class State {
    int i, j;

    public State(int i, int j) {
        this.i = i;
        this.j = j;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + i;
        result = prime * result + j;
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
        final State other = (State) obj;
        if (i != other.i) {
            return false;
        }
        if (j != other.j) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "State [i=" + i + ", j=" + j + "]";
    }
}


class PointInMaze {
    Collection<Dir> openFences = new ArrayList<Dir>(4);

    void addFence(Dir d) {
        openFences.add(d);
    }

    @Override
    public String toString() {
        return openFences.toString();
    }
}


enum Dir {
    UP, RIGHT, DOWN, LEFT;
}
