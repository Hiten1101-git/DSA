package Study;

import java.util.*;

public class Helper {
    /**
     * ListNode class for LL.
     */
    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode() {}
        public ListNode(int val) { this.val = val; }
        public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * TreeNode class for Trees.
     */
    public static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode() {}
        public TreeNode(int val) { this.val = val; }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * GraphNode class for Graphs.
     */
    public static class GraphNode {
        public int val;
        public List<GraphNode> neighbors;
        public GraphNode() {
            val = 0;
            neighbors = new ArrayList<GraphNode>();
        }
        public GraphNode(int _val) {
            val = _val;
            neighbors = new ArrayList<GraphNode>();
        }
        public GraphNode(int _val, ArrayList<GraphNode> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * Helper function to print a 2D matrix.
     * @param matrix The matrix to print.
     * @param msg A message to display before the matrix.
     */
    public void printMatrix(int[][] matrix, String msg) {
        System.out.println(msg + Arrays.deepToString(matrix));
        for (int[] row : matrix) {
            for (int num : row) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    /**
     * Helper function to print a linked list.
     * @param head head node of LL.
     * @param msg A message to display before the array.
     */
    public void printLL(ListNode head, String msg) {
        System.out.print(msg + ": [ ");
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println("]");
    }

    /**
     * Helper function to print a Tree.
     * @param root root node for the tree.
     * @param msg A message to display before the array.
     */
    public void printTree(TreeNode root, String msg) {
        System.out.print(msg + ": [ ");
        if (root == null) {
            System.out.println("null");
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        boolean hasMoreNodes = true;

        while (!queue.isEmpty() && hasMoreNodes) {
            int levelSize = queue.size();
            hasMoreNodes = false;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();

                if (node == null) {
                    System.out.print("null ");
                    queue.offer(null);
                    queue.offer(null);
                } else {
                    System.out.print(node.val + " ");
                    if (node.left != null || node.right != null) hasMoreNodes = true;
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
        }
        System.out.println("]");
    }

    /**
     * Helper function to print a Graph.
     * @param GraphNode root node for the graph.
     */
    public void printGraph(GraphNode GraphNode) {
        if (GraphNode == null) return;
        System.out.print(GraphNode.val + " -> ");
        for (GraphNode n : GraphNode.neighbors) {
            System.out.print(n.val + " ");
        }
        System.out.println();
        for (GraphNode n : GraphNode.neighbors) {
            printGraph(n);
        }
    }
}
