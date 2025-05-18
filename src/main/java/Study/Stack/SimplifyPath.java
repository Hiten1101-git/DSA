package Study.Stack;

import java.util.Stack;

/**
 * You are given an absolute path for a Unix-style file system, which always begins with a slash '/'.
 * Your task is to transform this absolute path into its simplified canonical path.
 *.
 * The rules of a Unix-style file system are as follows:
 *.
 * A single period '.' represents the current directory.
 * A double period '..' represents the previous/parent directory.
 * Multiple consecutive slashes such as '//' and '///' are treated as a single slash '/'.
 * Any sequence of periods that does not match the rules above should be treated as a valid directory or file name.
 * For example, '...' and '....' are valid directory or file names.
 * The simplified canonical path should follow these rules:
 *.
 * The path must start with a single slash '/'.
 * Directories within the path must be separated by exactly one slash '/'.
 * The path must not end with a slash '/', unless it is the root directory.
 * The path must not have any single or double periods ('.' and '..') used to denote current or parent directories.
 * Return the simplified canonical path.
 */
public class SimplifyPath {
    public static void main(String[] args) {
        String path = "/home/user/Documents/../Pictures/./";
        SimplifyPath sp = new SimplifyPath();
        System.out.println("Input: " + path);
        System.out.println("Output: " + sp.simplifyPath(path));
    }

    /**
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    private String simplifyPath(String path) {
        String[] parts = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String part : parts) {
            if (part.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else if (!part.isEmpty() && !part.equals(".")) {
                stack.push(part);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (String str : stack) {
            sb.append("/").append(str);
        }

        return sb.length() == 0 ? "/" : sb.toString();
    }
}
