package Study.Trees;

import Study.Helper;
import Study.Helper.TreeNode;

import java.util.HashMap;

/**
 * Given two integer arrays inorder and postorder where inorder is the inorder traversal of a binary tree and
 * postorder is the postorder traversal of the same tree, construct and return the binary tree.
 */
public class ConstructTreeFromInorderPostorder {
    public static void main(String[] args) {
        Helper helper = new Helper();
        ConstructTreeFromInorderPostorder obj = new ConstructTreeFromInorderPostorder();
        int[] inorder = {9, 3, 15, 20, 7};
        int[] postorder = {9, 15, 7, 20, 3};
        TreeNode root = obj.buildTree(inorder, postorder);
        helper.printTree(root, "Constructed Tree");
    }

    private final HashMap<Integer, Integer> inorderMap = new HashMap<>();
    private int postorderIndex;

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postorderIndex = postorder.length - 1;

        for (int i = 0 ; i < inorder.length ; i++) {
            inorderMap.put(inorder[i], i);
        }

        return buildTree(postorder, 0, postorder.length - 1);
    }

    private TreeNode buildTree(int[] postorder, int left, int right) {
        if (left > right || postorderIndex < 0) return null;

        int rootVal = postorder[postorderIndex--];
        TreeNode root = new TreeNode(rootVal);
        int inorderIdx = inorderMap.get(rootVal);

        root.right = buildTree(postorder, inorderIdx + 1, right);
        root.left = buildTree(postorder, left, inorderIdx - 1);

        return root;
    }
}
