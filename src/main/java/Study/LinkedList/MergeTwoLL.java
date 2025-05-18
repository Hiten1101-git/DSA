package Study.LinkedList;

import Study.Helper;
import Study.Helper.ListNode;

/**
 * You are given the heads of two sorted linked lists list1 and list2.
 *.
 * Merge the two lists into one sorted list. The list should be made by splicing together the nodes of the
 * first two lists.
 *.
 * Return the head of the merged linked list.
 * .
 * Input: list1 = [1,2,4], list2 = [1,3,4]
 * Output: [1,1,2,3,4,4]
 * .
 * Input: list1 = [], list2 = [0]
 * Output: [0]
 */
public class MergeTwoLL {
    public static void main(String[] args) {
        Helper helper = new Helper();
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(2);
        l1.next.next = new ListNode(4);

        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        helper.printLL(l1, "l1");
        helper.printLL(l2, "l2");

        MergeTwoLL mergeTwoLL = new MergeTwoLL();
        ListNode mergedList = mergeTwoLL.mergeTwoLists(l1, l2);

        helper.printLL(mergedList, "mergedList");

    }

    /**
     * Time Complexity: O(n + m), where n and m are the lengths of the two linked lists.
     * Space Complexity: O(1), as we are modifying the existing lists.
     *
     * @param l1 - first linked list
     * @param l2 - second linked list
     * @return - merged linked list
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode newList = new ListNode();
        ListNode curr = newList;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        curr.next = l1 != null ? l1 : l2;

        return newList.next;
    }
}
