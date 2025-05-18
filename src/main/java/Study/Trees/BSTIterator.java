package Study.Trees;

import Study.Helper.TreeNode;

import java.util.Stack;

public class BSTIterator {
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(7);
        root.left = new TreeNode(3);
        root.right = new TreeNode(15);
        root.right.left = new TreeNode(9);
        root.right.right = new TreeNode(20);

        BSTIterator iterator = new BSTIterator(root);
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    private final Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        pushLeftBranch(root);
    }

    public int next() {
        TreeNode node = stack.pop();
        if (node.right != null) {
            pushLeftBranch(node.right);
        }
        return node.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public void pushLeftBranch(TreeNode node){
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
    }
}
