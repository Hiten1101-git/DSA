package Study.Graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Course Schedule
 * You are given an array prerequisites where prerequisites[i] = [a, b] indicates that you must take course b
 * first if you want to take course a.
 * The pair [0, 1], indicates that must take course 1 before taking course 0.
 * There are a total of numCourses courses you are required to take, labeled from 0 to numCourses - 1.
 * Return true if it is possible to finish all courses, otherwise return false.
 */
public class CourseSchedule {
    public static void main(String[] args) {
        Solution obj = new Solution();
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        System.out.println("Can finish courses: " + obj.canFinish(numCourses, prerequisites));
    }

    static class Solution {
        public boolean canFinish(int numCourses, int[][] prerequisites) {
            // Step 1: create a adj list and in-degree array
            List<List<Integer>> adj = new ArrayList<>();
            int[] inDegree = new int[numCourses];

            for (int i = 0; i < numCourses; i++) {
                adj.add(new ArrayList<>());
            }

            for (int[] pair : prerequisites) {
                int course = pair[0];
                int pre = pair[1];
                adj.get(pre).add(course);
                inDegree[course]++;
            }

            // Step 2: initialize the queue with courses having 0 in-degree
            Queue<Integer> q = new LinkedList<>();
            for (int course = 0 ; course < numCourses ; course++) {
                if (inDegree[course] == 0) q.offer(course);
            }

            // Step 3: iterate through the queue to count how many courses we have processed
            int count = 0;
            while (!q.isEmpty()) {
                int currCourse = q.poll();
                count++;

                for (int neighbor : adj.get(currCourse)) {
                    inDegree[neighbor]--;
                    if (inDegree[neighbor] == 0) q.offer(neighbor);
                }
            }

            // Step 4: verify if the count equals number of courses
            return count == numCourses;
        }
    }

}
