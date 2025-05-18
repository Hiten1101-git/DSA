package Study.Trees;

import Study.Helper.TreeNode;

import java.util.*;

public class ZigZagLevelOrderTraversal {
    public static void main(String[] args) {
        // Example usage
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        ZigZagLevelOrderTraversal solution = new ZigZagLevelOrderTraversal();
        List<List<Integer>> result = solution.zigzagLevelOrder(root);
        System.out.println("Zigzag level order traversal: " + result); // Output: [[1], [3, 2], [4, 5]]
    }

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        return approach1(root);
    }

    private List<List<Integer>> approach1(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean reverseFlag = false;

        while (!q.isEmpty()) {
            List<Integer> levelList = new ArrayList<>();
            int n = q.size();

            for (int i = 0 ; i < n ; i++) {
                TreeNode node = q.poll();
                levelList.add(node.val);
                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            if (reverseFlag) {
                Collections.reverse(levelList);
            }

            result.add(levelList);
            reverseFlag = !reverseFlag;
        }

        return result;
    }

    private List<List<Integer>> approach2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        boolean reverseFlag = false;

        while (!q.isEmpty()) {
            Deque<Integer> levelList = new LinkedList<>();
            int n = q.size();

            for (int i = 0 ; i < n ; i++) {
                TreeNode node = q.poll();

                if (reverseFlag) {
                    levelList.addFirst(node.val);
                } else {
                    levelList.addLast(node.val);
                }

                if (node.left != null) q.offer(node.left);
                if (node.right != null) q.offer(node.right);
            }

            result.add(new ArrayList<>(levelList));
            reverseFlag = !reverseFlag;
        }

        return result;
    }
}
