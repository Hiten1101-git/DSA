package Study.LinkedList;

import java.util.HashMap;

/**
 * Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
 *.
 * Implement the LRUCache class:
 *.
 * LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 * int get(int key) Return the value of the key if the key exists, otherwise return -1.
 * void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to
 * the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 * The functions get and put must each run in O(1) average time complexity.
 */
public class LRUCache {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1)); // returns 1
        lruCache.put(3, 3); // evicts key 2
        System.out.println(lruCache.get(2)); // returns -1 (not found)
        lruCache.put(4, 4); // evicts key 1
        System.out.println(lruCache.get(1)); // returns -1 (not found)
        System.out.println(lruCache.get(3)); // returns 3
        System.out.println(lruCache.get(4)); // returns 4
    }

    public static class Node {
        int key;
        int val;
        Node prev;
        Node next;

        Node (int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    Node head = new Node(-1, -1);
    Node tail = new Node(-1, -1);
    int cap;
    HashMap<Integer, Node> m = new HashMap<>();

    public LRUCache(int capacity) {
        cap = capacity;
        head.next = tail;
        tail.prev = head;
    }

    private void addNode(Node newNode) {
        Node temp = head.next;

        newNode.next = temp;
        newNode.prev = head;

        head.next = newNode;
        temp.prev = newNode;
    }

    private void deleteNode(Node delNode) {
        Node prevNode = delNode.prev;
        Node nextNode = delNode.next;

        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    public int get(int key) {
        if (m.containsKey(key)) {
            Node resNode = m.get(key);
            int ans = resNode.val;

            // update the node as recently used
            m.remove(key);
            deleteNode(resNode);
            addNode(resNode);
            m.put(key, head.next);

            return ans;
        }

        return -1;
    }

    public void put(int key, int value) {
        if (m.containsKey(key)) {
            // update the node as recently used
            Node curr = m.get(key);
            m.remove(key);
            deleteNode(curr);
        }

        if (m.size() == cap) {
            // remove the LRU node if capacity reached
            m.remove(tail.prev.key);
            deleteNode(tail.prev);
        }

        addNode(new Node(key, value));
        m.put(key, head.next);
    }
}
