package Study.Stack;

import java.util.Stack;

public class BasicCalculator {
    public static void main(String[] args) {
        String s = "(1+(4+5+2)-3)+(6+8)";
        BasicCalculator bc = new BasicCalculator();
        System.out.println("Input: " + s);
        System.out.println("Output: " + bc.calculate(s));
    }

    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        int number = 0;
        int sign = 1;

        for (int i = 0 ; i < s.length() ; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                number = 10 * number + (c - '0');
            } else if (c == '+') {
                result += sign * number;
                number = 0;
                sign = 1;
            } else if (c == '-') {
                result += sign * number;
                number = 0;
                sign = -1;
            } else if (c == '(') {
                stack.push(result);
                stack.push(sign);
                sign = 1;
                result = 0;
            } else if (c == ')') {
                result += sign * number;
                number = 0;
                result *= stack.pop(); // pop sign
                result += stack.pop(); // pop result
            }
        }

        if (number != 0) result += sign * number;

        return result;
    }
}
