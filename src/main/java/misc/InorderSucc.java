package misc;

public class InorderSucc {
  Tree find(Tree root, Tree node) {
    if (root == null || node == null) {
      throw new IllegalArgumentException();
    }

    if (node.right != null) {
      return minimum(node.right);
    }

    return find(root, node, null);
  }

  private Tree find(Tree root, Tree node, Tree parent) {
    /*
     * return the lowest ancestor of 'node' such that
     * 'node' lies in the left subtree of parent
     */
    if (root == null) {
      return null;
    }

    if (root == node) {
      return parent;
    }

    Tree ans = find(root.left, node, root);
    if (ans == null) {
      ans = find(root.right, node, parent);
    }
    return ans;
  }

  private Tree minimum(Tree node) {
    if (node.left != null) {
      return minimum(node.left);
    } else {
      return node;
    }
  }
}
