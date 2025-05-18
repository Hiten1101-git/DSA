package Study.LinkedList;

import Study.Helper;
import Study.Helper.ListNode;

/**
 * Given the head of a sorted linked list, delete all nodes that have duplicate numbers, leaving only distinct
 * numbers from the original list. Return the linked list sorted as well.
 */
public class RemoveDuplicates {
    public static void main(String[] args) {
        Helper helper = new Helper();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next.next = new ListNode(5);

        helper.printLL(head, "Original Linked List");

        RemoveDuplicates removeDuplicates = new RemoveDuplicates();
        ListNode result = removeDuplicates.deleteDuplicates(head);

        // Print the modified linked list
        helper.printLL(result, "Modified Linked List");
    }

    /**
     Time: O(n)
     Space: O(1)
     */
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return null;

        ListNode dummy = new ListNode(0, head);
        ListNode prev = dummy;
        ListNode curr = head;

        while (curr != null) {
            boolean isDuplicate = false;
            // Check if current value is duplicated
            while (curr.next != null && curr.val == curr.next.val) {
                curr = curr.next;
                isDuplicate = true;
            }

            if (isDuplicate) {
                // Skip all duplicates
                curr = curr.next;
                continue;
            }

            // Link previous if no duplicates
            prev.next = curr;
            prev = prev.next;
            curr = curr.next;
        }

        // Ensure last unique node doesn't link to duplicates
        prev.next = curr;

        return dummy.next;
    }
}
