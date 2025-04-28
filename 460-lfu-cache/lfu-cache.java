import java.util.*;

class LFUCache {
    // Doubly‐linked list node
    class Node {
        int key, value, freq;
        Node prev, next;
        Node(int k, int v) {
            key = k;
            value = v;
            freq = 1;           // on creation, frequency = 1
        }
    }

    // Doubly‐linked list to hold nodes of the same frequency,
    // with quick add to head / remove arbitrary / remove tail
    class DLinkedList {
        Node head, tail;
        int size;
        DLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
            size = 0;
        }
        void addToHead(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }
        void removeNode(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }
        Node removeTail() {
            if (size == 0) return null;
            Node node = tail.prev;
            removeNode(node);
            return node;
        }
    }

    private final int capacity;
    private int minFreq;
    private final Map<Integer, Node> keyNode;            // key → node
    private final Map<Integer, DLinkedList> freqMap;     // freq → DLL of nodes

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.minFreq = 0;
        this.keyNode  = new HashMap<>();
        this.freqMap  = new HashMap<>();
    }

    public int get(int key) {
        if (!keyNode.containsKey(key)) return -1;
        Node node = keyNode.get(key);
        // increment its frequency
        updateFrequency(node);
        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyNode.containsKey(key)) {
            // update existing
            Node node = keyNode.get(key);
            node.value = value;
            updateFrequency(node);
        } else {
            // if at capacity, evict least‐freq & least‐recent
            if (keyNode.size() == capacity) {
                DLinkedList minList = freqMap.get(minFreq);
                Node toRemove = minList.removeTail();
                keyNode.remove(toRemove.key);
            }
            // insert new node
            Node newNode = new Node(key, value);
            keyNode.put(key, newNode);
            // add to freq = 1 list
            freqMap.computeIfAbsent(1, k->new DLinkedList()).addToHead(newNode);
            minFreq = 1;
        }
    }

    // helper to bump a node's frequency
    private void updateFrequency(Node node) {
        int f = node.freq;
        DLinkedList oldList = freqMap.get(f);
        oldList.removeNode(node);
        // if this was the only node at old minFreq, bump minFreq
        if (f == minFreq && oldList.size == 0) {
            minFreq++;
        }
        node.freq++;
        // add to new frequency list
        freqMap.computeIfAbsent(node.freq, k->new DLinkedList()).addToHead(node);
    }
}
