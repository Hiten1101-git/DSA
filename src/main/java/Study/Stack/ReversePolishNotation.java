package Study.Stack;

import java.util.Arrays;
import java.util.Stack;

/**
 * You are given an array of strings tokens that represents a valid arithmetic expression in Reverse Polish Notation.
 * Return the integer that represents the evaluation of the expression.
 *.
 * The operands may be integers or the results of other operations.
 * The operators include '+', '-', '*', and '/'.
 * Assume that division between integers always truncates toward zero.
 * .
 * Example 1:
 * Input: tokens = ["2","1","+","3","*"]
 * Output: 9
 * Explanation: ((2 + 1) * 3) = 9
 */
public class ReversePolishNotation {
    public static void main(String[] args) {
        String[] tokens = {"2", "1", "+", "3", "*"};
        ReversePolishNotation rpn = new ReversePolishNotation();
        System.out.println("Input: " + Arrays.deepToString(tokens));
        System.out.println("Output: " + rpn.evalRPN(tokens)); // Output: 9
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private int evalRPN(final String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String c : tokens) {
            evaluate(c, stack);
        }
        return stack.pop();
    }

    private void evaluate(final String c, final Stack<Integer> stack) {
        switch (c) {
            case "+":
                stack.push(stack.pop() + stack.pop());
                break;
            case "-": {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b - a);
                break;
            }
            case "*":
                stack.push(stack.pop() * stack.pop());
                break;
            case "/": {
                int a = stack.pop();
                int b = stack.pop();
                stack.push(b / a);
                break;
            }
            default:
                stack.push(Integer.parseInt(c));
                break;
        }
    }
}
