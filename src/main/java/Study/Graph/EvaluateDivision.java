package Study.Graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Evaluate Division
 * You are given an array of variable pairs equations and an array of real numbers values, where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i]. Each Ai or Bi is a string that represents a single variable.
 * You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query where you must find the answer for Cj / Dj = ?.
 * Return the answers to all queries. If a single answer cannot be determined, return -1.0.
 * Note: The input is always valid. You may assume that evaluating the queries will not result in division by zero and that there is no contradiction.
 * Note: The variables that do not occur in the list of equations are undefined, so the answer cannot be determined for them.
 */
public class EvaluateDivision {
    public static void main(String[] args) {
        // Example usage
        List<List<String>> equations = List.of(
                List.of("a", "b"),
                List.of("b", "c")
        );
        double[] values = {2.0, 3.0};
        List<List<String>> queries = List.of(
                List.of("a", "c"),
                List.of("b", "a"),
                List.of("a", "e"),
                List.of("a", "a"),
                List.of("x", "x")
        );

        Solution solution = new Solution();
        double[] results = solution.calcEquation(equations, values, queries);
        for (double result : results) {
            System.out.println(result);
        }
    }

    static class Solution {
        public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
            HashMap<String, HashMap<String, Double>> gr =  buildGraph(equations, values);
            double[] finalAns = new double[queries.size()];

            for (int i = 0 ; i < queries.size() ; i++) {
                String dividend = queries.get(i).get(0);
                String divisor = queries.get(i).get(1);

                if (!gr.containsKey(dividend) || !gr.containsKey(divisor)) {
                    finalAns[i] = -1.0;
                } else {
                    HashSet<String> vis = new HashSet<>();
                    double[] ans = {-1.0};
                    double temp = 1.0;
                    dfs(dividend, divisor, gr, vis, ans, temp);
                    finalAns[i] = ans[0];
                }
            }

            return finalAns;
        }

        private HashMap<String, HashMap<String, Double>> buildGraph(List<List<String>> eq, double[] values) {
            HashMap<String, HashMap<String, Double>> graph = new HashMap<>();

            for (int i = 0 ; i < eq.size() ; i++) {
                String dividend = eq.get(i).get(0);
                String divisor = eq.get(i).get(1);
                double value = values[i];

                graph.putIfAbsent(dividend, new HashMap<>());
                graph.putIfAbsent(divisor, new HashMap<>());

                graph.get(dividend).put(divisor, value);
                graph.get(divisor).put(dividend, 1.0 / value);
            }

            return graph;
        }

        private void dfs(String node, String dest, HashMap<String, HashMap<String, Double>> gr, HashSet<String> vis, double[] ans, double temp) {
            if (vis.contains(node)) return;

            vis.add(node);
            if (node.equals(dest)) {
                ans[0] = temp;
                return;
            }

            for (Map.Entry<String, Double> entry : gr.get(node).entrySet()) {
                String ne = entry.getKey();
                double val = entry.getValue();
                dfs(ne, dest, gr, vis, ans, temp * val);
            }
        }
    }
}
