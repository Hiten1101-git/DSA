package Study.Trees;

import Study.Helper.TreeNode;
import javafx.util.Pair;

/**
 * Given a binary tree, return true if it is height-balanced and false otherwise.
 *.
 * A height-balanced binary tree is defined as a binary tree in which the left and right subtrees of every node
 * differ in height by no more than 1.
 */
public class BalancedBinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        BalancedBinaryTree bbt = new BalancedBinaryTree();
        System.out.println(bbt.isBalanced(root)); // Output: true
    }

    public boolean isBalanced(TreeNode root) {
        return approach2(root);
    }

    private boolean approach1(TreeNode root) {
        return dfs1(root)[0] == 1;
    }

    private int[] dfs1(TreeNode root) {
        if (root == null) return new int[]{1, 0};

        int[] left = dfs1(root.left);
        int[] right = dfs1(root.right);

        boolean balanced = (left[0] == 1 && right[0] == 1)
                && isHeightDifValid(left[1], right[1]);

        int height = 1 + Math.max(left[1], right[1]);

        return new int[]{balanced ? 1 : 0, height};
    }

    /**
     Approach 2 is similar to one, but uses a pair
     */
    private boolean approach2(TreeNode root) {
        return dfs2(root).getKey();
    }

    private Pair<Boolean, Integer> dfs2(TreeNode root) {
        if (root == null) return new Pair<>(true, 0);

        Pair<Boolean, Integer> left = dfs2(root.left);
        Pair<Boolean, Integer> right = dfs2(root.right);

        boolean balanced = left.getKey() && right.getKey()
                && isHeightDifValid(left.getValue(), right.getValue());

        int height = 1 + Math.max(left.getValue(), right.getValue());

        return new Pair<>(balanced, height);
    }

    private boolean isHeightDifValid(int left, int right) {
        return Math.abs(left - right) <= 1;
    }
}
