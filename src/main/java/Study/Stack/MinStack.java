package Study.Stack;

import java.util.Stack;

/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 *.
 * Implement the MinStack class:
 *.
 * MinStack() initializes the stack object.
 * void push(int val) pushes the element val onto the stack.
 * void pop() removes the element on the top of the stack.
 * int top() gets the top element of the stack.
 * int getMin() retrieves the minimum element in the stack.
 * You must implement a solution with O(1) time complexity for each function.
 */
public class MinStack {
    public static void main(String[] args) {
        MinStack1 minStack = new MinStack1();
        System.out.println("Stack: " + minStack + " Minimum: " + minStack.getMin()); // Returns 2
        minStack.push(3);
        minStack.push(5);
        System.out.println("Stack: " + minStack + " Minimum: " + minStack.getMin());
        minStack.push(2);
        minStack.push(1);
        System.out.println("Stack: " + minStack + " Minimum: " + minStack.getMin()); // Returns 1
        minStack.pop();
        System.out.println("Stack: " + minStack + " Top: " + minStack.top()); // Returns 2
        System.out.println("Stack: " + minStack + " Minimum: " + minStack.getMin()); // Returns 2
    }

    private static class MinStack1 {
        private final Stack<Integer> stack;

        public MinStack1() {
            stack = new Stack<>();
        }

        public void push(int val) {
            stack.push(val);
        }

        public void pop() {
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            if (stack.isEmpty()) return 0;

            int mini = Integer.MAX_VALUE;
            for (int i : stack) {
                mini = Math.min(i, mini);
            }
            return mini;
        }

        public String toString() {
            return stack.toString();
        }
    }
}
