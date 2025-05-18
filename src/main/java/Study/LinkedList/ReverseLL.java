package Study.LinkedList;

import Study.Helper;
import Study.Helper.ListNode;

/**
 * Given the head of a singly linked list, reverse the list, and return the reversed list.
 */
public class ReverseLL {
    public static void main(String[] args) {
        Helper helper = new Helper();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        helper.printLL(head, "Original Linked List");

        ReverseLL reverseLL = new ReverseLL();
        ListNode reversedHead = reverseLL.reverseList(head);

        // Print the reversed linked list
        helper.printLL(reversedHead, "Reversed Linked List");
    }

    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }

        return prev;
    }
}
