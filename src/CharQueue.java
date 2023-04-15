/*
    Name: Sebastian Ferragut, David Tsukamoto
    PID:  A17263077, A17379000
 */

import java.util.Arrays;
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
     *
     * @throws IllegalArgumentException if capacity is out of valid range (i.e. less than 1)
     */
    public CharQueue(int capacity) throws IllegalArgumentException {
        if (capacity < 1) {
            throw new IllegalArgumentException();
        }
        circularArray = new char[capacity];
        length = 0;
        front = 0;
        rear = 0;
        queueCapacity = capacity;
    }

    /**
     * Checks if the queue is empty.
     *
     * @return Returns true if it is empty, false otherwise.
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Returns the number of elements currently stored in the queue
     *
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
            CharQueue tempQueue = new CharQueue(this.queueCapacity * QUEUE_DOUBLER);
            char tempChar = elem;
            this.enqueueLimit = this.queueCapacity;
            this.enqueueCounter = 0;
            /*
            loop to get the right order in new array
            start iterating from front to capacity, change iterator to rear, iterate up
             to front/null
            */
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

            this.rear = this.length;
            tempQueue.circularArray[rear] = tempChar;

            //assign instance vars to update to new size
            this.circularArray = tempQueue.circularArray;
            this.queueCapacity = tempQueue.queueCapacity;
            this.front = 0;
            this.rear = this.length + 1;
            this.length = this.length + 1;

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
     *
     * @return char elem at front index
     * @throws NoSuchElementException if the queue is empty
     */
    public char peek() throws NoSuchElementException {
        if (this.length == 0) {
            throw new NoSuchElementException();
        }
        return this.circularArray[this.front];
    }

    /**
     * Returns and removes the element at the front of the queue.
     *
     * @return char elem at front index
     * @throws NoSuchElementException if the queue is empty
     */
    public char dequeue() throws NoSuchElementException {
        if (this.length == 0) {
            throw new NoSuchElementException();
        }
        char toReturn = this.circularArray[this.front];
        this.circularArray[this.front] = 0;

        if (this.front == this.queueCapacity - 1) {
            this.front = 0;
        } else {
            this.front += 1;
        }
        this.length -= 1;
        return toReturn;
    }

    /**
     * Returns queue capacity
     * @return int queue capacity
     */
    public int getQueueCapacity() {
        return queueCapacity;
    }

    /**
     * Returns string representation of the circularArray
     * @return string version of variable circularArray
     */
    @Override
    public String toString() {
        return Arrays.toString(circularArray);
    }

}

