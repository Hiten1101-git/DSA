package Study.LinkedList;

import Study.Helper;
import Study.Helper.ListNode;

/**
 * Given the head of a singly linked list and two integers left and right where left <= right, reverse the
 * nodes of the list from position left to position right, and return the reversed list.
 */
public class ReverseLL2 {
    public static void main(String[] args) {
        Helper helper = new Helper();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        helper.printLL(head, "Original Linked List");

        ReverseLL2 reverseLL2 = new ReverseLL2();
        ListNode reversedHead = reverseLL2.reverseBetween(head, 2, 4);

        // Print the reversed linked list
        helper.printLL(reversedHead, "Reversed Linked List");
    }

    /**
     Explanation:
     •	dummy helps simplify cases where left is 1 (i.e., the head itself changes).
     •	prev points to the node before the reversal section.
     •	curr is the first node of the section to be reversed.
     •	In each iteration, we remove the node after curr and insert it right after
     prev, effectively reversing the list one node at a time

     Time: O(n)
     Space: O(1)
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;

        // move prev to node just before left
        for (int i = 1 ; i < left ; i++) {
            prev = prev.next;
        }

        // reverse sublist from left to right
        ListNode curr = prev.next;
        for (int i = 0 ; i < right - left ; i++) {
            ListNode temp = curr.next;
            curr.next = temp.next;
            temp.next = prev.next;
            prev.next = temp;
        }

        return dummy.next;
    }
}
