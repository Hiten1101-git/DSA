package Study.LinkedList;

import java.util.HashMap;

public class CopyLLRandomPointer {

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public static void main(String[] args) {
        Node head = new Node(7);
        head.next = new Node(13);
        head.next.next = new Node(11);
        head.next.next.next = new Node(10);
        head.next.next.next.next = new Node(1);

        head.random = null;
        head.next.random = head;
        head.next.next.random = head.next.next.next;
        head.next.next.next.random = head;
        head.next.next.next.next.random = head;

        CopyLLRandomPointer copyLLRandomPointer = new CopyLLRandomPointer();
        Node copiedListHead = copyLLRandomPointer.copyRandomList(head);

        // Print the copied list
        while (copiedListHead != null) {
            System.out.println("Value: " + copiedListHead.val + ", Random: " + (copiedListHead.random != null ? copiedListHead.random.val : "null"));
            copiedListHead = copiedListHead.next;
        }
    }

    /**
     Start creating nodes and map for old and new nodes
     Then use the node to filling next and random values for copy

     Time: O(n)
     Space: O(n)
     */
    public Node copyRandomList(Node head) {
        HashMap<Node, Node> oldToCopy = new HashMap<>();
        oldToCopy.put(null, null);

        Node cur = head;
        while (cur != null) {
            Node copy = new Node(cur.val);
            oldToCopy.put(cur, copy);
            cur = cur.next;
        }

        cur = head;
        while (cur != null) {
            Node copy = oldToCopy.get(cur);
            copy.next = oldToCopy.get(cur.next);
            copy.random = oldToCopy.get(cur.random);
            cur = cur.next;
        }

        return oldToCopy.get(head);
    }
}
