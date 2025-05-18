package Study.LinkedList;

import Study.Helper;
import Study.Helper.ListNode;

/**
 * Given head, the head of a linked list, determine if the linked list has a cycle in it.
 *.
 * There is a cycle in a linked list if there is some node in the list that can be reached again by continuously
 * following the next pointer. Internally, pos is used to denote the index of the node that tail's next pointer
 * is connected to. Note that pos is not passed as a parameter.
 *.
 * Return true if there is a cycle in the linked list. Otherwise, return false.
 * .
 * Input: head = [3,2,0,-4], pos = 1
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the second node.
 * .
 * Input: head = [1,2], pos = 0
 * Output: true
 * Explanation: There is a cycle in the linked list, where tail connects to the first node.
 */
public class DetectCycles {
    public static void main(String[] args) {
        ListNode head = new ListNode(3);
        head.next = new ListNode(2);
        head.next.next = new ListNode(0);
        head.next.next.next = new ListNode(-4);
        head.next.next.next.next = head.next; // Creating a cycle

        DetectCycles detectCycles = new DetectCycles();
        boolean hasCycle = detectCycles.hasCycle(head);

        System.out.println("Has Cycle: " + hasCycle); // Output: true
    }

    /**
     * Time Complexity: O(n), where n is the number of nodes in the linked list.
     * Space Complexity: O(1), as we are using only two pointers.
     */
    public boolean hasCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (fast == slow) {
                return true;
            }
        }

        return false;
    }
}
