package Study.Trees;

import Study.Helper;
import Study.Helper.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class InvertTree {
    public static void main(String[] args) {
        Helper helper = new Helper();
        InvertTree obj = new InvertTree();
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3, new TreeNode(4), null));
        helper.printTree(root, "Original Tree");
        helper.printTree(obj.invertTree(root), "Inverted Tree");
    }

    public TreeNode invertTree(TreeNode root) {
        return bfs(root);
    }

    private TreeNode dfs(TreeNode root) {
        if (root == null) return null;

        TreeNode node = new TreeNode(root.val);

        node.right = dfs(root.left);
        node.left = dfs(root.right);

        return node;
    }

    private TreeNode bfs(TreeNode root) {
        if (root == null) return root;
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            TreeNode node = q.poll();

            // swap left and right
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if (node.left != null) q.offer(node.left);
            if (node.right != null) q.offer(node.right);
        }

        return root;
    }
}
