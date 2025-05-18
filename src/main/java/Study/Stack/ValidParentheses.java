package Study.Stack;

import java.util.HashMap;
import java.util.Stack;

/**
 * You are given a string s consisting of the following characters: '(', ')', '{', '}', '[' and ']'.
 *.
 * The input string s is valid if and only if:
 *.
 * Every open bracket is closed by the same type of close bracket.
 * Open brackets are closed in the correct order.
 * Every close bracket has a corresponding open bracket of the same type.
 * Return true if s is a valid string, and false otherwise.
 */
public class ValidParentheses {
    public static void main(String[] args) {
        String s = "([{}])";
        System.out.println("Input: " + s);
        ValidParentheses vp = new ValidParentheses();
        System.out.println("Output: " + vp.isValid(s));
    }

    /**
     Time: O(n)
     Space: O(n)
     */
    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        HashMap<Character, Character> hm = getParenthesisMap();

        for (char c : s.toCharArray()) {
            if (hm.containsKey(c)) {
                if (!stack.isEmpty() && stack.peek() == hm.get(c)) {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    private HashMap<Character, Character> getParenthesisMap() {
        HashMap<Character, Character> hm = new HashMap<>();
        hm.put(']', '[');
        hm.put(')', '(');
        hm.put('}', '{');
        return hm;
    }
}
