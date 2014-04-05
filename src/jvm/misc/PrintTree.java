package misc;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;


// root is at index 1
// left tree at 2*i or -1
// right tree at 2*i + 1 or -1;

public class PrintTree {
    static void levelOrder(int[] tree) {
        final Queue<Object> q = new LinkedList<Object>();
        q.offer(1);
        q.offer("|"); // level separator

        while (q.size() > 1) {
            final Object e = q.poll();
            if (e instanceof Integer) {
                final int n = (Integer) e;
                System.out.print(n + " ");

                for (final int child : children(tree, n)) {
                    q.offer(child);
                }
            } else {
                q.offer("|"); // level separator;
                System.out.println();
            }
        }
    }

    private static Collection<Integer> children(int[] tree, int n) {
        final Collection<Integer> children = new LinkedList<Integer>();
        final int l = left(tree, n);
        if (l > 0) {
            children.add(l);
        }
        final int r = right(tree, n);
        if (r > 0) {
            children.add(r);
        }
        return children;
    }

    static int left(int[] nodes, int i) {
        if (2 * i < nodes.length) {
            return nodes[2 * i];
        } else {
            return -1;
        }
    }

    static int right(int[] nodes, int i) {
        if (2 * i + 1 < nodes.length) {
            return nodes[2 * i + 1];
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        // 0th index not used
        PrintTree.levelOrder(new int[] {0, 1, 2, 3, 4, 5, 6, 7});
    }
}
