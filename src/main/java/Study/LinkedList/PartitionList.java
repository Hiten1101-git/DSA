package Study.LinkedList;

import Study.Helper;
import Study.Helper.ListNode;

/**
 * Given the head of a linked list and a value x, partition it such that all nodes less than x come before
 * nodes greater than or equal to x.
 *.
 * You should preserve the original relative order of the nodes in each of the two partitions.
 */
public class PartitionList {
    public static void main(String[] args) {
        Helper helper = new Helper();
        int x = 3;
        ListNode head = new ListNode(1);
        head.next = new ListNode(4);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(2);
        helper.printLL(head, "Modified Linked List");
        System.out.println("x: " + x);

        PartitionList partitionList = new PartitionList();
        ListNode result = partitionList.partition(head, x);

        // Print the modified linked list
        helper.printLL(result, "Modified Linked List");
    }

    /**
     Time: O(n)
     Space: O(1)
     */
    public ListNode partition(ListNode head, int x) {
        ListNode before = new ListNode(0);
        ListNode after = new ListNode(0);
        ListNode before_curr = before;
        ListNode after_curr = after;

        while (head != null) {
            if (head.val < x) {
                before_curr.next = head;
                before_curr = before_curr.next;
            } else {
                after_curr.next = head;
                after_curr = after_curr.next;
            }
            head = head.next;
        }

        after_curr.next = null;
        before_curr.next = after.next;

        return before.next;
    }
}
