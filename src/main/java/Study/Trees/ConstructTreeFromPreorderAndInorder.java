package Study.Trees;

import Study.Helper;
import Study.Helper.TreeNode;

import java.util.*;

public class ConstructTreeFromPreorderAndInorder {
    public static void main(String[] args) {
        Helper helper = new Helper();
        ConstructTreeFromPreorderAndInorder obj = new ConstructTreeFromPreorderAndInorder();
        int[] preorder = {3, 9, 20, 15, 7};
        int[] inorder = {9, 3, 15, 20, 7};
        System.out.println("Preorder: " + Arrays.toString(preorder));
        System.out.println("Inorder: " + Arrays.toString(inorder));
        TreeNode root = obj.buildTree(preorder, inorder);
        helper.printTree(root, "Constructed Tree");
    }

    private final Map<Integer, Integer> inorderIndexMap = new HashMap<>();
    private int preorderIndex = 0;

    private TreeNode buildTree(int[] preorder, int[] inorder) {
        return smartRecursiveBuild(preorder, inorder);
    }

    // This is a naive approach and not recommended for large trees
    private TreeNode recursiveBuild(int[] preorder, int[] inorder) {
        if (preorder.length == 0 || inorder.length == 0) return null;

        int rootVal = preorder[0];
        TreeNode root = new TreeNode(rootVal);
        int index = -1;

        for (int i = 0 ; i < inorder.length ; i++) {
            if (inorder[i] == rootVal) {
                index = i;
                break;
            }
        }

        // start slicing from index
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, index);
        int[] rightInorder = Arrays.copyOfRange(inorder, index + 1, inorder.length);
        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, index + 1);
        int[] rightPreorder = Arrays.copyOfRange(preorder, index + 1, preorder.length);

        root.left = recursiveBuild(leftPreorder, leftInorder);
        root.right = recursiveBuild(rightPreorder, rightInorder);

        return root;
    }

    // This is a more efficient approach using a map to store inorder indices
    private TreeNode smartRecursiveBuild(int[] preorder, int[] inorder) {
        // fill the map with inorder values and indices
        for (int i = 0 ; i < inorder.length ; i++) {
            inorderIndexMap.put(inorder[i], i);
        }

        return smartRecursiveBuild(preorder, 0, inorder.length - 1);
    }

    private TreeNode smartRecursiveBuild(int[] preorder, int left, int right) {
        if (left > right) return null;

        int rootVal = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootVal);

        int index = inorderIndexMap.get(rootVal);
        root.left = smartRecursiveBuild(preorder, left, index - 1);
        root.right = smartRecursiveBuild(preorder, index + 1, right);

        return root;
    }
}
