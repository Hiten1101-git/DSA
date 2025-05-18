package Study.Trees;

/**
 * Given a binary tree
 *.
 * struct GraphNode {
 *   int val;
 *   GraphNode *left;
 *   GraphNode *right;
 *   GraphNode *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node,
 * the next pointer should be set to NULL.
 *.
 * Initially, all next pointers are set to NULL.
 */
public class PopulateNextInTree {
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public static void main(String[] args) {
        // Example usage
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.right = new Node(6);

        PopulateNextInTree solution = new PopulateNextInTree();
        solution.connect(root);

        // Print the next pointers
        System.out.println(root.left.next.val); // Should print 3
    }

    /**
     •	Time Complexity: O(n)   — Every node is visited once.
     •	Space Complexity: O(1)  — No additional data structures used;
                                  we only use pointers.
     */
    public Node connect(Node root) {
        if (root == null) return null;

        Node current = root; // Start with the root node
        while (current != null) {
            Node dummy = new Node(0); // Dummy node for the next level
            Node tail = dummy; // Tail helps connect children

            // Traverse the current level
            while (current != null) {
                if (current.left != null) {
                    tail.next = current.left;
                    tail = tail.next;
                }
                if (current.right != null) {
                    tail.next = current.right;
                    tail = tail.next;
                }
                current = current.next; // Move to the next node in the current level
            }
            current = dummy.next; // Move to the next level
        }

        return root;
    }
}
