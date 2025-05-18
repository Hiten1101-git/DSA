package Study.Trees;

import Study.Helper.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AverageOfLevelInBinaryTree {
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);

        AverageOfLevelInBinaryTree solution = new AverageOfLevelInBinaryTree();
        List<Double> result = solution.averageOfLevels(root);
        System.out.println("Average of levels: " + result); // Output: [3.0, 14.5, 11.0]
    }

    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            double sum = 0;
            int n = queue.size();
            for (int i = 0 ; i < n ; i++) {
                TreeNode node = queue.poll();
                sum += node.val;
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }

            result.add(sum / n);
        }

        return result;
    }
}
