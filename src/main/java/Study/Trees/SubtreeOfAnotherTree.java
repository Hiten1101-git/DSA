package Study.Trees;

import Study.Helper.TreeNode;

/**
 * Given the roots of two binary trees root and subRoot, return true if there is a subtree of root with the same
 * structure and node values of subRoot and false otherwise.
 *.
 * A subtree of a binary tree is a tree that consists of a node in tree and all of this node's descendants.
 * The tree could also be considered as a subtree of itself.
 * .
 * The problem is similar to Same Tree problem.
 */
public class SubtreeOfAnotherTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(4);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(0);

        TreeNode subRoot = new TreeNode(4);
        subRoot.left = new TreeNode(1);
        subRoot.right = new TreeNode(2);

        SubtreeOfAnotherTree st = new SubtreeOfAnotherTree();
        System.out.println(st.isSubtree(root, subRoot)); // Output: true
    }

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        if (root == null) return false;
        if (isSame(root, subRoot)) return true;
        return isSubtree(root.left, subRoot) || isSubtree(root.right, subRoot);
    }

    private Boolean isSame(TreeNode first, TreeNode second) {
        if (first == null && second == null) return true;

        if (first == null || second == null || first.val != second.val) return false;

        return isSame(first.left, second.left) && isSame(first.right, second.right);
    }

}
