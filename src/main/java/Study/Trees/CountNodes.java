package Study.Trees;

import Study.Helper;
import Study.Helper.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class CountNodes {
    public static void main(String[] args) {
        Helper helper = new Helper();
        CountNodes obj = new CountNodes();
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), null));
        helper.printTree(root, "Original Tree");
        System.out.println("Count of nodes: " + obj.countNodes(root));
    }

    public int countNodes(TreeNode root) {
        return bfs(root);
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;

        int left = dfs(root.left);
        int right = dfs(root.right);

        return 1 + left + right;
    }

    private int bfs(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        int ans = 0;

        while (!q.isEmpty()) {
            TreeNode curr = q.poll();
            ans++;
            if (curr.left != null) q.offer(curr.left);
            if (curr.right != null) q.offer(curr.right);
        }

        return ans;
    }
}
