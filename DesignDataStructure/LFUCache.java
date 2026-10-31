package DesignDataStructure;

import java.util.*;

public class LFUCache {

    class Node {
        int key, value, freq;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.freq = 1;
        }
    }

    class DoublyLinkedList {
        Node head, tail;
        int size;

        DoublyLinkedList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        void addFirst(Node node) {
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            size--;
        }

        Node removeLast() {
            if (size == 0) return null;

            Node lru = tail.prev;
            remove(lru);
            return lru;
        }
    }

    HashMap<Integer, Node> keyNodeMap;
    HashMap<Integer, DoublyLinkedList> freqListMap;

    int capacity;
    int minFreq;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        keyNodeMap = new HashMap<>();
        freqListMap = new HashMap<>();
        minFreq = 0;
    }

    private void updateFrequency(Node node) {
        int oldFreq = node.freq;
        DoublyLinkedList oldList = freqListMap.get(oldFreq);

        oldList.remove(node);

        if (oldList.size == 0) {
            freqListMap.remove(oldFreq);

            if (minFreq == oldFreq) {
                minFreq++;
            }
        }

        node.freq++;

        freqListMap
            .computeIfAbsent(node.freq, k -> new DoublyLinkedList())
            .addFirst(node);
    }

    public int get(int key) {
        if (!keyNodeMap.containsKey(key)) {
            return -1;
        }

        Node node = keyNodeMap.get(key);
        updateFrequency(node);

        return node.value;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (keyNodeMap.containsKey(key)) {
            Node node = keyNodeMap.get(key);
            node.value = value;
            updateFrequency(node);
            return;
        }

        if (keyNodeMap.size() == capacity) {
            DoublyLinkedList minList = freqListMap.get(minFreq);
            Node lru = minList.removeLast();

            keyNodeMap.remove(lru.key);

            if (minList.size == 0) {
                freqListMap.remove(minFreq);
            }
        }

        Node newNode = new Node(key, value);
        keyNodeMap.put(key, newNode);

        freqListMap
            .computeIfAbsent(1, k -> new DoublyLinkedList())
            .addFirst(newNode);

        minFreq = 1;
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);

        cache.put(1, 1);
        cache.put(2, 2);
        System.out.println(cache.get(1)); // 1

        cache.put(3, 3);
        System.out.println(cache.get(2)); // -1
        System.out.println(cache.get(3)); // 3

        cache.put(4, 4);
        System.out.println(cache.get(1)); // -1
        System.out.println(cache.get(3)); // 3
        System.out.println(cache.get(4)); // 4
    }
}