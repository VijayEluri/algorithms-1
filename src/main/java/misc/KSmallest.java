package misc;

public class KSmallest {
  int value = -1;

  int find(Tree root, int k) {
    if (root == null) {
      return 0;
    }

    int l = find(root.left, k);
    if (l < k - 1) {
      int r = find(root.right, k - 1 - l);
      return l + r + 1;
    }

    if (l == k - l) {
      value = root.data;
      return l + 1;
    } else {
      return l;
    }
  }
}
