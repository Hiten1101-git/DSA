package Study.Graph;

import Study.Helper.GraphNode;
import Study.Helper;

import java.util.*;

public class CloneGraph {
    public static void main(String[] args) {
        Helper helper = new Helper();
        GraphNode node = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);
        GraphNode node4 = new GraphNode(4);

        node.neighbors.add(node2);
        node.neighbors.add(node3);
        node2.neighbors.add(node4);
        node3.neighbors.add(node4);

        Solution obj = new Solution();
        GraphNode copy = obj.cloneGraph(node);

        System.out.println("Original Graph:");
        helper.printGraph(node);
        System.out.println("Cloned Graph:");
        helper.printGraph(copy);
    }

    static class Solution {
        private GraphNode node;
        Map<GraphNode, GraphNode> map;

        public GraphNode cloneGraph(GraphNode node) {
            this.node = node;
            return dfs();
        }

        private GraphNode bfs() {
            if (node == null) return null;
            if (node.neighbors.isEmpty()) return new GraphNode(node.val);

            map = new HashMap<>();
            Queue<GraphNode> qu = new LinkedList<>();

            map.put(node, new GraphNode(node.val));
            qu.offer(node);

            while (!qu.isEmpty()) {
                GraphNode curr = qu.poll();
                for (GraphNode n : curr.neighbors) {
                    if (!map.containsKey(n)) {
                        map.put(n, new GraphNode(n.val));
                        qu.add(n);
                    }
                    map.get(curr).neighbors.add(map.get(n));
                }
            }

            return map.get(node);
        }

        public GraphNode dfs() {
            map = new HashMap<>();
            return dfs(node);
        }

        public GraphNode dfs(GraphNode node){
            if(node == null) return null;

            if(map.containsKey(node)) {
                return map.get(node);
            }

            GraphNode copy = new GraphNode(node.val);
            map.put(node, copy);

            for(GraphNode n : node.neighbors){
                copy.neighbors.add(dfs(n));
            }
            return copy;
        }
    }
}
