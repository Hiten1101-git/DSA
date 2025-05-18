package Study.Trees;

import Study.Helper.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BinaryTreeRightSideView {
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(4);

        BinaryTreeRightSideView solution = new BinaryTreeRightSideView();
        List<Integer> result = solution.rightSideView(root);
        System.out.println("Right side view: " + result); // Output: [1, 3, 4]
    }

    Map<Integer, Integer> levelMap = new HashMap<>();

    /**
     Time: O(n)
     Space: O(n) in the worst case
     */
    public List<Integer> rightSideView(TreeNode root) {
        dfs(root, 0);
        List<Integer> list = new ArrayList();
        for (int level : levelMap.keySet()) {
            list.add(levelMap.get(level));
        }
        return list;
    }

    private void dfs(TreeNode node, int level) {
        if (node == null) return;

        if (!levelMap.containsKey(level)) levelMap.put(level, node.val);

        dfs(node.right, level + 1);
        dfs(node.left, level + 1);
    }
}
