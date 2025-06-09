package Study.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class CourseSchedule2 {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int numCourses = 4;
        int[][] prerequisites = {{1, 0}, {2, 1}, {3, 2}};
        int[] order = obj.findOrder(numCourses, prerequisites);
        System.out.println("Course order: " + (order.length > 0 ? java.util.Arrays.toString(order) : "Not possible"));
    }

    static class Solution {
        public int[] findOrder(int numCourses, int[][] prerequisites) {
            List<List<Integer>> adj = new ArrayList<>();
            int[] inDegree = new int[numCourses];

            // Step 1: Build adjacency list and in-degree array
            for (int i = 0; i < numCourses; i++) {
                adj.add(new ArrayList<>());
            }

            for (int[] pair : prerequisites) {
                int course = pair[0];
                int pre = pair[1];
                adj.get(pre).add(course);    // Correct direction: pre -> course
                inDegree[course]++;          // Increase in-degree of the course
            }

            // Step 2: Add all courses with 0 in-degree to the queue
            Queue<Integer> q = new LinkedList<>();
            for (int i = 0; i < numCourses; i++) {
                if (inDegree[i] == 0) q.offer(i);
            }

            // Step 3: Topological sort
            int[] order = new int[numCourses];
            int index = 0;

            while (!q.isEmpty()) {
                int curr = q.poll();
                order[index++] = curr;

                for (int neighbor : adj.get(curr)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) q.offer(neighbor);
                }
            }

            // Step 4: If we processed all courses, return the order; else return empty array
            return index == numCourses ? order : new int[0];
        }
    }

}
