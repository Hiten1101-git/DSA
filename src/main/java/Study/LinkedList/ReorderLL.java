package Study.LinkedList;

import Study.Helper;
import Study.Helper.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given the head of a singly linked-list.
 *.
 * The positions of a linked list of length = 7 for example, can intially be represented as:
 *.
 * [0, 1, 2, 3, 4, 5, 6]
 *.
 * Reorder the nodes of the linked list to be in the following order:
 *.
 * [0, 6, 1, 5, 2, 4, 3]
 *.
 * Notice that in the general case for a list of length = n the nodes are reordered to be in the following order:
 *.
 * [0, n-1, 1, n-2, 2, n-3, ...]
 *.
 * You may not modify the values in the list's nodes, but instead you must reorder the nodes themselves.
 */
public class ReorderLL {
    public static void main(String[] args) {
        Helper helper = new Helper();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        helper.printLL(head, "Original Linked List");

        ReorderLL reorderLL = new ReorderLL();
        reorderLL.reorderList(head);

        // Print the reordered linked list
        helper.printLL(head, "Reordered Linked List");
    }

    public void reorderList(ListNode head) {
        // bruteForce(head);
        recursion(head);
    }

    /**
     * Brute Force approach
     * time: O(n)
     * space: O(n)
     */
    private void bruteForce(ListNode head) {
        if (head == null) {
            return;
        }

        // add all the elements to the arraylist
        List<ListNode> nodes = new ArrayList<>();
        ListNode cur = head;
        while(cur != null) {
            nodes.add(cur);
            cur = cur.next;
        }

        // traverse using 2 pointers, at start and at end
        int i = 0, j = nodes.size() - 1;
        while(i < j) {
            nodes.get(i).next = nodes.get(j);
            i++;
            if (i >= j) {
                break;
            }
            nodes.get(j).next = nodes.get(i);
            j--;
        }

        // mark the last node
        nodes.get(i).next = null;
    }

    /**
     * Recurrsion approach
     * time: O(n)
     * space: O(n)
     */
    private void recursion(ListNode head) {
        head = recursion(head, head.next);
    }

    /**
     * At a given point of time, root and cur will be as:
     * 0, n-1 | 1, n-2 | 2, n-3...
     */
    private ListNode recursion(ListNode root, ListNode cur) {
        if (cur == null) {
            return root;
        }

        root = recursion(root, cur.next);
        if (root == null) {
            return null;
        }

        ListNode tmp = null;
        if (root == cur || root.next == cur) {
            cur.next = null;
        } else {
            tmp = root.next;
            root.next = cur;
            cur.next = tmp;
        }

        return tmp;
    }
}
