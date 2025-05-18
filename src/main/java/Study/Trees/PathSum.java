package Study.Trees;

import Study.Helper;
import Study.Helper.TreeNode;

public class PathSum {
    public static void main(String[] args) {
        Helper helper = new Helper();
        PathSum obj = new PathSum();
        int target = 22;
        TreeNode root = new TreeNode(5, new TreeNode(4), new TreeNode(8, new TreeNode(11), new TreeNode(13)));
        helper.printTree(root, "Original Tree");
        System.out.println("Target sum: " + target);
        System.out.println("Has path sum? " + obj.hasPathSum(root, target));
    }

    private boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) {
            // reached the leaf node
            return targetSum == root.val;
        }

        boolean leftSum = hasPathSum(root.left, targetSum - root.val);
        boolean rightSum = hasPathSum(root.right, targetSum - root.val);

        return leftSum || rightSum;
    }
}
