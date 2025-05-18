package Study.LinkedList;

import Study.Helper;
import Study.Helper.ListNode;

/**
 * Given the head of a linked list, rotate the list to the right by k places.
 */
public class RotateList {
    public static void main(String[] args) {
        Helper helper = new Helper();
        int k = 2;
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        helper.printLL(head, "Original Linked List");
        System.out.println("Rotate by k: " + k);

        RotateList rotateList = new RotateList();
        ListNode result = rotateList.rotateRight(head, k);

        // Print the rotated linked list
        helper.printLL(result, "Rotated Linked List");
    }

    /**
     Time: O(n)
     Space: O(1)
     */
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;

        ListNode temp = head;
        int cnt = 1;

        // count the number of nodes in the LinkedList
        while (temp.next != null) {
            temp = temp.next;
            cnt++;
        }

        // calculate the number of nodes to be changed
        k = k % cnt;
        if (k == 0) return head;

        // maintain and slow and fast pointer to maintain the particular distance
        ListNode slow = head, fast = head;
        while (k > 0) {
            fast = fast.next;
            k--;
        }

        // iterate till the last, and update slow and fast pointers
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // make the switch for places between left and right sides
        ListNode rightSide = slow.next;
        slow.next = null;
        fast.next = head;

        return rightSide;
    }
}
