package Study.Trees;

import Study.Helper;
import Study.Helper.TreeNode;

public class DiameterOfTree {
    public static void main(String[] args) {
        DiameterOfTree obj = new DiameterOfTree();
        Helper helper = new Helper();
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), null));
        helper.printTree(root, "Original Tree");
        System.out.println("Diameter of binary tree: " + obj.diameterOfBinaryTree(root));
    }

    private int diameter = 0;

    /**
     This is a classic post-order DFS:
     Recurse left
     Recurse right
     Do the work

     “I’ll use DFS to compute the height of left and right subtrees at every
     node. The sum of those gives the longest path through that node. I’ll
     track the max across all nodes. My helper returns height for recursion,
     and updates a global max.”

     Time: O(n) — every node visited once
     Space: O(h) — height of tree recursion stack
     */
    public int diameterOfBinaryTree(TreeNode root) {
        dfs(root);
        return diameter;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);

        diameter = Math.max(diameter, left + right);

        return 1 + Math.max(left, right);
    }
}
