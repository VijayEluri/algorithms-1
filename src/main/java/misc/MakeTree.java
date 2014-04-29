package misc;

public class MakeTree {
  final class Data {
    final Tree root;
    final String whatsLeft;

    Data(Tree _root, String _whatsLeft) {
      root = _root;
      whatsLeft = _whatsLeft;
    }
  }

  Tree makeFromPreOrder(String s) {
    return make(s).root;
  }

  private Data make(String s) {
    if (s == "") {
      return new Data(null, s);
    }

    if (s.charAt(0) == 'L') {
      return new Data(new Tree(s.charAt(0)), (s.length() == 1 ? "" : s.substring(1)));
    }

    Data left = make(s.substring(1));
    Data right = make(left.whatsLeft);

    return new Data(new Tree(s.charAt(0), left.root, right.root), right.whatsLeft);
  }

  public static void main(String[] _) {
    System.out.println(new MakeTree().makeFromPreOrder("NNLNLLNLL"));
  }
}
