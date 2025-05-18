package Study.Trees;

import Study.Helper;
import Study.Helper.TreeNode;
import javafx.util.Pair;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Maximum Depth of Binary Tree
 * Given the root of a binary tree, return its depth.
 *.
 * The depth of a binary tree is defined as the number of nodes along the longest path from the root node
 * down to the farthest leaf node.
 * .
 * Example 1:
 * Input: root = [1,2,3,null,null,4]
 * Output: 3
 */
public class DepthOfBinaryTree {
    public static void main(String[] args) {
        Helper helper = new Helper();
        DepthOfBinaryTree obj = new DepthOfBinaryTree();
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), null));
        helper.printTree(root, "Original Tree");
        System.out.println("Max depth of binary tree: " + obj.iterativeDFS(root));
    }

    /**
         BFS solution.
         level order - Traverse level by level.

         “I’ll use a BFS strategy. For each node, I’ll add its children to the
         queue. When I finish processing all nodes at the current depth, I’ll
         increment the depth counter.”

         * Time: O(n) — visit every node once
         * Space: O(n) — queue can grow to n/2 in the worst case
     */
    private int bfs(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int depth = 0;

        while (!q.isEmpty()) {
            for (int i = q.size() ; i > 0 ; i--) {
                TreeNode node = q.poll();
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }
            depth++;
        }

        return depth;
    }

    /**
         Iterative DFS solution.
         pre-order - Traverse parent first and then children.

         “I’ll use a DFS strategy. For each node, I’ll iteratively compute
         the depth of its left and right children, and return 1 plus the
         maximum of those. The base case is when the node is null — in that
         case, depth is 0.”

         * Time: O(n) — visit every node once
         * Space: O(h) — height of the tree (due to stack),
         * worst case O(n)
     */
    private int iterativeDFS(TreeNode root) {
        if (root == null) return 0;

        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, 1));
        int maxDepth = 0;

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> curr = stack.pop();
            TreeNode node = curr.getKey();
            int depth = curr.getValue();
            maxDepth = Math.max(depth, maxDepth);

            if (node.left != null) stack.push(new Pair<>(node.left, depth + 1));
            if (node.right != null) stack.push(new Pair<>(node.right, depth + 1));
        }

        return maxDepth;
    }

    /**
         Recursive DFS solution.
         post order - Traverse children first and then return.

         “I’ll use a DFS strategy. For each node, I’ll recursively compute
         the depth of its left and right children, and return 1 plus the
         maximum of those. The base case is when the node is null — in that
         case, depth is 0.”

         Time: O(n) — visit every node once
         Space: O(h) — height of the tree (due to recursion stack),
         worst case O(n)
     */
    private int recursiveDFS(TreeNode root) {
        if (root == null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);

        return 1 + Math.max(left, right);
    }

    private int dfs(TreeNode root) {
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        // root element is added initially with height 1
        stack.push(new Pair<>(root, 1));
        int res = 0;

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.pop();
            TreeNode node = current.getKey();
            int depth = current.getValue();

            if (node != null) {
                res = Math.max(res, depth);
                stack.push(new Pair<>(node.left, depth + 1));
                stack.push(new Pair<>(node.right, depth + 1));
            }
        }

        return res;
    }
}
