package Study.Trees;

import Study.Helper;
import Study.Helper.TreeNode;

public class SymmetricTree {
    public static void main(String[] args) {
        Helper helper = new Helper();
        SymmetricTree obj = new SymmetricTree();
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(2));
        helper.printTree(root, "Original Tree");
        System.out.println("Is tree symmetric? " + obj.isSymmetric(root));
    }

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode n1, TreeNode n2) {
        if (n1 == null && n2 == null) return true;
        if (n1 == null || n2 == null) return false;

        return n1.val == n2.val
                && isSymmetric(n1.left, n2.right)
                && isSymmetric(n1.right, n2.left);
    }
}
