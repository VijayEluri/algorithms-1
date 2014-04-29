package misc;

public class SymmetricTree {
  boolean isSymmetric(Tree root) {
    if (root == null || leaf(root)) {
      return true;
    }
    return isMirror(root.left, root.right);
  }

  private boolean leaf(Tree root) {
    return false;
  }

  private boolean isMirror(Tree a, Tree b) {
    return (a == null && b == null) || (isMirror(a.left, b.right) && isMirror(a.right, b.left));
  }
}
