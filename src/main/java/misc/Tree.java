package misc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class Tree {
  Integer data;
  Tree left;
  Tree right;
  Tree parent;

  public Tree(int _data) {
    this(_data, null, null);
  }

  public Tree(int _data, Tree l, Tree r) {
    data = _data;
    left = l;
    right = r;
  }

  static String asString(Tree root, int paddingLen) {
    char[] paddingArray = new char[paddingLen];
    Arrays.fill(paddingArray, ' ');
    String padding = new String(paddingArray);

    if (root == null) {
      return padding + "|\n";
    }

    int len = root.data.toString().length();
    return asString(root.right, paddingLen + len + 1) +
        padding + "/\n" +
        padding + root.data.toString() + "\n" +
        padding + "\\\n" +
        asString(root.left, paddingLen + len + 1);
  }

  @Override
  public String toString() {
    return asString(this, 0);
  }

  static Tree fromList(List<Integer> l) {
    if (l == null || l.isEmpty()) {
      return null;
    }

    if (l.size() == 1) {
      return new Tree(l.get(0), null, null);
    }

    int mid = l.size() / 2;
    Tree leftChild = fromList(l.subList(0, mid));
    Tree rightChild = fromList(l.subList(mid + 1, l.size()));
    return new Tree(l.get(mid), leftChild, rightChild);
  }

  static List<Integer> toList(Tree root) {
    if (root == null) {
      return new LinkedList<Integer>();
    }

    List<Integer> left = toList(root.left);
    List<Integer> right = toList(root.right);

    left.add(root.data);
    left.addAll(right);

    return left;
  }

  static boolean isBST(Tree root) {
    if (root == null) {
      return true;
    }

    return nodeWithinRange(root.left, Integer.MIN_VALUE, root.data) && nodeWithinRange(root.right, root.data,
        Integer.MAX_VALUE);
  }

  static private boolean nodeWithinRange(Tree node, int minValue, int maxValue) {
    if (node == null) {
      return true;
    }

    return node.data >= minValue &&
        node.data <= maxValue &&
        nodeWithinRange(node.left, minValue, node.data) &&
        nodeWithinRange(node.right, node.data, maxValue);
  }

  int noOfLeaves(Tree root) {
    if (root == null) {
      return 0;
    }

    if (root.left == null && root.right == null) {
      return 1;
    }

    return noOfLeaves(root.left) + noOfLeaves(root.right);
  }

  int noOfNodes(Tree root) {
    if (root == null) {
      return 0;
    }
    return noOfNodes(root.left) + noOfNodes(root.right) + 1;
  }

  boolean hasPathSum(Tree root, int sum) {
    if (root == null) {
      return sum == 0;
    }

    return sum > root.data && (hasPathSum(root.left, sum - root.data) || hasPathSum(root.right, sum - root.data));
  }

  static boolean hasSum(Tree root, int s) {
    if (s == 0) {
      return true;
    }

    if (root == null) {
      return false;
    }

    for (int i = 0; i <= s - root.data; i++) {
      if (hasSum(root.left, s - i - root.data) && hasSum(root.right, i)) {
        return true;
      }
    }

    return false;
  }

  void printPaths(Tree root) {
    if (root == null) {
      return;
    }

    printPathsHelper(root, new ArrayList<Integer>(), 0);
  }

  private void printPathsHelper(Tree node, List<Integer> path, int i) {
    if (node == null) {
      System.out.println(path.subList(0, i + 1));
    }

    path.add(i + 1, node.data);
    printPathsHelper(node.left, path, i + 1);
    printPathsHelper(node.left, path, i + 1);
  }

  public static long countTrees(int n) {
    if (n < 0) {
      throw new IllegalArgumentException(n + " shouldn't be negative");
    }
    if (n == 0 || n == 1 || n == 2) {
      return n;
    }

    long cnt_n_1 = countTrees(n - 1);
    long cnt_n_1_by_2 = countTrees((n - 1) / 2);
    long cnt_n_1_by_2_add_1 = countTrees((n - 1) / 2 + 1);

    if (n % 2 == 0)  // n - 1 == odd
    {
      return 2 * cnt_n_1 + 2 * cnt_n_1_by_2 * cnt_n_1_by_2_add_1;
    } else {
      return 2 * cnt_n_1 + cnt_n_1_by_2 * cnt_n_1_by_2;
    }
  }

  static Tree lca(Tree root, Tree a, Tree b) {
    if (a == null || b == null) {
      return null;
    }
    Tree lca = lca(root.right, a, b);
    if (lca != null) {
      return lca;
    }
    lca = lca(root.left, a, b);
    if (lca != null) {
      return lca;
    }
    return root;
  }

  public static void main(String[] _) {
    //System.out.println(Tree.hasSum(Tree.fromList((Arrays.asList(1, 10, 3, 4, 5, 6, 7, 8))), 16));
    System.out.println(countTrees(50));
  }
}