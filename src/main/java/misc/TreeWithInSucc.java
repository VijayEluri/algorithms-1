package misc;

public class TreeWithInSucc {
  class Container {
    TreeWithInSucc lastPrinted;
  }

  TreeWithInSucc left;
  TreeWithInSucc right;
  TreeWithInSucc successor;

  void updateSuccessor(TreeWithInSucc root) {
    updateSuccessor(root, new Container());
  }

  void updateSuccessor(TreeWithInSucc root, Container container) {
    if (root != null) {
      updateSuccessor(root.left, container);
      update(container, root);
      container.lastPrinted = root;
      updateSuccessor(root.right, container);
    }
  }

  void update(Container container, TreeWithInSucc successor) {
    if (container.lastPrinted != null) {
      container.lastPrinted.successor = successor;
    }
  }
}