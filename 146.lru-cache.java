import java.util.HashMap;
import java.util.Map;

/*
 * @lc app=leetcode id=146 lang=java
 *
 * [146] LRU Cache
 *
 * https://leetcode.com/problems/lru-cache/description/
 *
 * algorithms
 * Medium (29.78%)
 * Likes:    4599
 * Dislikes: 200
 * Total Accepted:    436.5K
 * Total Submissions: 1.5M
 * Testcase Example:  '["LRUCache","put","put","get","put","get","put","get","get","get"]\n[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]'
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache.
 * It should support the following operations: get and put.
 * 
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present.
 * When the cache reached its capacity, it should invalidate the least recently
 * used item before inserting a new item.
 * 
 * The cache is initialized with a positive capacity.
 * 
 * Follow up:
 * Could you do both operations in O(1) time complexity?
 * 
 * Example:
 * 
 * 
 * LRUCache cache = new LRUCache( 2 capacity );
 * 
 * cache.put(1, 1);
 * cache.put(2, 2);
 * cache.get(1);       // returns 1
 * cache.put(3, 3);    // evicts key 2
 * cache.get(2);       // returns -1 (not found)
 * cache.put(4, 4);    // evicts key 1
 * cache.get(1);       // returns -1 (not found)
 * cache.get(3);       // returns 3
 * cache.get(4);       // returns 4
 * 
 * 
 * 
 * 
 */

// @lc code=start
// Time: O(1) - Search: O(1), Add: O(1), Delete: O(1), Update: O(1)
// Space: O(n)
// Double Linked List
class LRUCache {

    static class Node {
        private int key;
        private int value;
        Node pre, next;
        public Node (int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private int capacity;
    // Use HashMap to store `key -> Node (key->value)`
    private Map<Integer, Node> map;
    // dummyhead is before real "head", and dummytail is after real "tail"
    private Node dummyhead, dummytail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.dummyhead = new Node(-1, -1);
        this.dummytail = new Node(-1, -1);
        this.dummyhead.next = this.dummytail;
        this.dummytail.pre = this.dummyhead;
    }
    
    public int get(int key) {
        Node node = getNode(key);
        if (node == null) {
            return -1;
        }
        return node.value;
    }
    
    public void put(int key, int value) {
        Node node = getNode(key);
        if (node != null) {
            node.value = value;
        } else { // if the node does not exist, then add a new node
            node = new Node(key, value);
            insertHead(node);

            map.put(key, node);
            if (map.size() > capacity) {
                Node tail = dummytail.pre;
                disconnect(tail); // in this scenario, `disconnect()` makes connection between new "tail" and dummytail.
                map.remove(tail.key);
            }
        }
    }

    private Node getNode(int key) {
        Node node = map.get(key);
        if (node == null) {
            return null;
        }
        disconnect(node);
        insertHead(node);
        return node;
    }

    // disconnect part of relationship between node and pre+next
    // connect double directions for pre and next
    private void disconnect(Node node) {
        node.next.pre = node.pre;
        node.pre.next = node.next;
    }

    private void insertHead(Node node) {
        node.next = dummyhead.next;
        dummyhead.next.pre = node;
        node.pre = dummyhead;
        dummyhead.next = node;
    }

    // static class Node {
    //     private int key;
    //     private int val;
    //     Node pre, next;
    //     public Node (int key, int val) {
    //         this.key = key;
    //         this.val = val;
    //     }
    // }

    // private int capacity;
    // private Map<Integer, Node> map;
    // private Node dummyhead, dummytail;

    // public LRUCache(int capacity) {
    //     this.capacity = capacity;
    //     this.map = new HashMap<>();
    //     this.dummyhead = new Node(-1, -1);
    //     this.dummytail = new Node(-1, -1);
    //     this.dummyhead.next = this.dummytail;
    //     this.dummytail.pre = this.dummyhead;
    // }
    
    // public int get(int key) {
    //     Node node = getNode(key);
    //     if (node == null) return -1;
    //     return node.val;
    // }

    // private Node getNode(int key) {
    //     Node node = map.get(key);
    //     if (node == null) return null;
    //     disconnect(node);
    //     insertHead(node);
    //     return node;
    // }

    // private void disconnect(Node node) {
    //     node.next.pre = node.pre;
    //     node.pre.next = node.next;
    // }

    // private void insertHead(Node node) {
    //     node.next = dummyhead.next;
    //     dummyhead.next.pre = node;
    //     node.pre = dummyhead;
    //     dummyhead.next = node;
    // }
    
    // public void put(int key, int value) {
    //     Node node = getNode(key);   
    //     if (node != null) node.val = value;
    //     else {
    //         node = new Node(key, value);
    //         insertHead(node);

    //         map.put(key, node);
    //         if (map.size() > capacity) {
    //             Node tail = dummytail.pre;
    //             disconnect(tail);
    //             map.remove(tail.key);
    //         }
    //     }
    // }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
// @lc code=end

