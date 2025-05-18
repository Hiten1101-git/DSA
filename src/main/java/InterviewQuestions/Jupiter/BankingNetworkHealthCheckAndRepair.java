package InterviewQuestions.Jupiter;

import java.util.*;

public class BankingNetworkHealthCheckAndRepair {
    /**
     * Task:
     * Write a function bankingNetworkHealthCheckAndRepair that determines if the network is still fully connected.
     * If not, finds the minimum number of channels required to reconnect the entire network.
     * .
     * Input: 1, 5, -1, 4, 5, -1, 3, 4, -1, 4, 5, -1
     * integerArray: an array of integers, The provided 1D array of integers represents a network of banks.
     * Each segment of the array, separated by the delimiter -1, denotes a bank with its unique identifier followed
     * by its neighbouring banks. For instance, the segment 1, 5, -1 indicates that bank 1 has only one neighbour, which is bank 5.
     * Similarly, bank 4 has neighbour 5 only.
     *.
     * Output: 2:1
     * Return a string in the format "a:b", where:
     * 'a' is the number of isolated groups of banks. If the network is fully connected, 'a' is 0.
     * 'b' is the minimum number of channels required to reconnect the entire network. If network is fully connected then b is 0
     */
    public static void main(String[] args) {
        int[] input1 = {1, 5, -1, 2, -1, 5, -1, 3, 4, -1, 4, 5, -1};  // Two groups
        int[] input2 = {1, 2, -1, 2, 3, -1, 3, 1, -1};             // Fully connected
        int[] input3 = {1, 5, -1, 2, 3, -1, 5, 3, -1, 4, 1, 6};  // Two groups

        System.out.println(bankingNetworkHealthCheckAndRepair(input1)); // Output: 2:1
        System.out.println(bankingNetworkHealthCheckAndRepair(input2)); // Output: 0:0
        System.out.println(bankingNetworkHealthCheckAndRepair(input3));
    }

    /**
     * Function to check the health of the banking network and repair it if necessary.
     * graph will contain all the neighbours in the form of a map.
     * @param strArr
     * @return
     */
    public static String bankingNetworkHealthCheckAndRepair(int[] strArr) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Set<Integer> allBanks = new HashSet<>();

        // Build graph
        int i = 0;
        while (i < strArr.length) {
            if (strArr[i] == -1) {
                i++;
                continue;
            }

            int bank = strArr[i++];
            allBanks.add(bank);
            graph.putIfAbsent(bank, new ArrayList<>());

            while (i < strArr.length && strArr[i] != -1) {
                int neighbor = strArr[i++];
                allBanks.add(neighbor);
                graph.putIfAbsent(neighbor, new ArrayList<>());

                // Since undirected, add both directions
                graph.get(bank).add(neighbor);
                graph.get(neighbor).add(bank);
            }
        }

        // DFS to find connected components
        Set<Integer> visited = new HashSet<>();
        int components = 0;

        for (int bank : allBanks) {
            if (!visited.contains(bank)) {
                components++;
                dfsRecursive(bank, graph, visited);
            }
        }

        int repairs = Math.max(0, components - 1);
        return components == 1 ? "0:0" : components + ":" + repairs;
    }

    private static void dfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(node);
        while (!stack.isEmpty()) {
            int current = stack.pop();
            if (!visited.contains(current)) {
                visited.add(current);
                for (int neighbor : graph.getOrDefault(current, Collections.emptyList())) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
    }

    private static void dfsRecursive(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        visited.add(node);
        for (int neighbor : graph.get(node)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, graph, visited);
            }
        }
    }
}
