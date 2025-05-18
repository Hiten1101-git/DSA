package Study.Trees;

import Study.Helper.TreeNode;

/**
 * You are given the root of a binary tree containing digits from 0 to 9 only.
 *.
 * Each root-to-leaf path in the tree represents a number.
 *.
 * For example, the root-to-leaf path 1 -> 2 -> 3 represents the number 123.
 * Return the total sum of all root-to-leaf numbers. Test cases are generated so that the answer will fit in a 32-bit integer.
 *.
 * A leaf node is a node with no children.
 */
public class SumRootToLeaf {
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        SumRootToLeaf solution = new SumRootToLeaf();
        int result = solution.sumNumbers(root);
        System.out.println("Sum of all numbers from root to leaf: " + result); // Output: 262
    }

    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }

    private int dfs(TreeNode node, int pathSum) {
        if (node == null) return 0;

        // Construct a number to be considered for the sum
        pathSum = pathSum * 10 + node.val;

        // if we have reached the leaf node, return the pathSum
        if (node.left == null && node.right == null) return pathSum;

        // recursively check for left and right nodes
        return dfs(node.left, pathSum) + dfs(node.right, pathSum);
    }
}
