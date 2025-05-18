package Study.Trees;

import Study.Helper;
import Study.Helper.TreeNode;

public class SameTree {
    public static void main(String[] args) {
        Helper helper = new Helper();
        SameTree obj = new SameTree();
        TreeNode p = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        TreeNode q = new TreeNode(1, new TreeNode(2), new TreeNode(3));

        helper.printTree(p, "p");
        helper.printTree(q, "q");

        System.out.println("Are both trees same? " + obj.isSameTree(p, q));
    }

    private boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;

        if (p != null && q != null && p.val == q.val) {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }

        return false;
    }
}
