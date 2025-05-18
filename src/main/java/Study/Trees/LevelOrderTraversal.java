package Study.Trees;

import Study.Helper.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderTraversal {
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        LevelOrderTraversal solution = new LevelOrderTraversal();
        List<List<Integer>> result = solution.levelOrder(root);
        System.out.println("Level order traversal: " + result); // Output: [[1], [2, 3], [4, 5]]
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            int n = q.size();

            for (int i = 0 ; i < n ; i++) {
                TreeNode node = q.poll();
                levelList.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            result.add(levelList);
        }

        return result;
    }
}
