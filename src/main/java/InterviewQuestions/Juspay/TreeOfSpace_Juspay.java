package InterviewQuestions.Juspay;

import java.util.*;

public class TreeOfSpace_Juspay {
    public static void main(String[] args) {
        /*   Let us create below tree
         *             1
         *         / /  \  \
         *       2  3   4    5
         *      / \     |  / |  \
         *     6  7     8  9 10 11
         *       /\           \
         *     12  13          14
         */
        NaryTree Tree = new NaryTree(1);
        Tree.addNode(1, 2);
        Tree.addNode(1, 3);
        Tree.addNode(1, 4);
        Tree.addNode(1, 5);
        Tree.addNode(2, 6);
        Tree.addNode(2, 7);
        Tree.addNode(4, 8);
        Tree.addNode(5, 9);
        Tree.addNode(5, 10);
        Tree.addNode(5, 11);
        Tree.addNode(7, 12);
        Tree.addNode(7, 13);
        Tree.addNode(10, 14);

        System.out.println("Tree 4 lock : " + Tree.lock(4, 5));
        System.out.println("Tree 7 lock : " + Tree.lock(7, 6));
        System.out.println("Tree 3 lock :  " + Tree.lock(3, 7));
        System.out.print("Tree 4 ");
        Tree.unlock(4, 5);
        System.out.print("Tree 3 ");
        Tree.unlock(3, 9);
        System.out.println();
        System.out.println("tree 2: ");
        Tree.upgrade(2, 5);
    }
}

class Node {
    // Stores the key of the GraphNode
    public int key;
    // Stores the total count of locked descendants
    public int lockedDescendantsCount;
    // Stores the locking information of the GraphNode
    public boolean isLocked;
    // Stores the children of the GraphNode
    Queue<Node> children = new LinkedList<>();
    // Stores the parent information of the GraphNode
    Node parent;

    public Node(Node parent, int key) {
        this.parent = parent;
        this.key = key;
        lockedDescendantsCount = 0;
        isLocked = false;
    }
}

class NaryTree {
    // Keeps track of all the Nodes in the tree {key, GraphNode*}
    HashMap<Integer, Node> AllNodes = new HashMap<>();
    // to store user information
    HashMap<Integer, Integer> lock = new HashMap<>();
    // root node of the tree
    Node root;

    public NaryTree(int key) {
            root = new Node(null, key);
            AllNodes.put(key, root);
    }

    public void addNode(int parentKey, int key) {
        Node curr = new Node(AllNodes.get(parentKey), key);
        AllNodes.put(key, curr);
        AllNodes.get(parentKey).children.add(curr); // see
    }

    // Utility function to lock a GraphNode
    public boolean lock(int key, int user) {
        // Stores the GraphNode corresponding to the key
        Node curr;
        // If the GraphNode corresponding to the key is not found
        // return or else store that GraphNode
        if(!AllNodes.containsKey(key)) {
            return false;
        } else {
            curr = AllNodes.get(key);
        }
        // If the GraphNode is already locked, return
        if(curr.isLocked) return false;
        // If our tree has descendants, It cannot be locked
        if(curr.lockedDescendantsCount > 0) return false;
        // Traverse the ancestors of the current GraphNode to verify that
        // GraphNode of its ancestors is Locked
        Node temp = curr.parent;
        while (temp != null) {
            if(temp.isLocked) {
                return false;
            }
            temp = temp.parent;
        }

        // Travel its ancestors and increment the count
        // of locked descendants for all its ancestors by 1
        temp = curr.parent;
        while(temp != null) {
            temp.lockedDescendantsCount += 1;
            temp = temp.parent;
        }

        // Lock the current GraphNode
        curr.isLocked = true;
        lock.put(key, user);
        return true;
    }


    // utility function for unlocking
    public void unlock(int key, int user) {
        // Stores the GraphNode corresponding to the key
        Node curr;
        // if user trying to unlock has not locked it, return false;
        if(lock.containsKey(key)) {
            int lockedUser = lock.get(key);
            if(user != lockedUser) {
                System.out.println(" not authorised to do so ");
                return;
            }
        }
        // If the GraphNode corresponding to the key is not found
        // return or else store that GraphNode
        if(!AllNodes.containsKey(key)) {
            System.out.println("is not available, Please try again !");
            return;
        } else {
            curr = AllNodes.get(key);
        }
        // If the GraphNode is already unlocked, return
        if(!curr.isLocked) {
            System.out.println("is unlocked already !");
            return;
        }
        // unlock the current node
        curr.isLocked = false;

        // Travel its ancestors and increment the count
        // of locked descendants for all its ancestors by 1
        Node temp = curr.parent;
        while(temp != null) {
            temp.lockedDescendantsCount -= 1;
            temp = temp.parent;
        }

        System.out.println("successfully unlocked");
    }


    // utility function for upgrading
    public void upgrade(int key, int user) {
        if(lock.containsKey(key)) {
            System.out.println("node is already locked");
            return;
        }

        Node curr = AllNodes.get(key);
        if(curr.lockedDescendantsCount == 0) {
            System.out.println("no descant is locked");
            return;
        }

        Node temp = curr.parent;
        while (temp != null) {
            if(temp.isLocked) {
                System.out.println("ancestors are locked");
                return;
            }
            temp = temp.parent;
        }

        Queue<Node> temp1 = curr.children;
        while(!temp1.isEmpty()) {
            Node temp2 = temp1.peek();
            temp1.poll();
            temp2.isLocked = false;
        }

        System.out.println(" all descendants are unlocked and the node is locked ");

        curr.isLocked = true;
        lock.put(key, user);

    }
}
