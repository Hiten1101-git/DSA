package Study.LinkedList;

import Study.Helper;
import Study.Helper.ListNode;

/**
 * You are given two non-empty linked lists representing two non-negative integers. The digits are stored in
 * reverse order, and each of their nodes contains a single digit. Add the two numbers and return the sum as a
 * linked list.
 *.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 * .
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        Helper helper = new Helper();
        ListNode l1 = new ListNode(2, new ListNode(4, new ListNode(3)));
        ListNode l2 = new ListNode(5, new ListNode(6, new ListNode(4)));

        AddTwoNumbers addTwoNumbers = new AddTwoNumbers();
        ListNode result = addTwoNumbers.addTwoNumbers(l1, l2);

        helper.printLL(l1, "L1");
        helper.printLL(l2, "L2");
        helper.printLL(result, "Result");
    }

    /**
     * Time Complexity: O(max(m, n)), where m and n are the lengths of the two linked lists.
     * Space Complexity: O(max(m, n)), for the result linked list.
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode curr = dummyHead;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;

            int sum = x + y + carry;

            carry = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummyHead.next;
    }
}
