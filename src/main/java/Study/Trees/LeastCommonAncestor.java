package Study.Trees;

import Study.Helper.TreeNode;

/**
 * Given a binary search tree (BST) where all node values are unique, and two nodes from the tree p and q,
 * return the lowest common ancestor (LCA) of the two nodes.
 *.
 * The lowest common ancestor between two nodes p and q is the lowest node in a tree T such that both p and q as
 * descendants. The ancestor is allowed to be a descendant of itself.
 */
public class LeastCommonAncestor {
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(5);
        root.right = new TreeNode(1);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        LeastCommonAncestor solution = new LeastCommonAncestor();
        TreeNode p = root.left; // GraphNode with value 5
        TreeNode q = root.right; // GraphNode with value 1
        TreeNode lca = solution.lowestCommonAncestor(root, p, q);
        System.out.println("Lowest Common Ancestor: " + (lca != null ? lca.val : "null")); // Output: 3
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return iterative(root, p, q);
    }

    /**
     In BST, all the nodes in left subtree of a node are less than the node's value
     and the values of all nodes in the right subtree are greater than the node's value.

     Time : O(h)
     Space : O(h)
     */
    private TreeNode recursive(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return null;

        // if greater value is less than root, find in left subtree
        if (Math.max(p.val, q.val) < root.val) return recursive(root.left, p, q);
            // if smaller value is greater than root, find in right subtree
        else if (Math.min(p.val, q.val) > root.val) return recursive(root.right, p, q);
            // else, it means one value is root, hence return root
        else return root;
    }

    /**
     In BST, all the nodes in left subtree of a node are less than the node's value
     and the values of all nodes in the right subtree are greater than the node's value.

     Time : O(h)
     Space : O(1)
     */
    private TreeNode iterative(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode curr = root;

        while (curr != null) {
            // if greater value is less than root, find in left subtree
            if (Math.max(p.val, q.val) < curr.val) curr = curr.left;
                // if smaller value is greater than root, find in right subtree
            else if (Math.min(p.val, q.val) > curr.val) curr = curr.right;
                // else, it means one value is root, hence return root
            else return curr;
        }
        return null;
    }
}
