/*
    Name: Sebastian Ferragut, David Tsukamoto
    PID:  A17263077, A17379000
 */

import java.util.NoSuchElementException;

/**
 * An implementation of a queue that only stores characters
 * using a circular array
 *
 * @author Sebastian Ferragut, David Tsukamoto
 * @since ${04-13-2023}
 */

public class CharQueue {
    /* instance variables, feel free to add more if you need */
    private char[] circularArray;
    private int length;
    private int front;
    private int rear;
    private int queueCapacity;
    private static final int QUEUE_DOUBLER = 2;
    private int enqueueLimit;
    private int enqueueCounter;

    /**
     * No-parameter constructor that creates a new queue with an
     * initial capacity of 5.
     */
    public CharQueue() {
        circularArray = new char[5];
        length = 0;
        front = 0;
        rear = 0;
        queueCapacity = 5;
    }

    /**
     * Creates a new queue with the specified capacity.
     * @throws IllegalArgumentException if capacity is out of valid range (i.e. less than 1)
     */
    public CharQueue(int capacity) throws IllegalArgumentException {
        if (capacity < 1) throw new IllegalArgumentException();
        circularArray = new char[capacity];
        length = 0;
        front = 0;
        rear = 0;
        queueCapacity = capacity;
    }

    /**
     * Checks if the queue is empty.
     * @return Returns true if it is empty, false otherwise.
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Returns the number of elements currently stored in the queue
     * @return number of elements in queue
     */
    public int size() {
        return length;
    }

    /**
     * Clears all elements in the queue.
     */
    public void clear() {
        this.circularArray = new char[queueCapacity];
        this.length = 0;
        this.front = 0;
        this.rear = 0;
    }

    /**
     * Adds a new element to the back of the queue.
     * Increase the capacity of the queue before adding
     * the elem if the maximum capacity is reached.
     *
     * @param elem New char element to be added to the queue
     */
    public void enqueue(char elem) {
        if (this.length == this.queueCapacity) {
            CharQueue tempQueue = new CharQueue(this.queueCapacity*QUEUE_DOUBLER);
            this.enqueueLimit = this.queueCapacity;
            this.enqueueCounter = 0;
            //loop to get the right order in new array
                //start iterating from front to capacity, change iterator to rear, iterate up
                // to front/null
            for (int i = this.front; i < enqueueLimit; i++) {
                if (i == this.queueCapacity + 1) {
                    i = this.rear;
                    this.enqueueLimit = this.front;
                }

                if (this.circularArray[i] == 0) {
                    break;
                }

                tempQueue.circularArray[enqueueCounter] = this.circularArray[i];
                this.enqueueCounter += 1;
            }


            //assign instance vars to update to new size
            this.circularArray = tempQueue.circularArray;
            this.queueCapacity = tempQueue.queueCapacity;
            this.front = 0;
            this.rear = this.length;


        } else {
            //wrap around
            if (this.rear == this.queueCapacity) {
                for (int i = 0; i < this.queueCapacity; i++) {
                    if (this.circularArray[i] == 0) {
                        this.circularArray[i] = elem;
                        this.length += 1;
                        break;
                    }
                }
            } else {
                //add element at rear index
                this.circularArray[rear] = elem;
                this.length += 1;
                if (this.rear + 1 == this.queueCapacity) {
                    this.rear = 0;
                } else {
                    this.rear += 1;
                }
            }
        }
    }

    /**
     * Returns the element at the front of the queue.
     * @return char elem at front index
     * @throws NoSuchElementException if the queue is empty
     */
    public char peek() {
        if (this.length == 0) throw new NoSuchElementException();
        return this.circularArray[this.front];
    }

    /**
     * Returns and removes the element at the front of the queue.
     * @return char elem at front index
     * @throws NoSuchElementException if the queue is empty
     */
    public char dequeue() {
        if (this.length == 0) throw new NoSuchElementException();
        char toReturn = this.circularArray[this.front];
        this.circularArray[this.front] = 0;

        if (this.front == this.queueCapacity - 1){
            this.front = 0;
        } else {
            this.front += 1;
        }
        this.length -= 1;
        return toReturn;
    }

    public static void main(String[] args) {
        CharQueue queue = new CharQueue(3);

        System.out.println("isEmpty() before adding elements: " + queue.isEmpty());
        System.out.println("size() before adding elements: " + queue.size());

        queue.enqueue('a');
        queue.enqueue('b');
        queue.enqueue('c');

        System.out.println("isEmpty() after adding elements: " + queue.isEmpty());
        System.out.println("size() after adding elements: " + queue.size());

        System.out.println("peek() result: " + queue.peek());

        char dequeued = queue.dequeue();
        System.out.println("dequeue() result: " + dequeued);

        System.out.println("size() after dequeuing: " + queue.size());

        queue.clear();

        System.out.println("isEmpty() after clearing: " + queue.isEmpty());
        System.out.println("size() after clearing: " + queue.size());

        CharQueue queue1 = new CharQueue(5);

        // add elements to the queue, causing it to wrap around
        queue1.enqueue('a');
        queue1.enqueue('b');
        queue1.enqueue('c');
        queue1.enqueue('d');
        queue1.enqueue('e');
        queue1.dequeue();
        queue1.dequeue();
        queue1.enqueue('f');
        queue1.enqueue('g');
        queue1.enqueue('h');
        queue1.enqueue('i');
        queue1.enqueue('j');

        // assert that the queue has the correct size and elements
        assert queue1.size() == 8;
        assert queue1.dequeue() == 'c';
        assert queue1.dequeue() == 'd';
        assert queue1.dequeue() == 'e';
        assert queue1.dequeue() == 'f';
        assert queue1.dequeue() == 'g';
        assert queue1.dequeue() == 'h';
        assert queue1.dequeue() == 'i';
        assert queue1.dequeue() == 'j';

        // clear the queue and add elements until it needs to be resized
        System.out.println("q1 size() after asserts: " + queue1.size());
        queue1.clear();
        System.out.println("q1 size() after clear: " + queue1.size());
        for (int i = 0; i < 10; i++) {
            queue1.enqueue((char) ('a' + i));
        }

        // assert that the queue has the correct size and elements
        assert queue1.size() == 10;
        assert queue1.dequeue() == 'a';
        assert queue1.dequeue() == 'b';
        assert queue1.dequeue() == 'c';
        assert queue1.dequeue() == 'd';
        assert queue1.dequeue() == 'e';
        assert queue1.dequeue() == 'f';
        assert queue1.dequeue() == 'g';
        assert queue1.dequeue() == 'h';
        assert queue1.dequeue() == 'i';
        assert queue1.dequeue() == 'j';

    }
}
