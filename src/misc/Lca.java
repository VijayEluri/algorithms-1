package misc;

public class Lca {
    class Node { Object data; Node left, right; }
    Node lca = null;

    int findLcaInternal(Node root, Node left, Node right) {
        if (root == null)
            return 2;  //2 -> both left and right missing under tree rooted at root

        if (root == left || root == right) {
            int l = findLcaInternal(root.left, left, right);
            if (l == 0) {
                int r = findLcaInternal(root.right, left, right);
                if (r == 0) return 1;
                else { lca = root; return 0; }  // r == 1
            } else { // l == 1
                lca = root;
                return 0;
            }
        }

        int l = findLcaInternal(root.left, left, right);
        if (l == 0) return 0;
        int r = findLcaInternal(root.right, left, right);
        if (l == 1) {  //1 -> either left or right node has been found under tree rooted at root.
            if (r == 2) return 1;
            if (r == 1) {
                lca = root;
                return 0;
            }
            //r cannot be equal to 0 since l == 1
        }
        //l == 2
        return r;
    }

    Node findLca(Node root, Node left, Node right) {
        int result = findLcaInternal(root, left, right);
        if (result == 0)
            return lca;
        else
          return null;
    }
}
