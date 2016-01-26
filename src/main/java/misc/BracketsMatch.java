package misc;

import java.util.Stack;

public class BracketsMatch {
  public static void main(String[] args) {
    System.out.println(doBracketsMatch("[(5+4)*{(5/7)-(5+(8/4))}]"));
    System.out.println(doBracketsMatch(""));
  }

  private static boolean doBracketsMatch(String s) {
    if (s == null || s.length() == 0) {
      return true;
    }
    Stack<Character> stack = new Stack<>();
    for (char c : s.toCharArray()) {
      if (c == '[' || c == '(' || c == '{') {
        stack.push(c);
      }
      else if (c == ']' || c == ')' || c == '}') {
        if(!checkMatch(stack, opposite(c))) {
          return false;
        }
      }
    }
    return stack.isEmpty();
  }

  private static boolean checkMatch(Stack<Character> stack, char c) {
    if (stack.isEmpty()) {
      return false;
    } else {
      return stack.pop() == c;
    }
  }

  private static char opposite(char c) {
    if (c == ']') return '[';
    if (c == ')') return '(';
    if (c == '}') return '{';
    throw new IllegalArgumentException("Not a valid char");
  }
}
