package Study.LinkedList;

import Study.Helper;
import Study.Helper.ListNode;

public class RemoveNthFromEnd {
    public static void main(String[] args) {
        Helper helper = new Helper();
        int n = 2;
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        helper.printLL(head, "Original Linked List");
        System.out.println("n: " + n);

        RemoveNthFromEnd removeNthFromEnd = new RemoveNthFromEnd();
        ListNode result = removeNthFromEnd.removeNthFromEnd(head, n);

        // Print the modified linked list
        helper.printLL(result, "Modified Linked List");
    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode res = new ListNode(0, head);
        ListNode dummy = res;

        for (int i = 0 ; i < n ; i++) {
            head = head.next;
        }

        while (head != null) {
            head = head.next;
            dummy = dummy.next;
        }

        dummy.next = dummy.next.next;

        return res.next;
    }
}
