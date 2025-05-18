package Study.Trees;

import Study.Helper.TreeNode;

/**
 * Given the root of a binary tree, flatten the tree into a "linked list":
 *.
 * The "linked list" should use the same TreeNode class where the right child pointer points to the next node in
 * the list and the left child pointer is always null.
 * The "linked list" should be in the same order as a pre-order traversal of the binary tree.
 */
public class FlattenTreeToLL {
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(6);

        FlattenTreeToLL flattener = new FlattenTreeToLL();
        flattener.flatten(root);

        // Print the flattened tree
        TreeNode current = root;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.right;
        }
    }

    public void flatten(TreeNode root) {
        preorder(root);
    }

    private void preorder(TreeNode root) {
        if (root == null) return;

        preorder(root.left);
        preorder(root.right);

        TreeNode leftNode = root.left;
        TreeNode rightNode = root.right;
        root.left = null;
        root.right = leftNode;

        TreeNode curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        curr.right = rightNode;
    }
}
