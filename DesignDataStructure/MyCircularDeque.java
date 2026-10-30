
package DesignDataStructure;

public class MyCircularDeque {

    int[] deque;
    int front;
    int rear;
    int size;
    int capacity;

    public MyCircularDeque(int k) {
        capacity = k;
        deque = new int[k];

        front = 0;
        rear = 0;
        size = 0;
    }

    // Insert at front
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        }

        front = (front - 1 + capacity) % capacity;
        deque[front] = value;
        size++;

        return true;
    }

    // Insert at rear
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        }

        deque[rear] = value;
        rear = (rear + 1) % capacity;
        size++;

        return true;
    }

    // Delete from front
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        }

        front = (front + 1) % capacity;
        size--;

        return true;
    }

    // Delete from rear
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        }

        rear = (rear - 1 + capacity) % capacity;
        size--;

        return true;
    }

    // Get front element
    public int getFront() {
        if (isEmpty()) {
            return -1;
        }

        return deque[front];
    }

    // Get rear element
    public int getRear() {
        if (isEmpty()) {
            return -1;
        }

        int index = (rear - 1 + capacity) % capacity;
        return deque[index];
    }

    // Check empty
    public boolean isEmpty() {
        return size == 0;
    }

    // Check full
    public boolean isFull() {
        return size == capacity;
    }

    public static void main(String[] args) {
        MyCircularDeque deque = new MyCircularDeque(3);

        System.out.println(deque.insertLast(1));
        System.out.println(deque.insertLast(2));
        System.out.println(deque.insertFront(3));
        System.out.println(deque.insertFront(4));
        System.out.println(deque.getRear());
        System.out.println(deque.isFull());
        System.out.println(deque.deleteLast());
        System.out.println(deque.insertFront(4));
        System.out.println(deque.getFront());
    }
}
