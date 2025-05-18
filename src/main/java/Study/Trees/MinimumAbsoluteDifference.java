package Study.Trees;

import Study.Helper.TreeNode;

public class MinimumAbsoluteDifference {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);

        MinimumAbsoluteDifference mad = new MinimumAbsoluteDifference();
        System.out.println(mad.getMinimumDifference(root)); // Output: 1
    }

    int min = Integer.MAX_VALUE;
    Integer prev = null;

    public int getMinimumDifference(TreeNode root) {
        if (root == null) return min;

        getMinimumDifference(root.left);

        if (prev != null) min = Math.min(min, root.val - prev);
        prev = root.val;

        getMinimumDifference(root.right);

        return min;
    }
}
