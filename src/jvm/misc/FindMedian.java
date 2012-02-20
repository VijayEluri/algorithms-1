package misc;

import java.util.HashMap;
import java.util.Map;

public class FindMedian {
  Map<Tree, Integer> noOfNodes = new HashMap<Tree, Integer>();
  
  int find(Tree root) {
    nodesUnder(root);
    return find(root, 0, 0);
  }

  private int find(Tree root, int pltr, int pgtr) {
    if (root == null)
      throw new IllegalStateException("Median not found, this is impossible!");
    
    int ltr = noOfNodes.get(root.left);
    int gtr = noOfNodes.get(root.right);
    
    int diff = Math.abs(ltr + pltr - (gtr + pgtr));
    if (diff == 0)
      return root.data;
    
    if (diff == 1) {
      if (ltr + pltr > gtr + pgtr)
        return (root.data + findMimimum(root.left))/2;
      else
        return (root.data + findMimimum(root.right))/2;
    } else {
      if (ltr + pltr > gtr + pgtr)
        return find(root.left, pltr + ltr, pgtr + gtr);
      else
        return  find(root.right, pltr + ltr + 1, pgtr + gtr);
    }
  }

  private Integer findMimimum(Tree right) {
    // TODO Auto-generated method stub
    return null;
  }

  private int nodesUnder(Tree root) {
    if (root == null) {
      noOfNodes.put(null, 0);
      return 0;
    }
    
    int l = nodesUnder(root.left);
    int r = nodesUnder(root.right);
    
    noOfNodes.put(root, 1 + l + r);
    return 1 + l + r;
  }
}
