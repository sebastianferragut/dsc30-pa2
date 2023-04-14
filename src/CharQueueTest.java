import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

public class CharQueueTest {
    @org.junit.jupiter.api.Test
    public void constructorTest() {
        CharQueue queue = new CharQueue();
        //3 asserts for this, isEmpty, size, peek
        Assertions.assertTrue(queue.isEmpty());
        queue.enqueue('a');
        Assertions.assertEquals(1, queue.size());
        Assertions.assertEquals('a', queue.peek());

        //3 asserts, Exception, size, isEmpty
        CharQueue queueResize = new CharQueue(2);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CharQueue queueResizeAssert = new CharQueue(0);
        });
        Assertions.assertEquals(0, queueResize.size());
        Assertions.assertTrue(queueResize.isEmpty());
    }

    @org.junit.jupiter.api.Test
    public void isEmptyTest() {
        CharQueue queue = new CharQueue();
        Assertions.assertTrue(queue.isEmpty());
        queue.enqueue('a');
        Assertions.assertFalse(queue.isEmpty());
        queue.dequeue();
        Assertions.assertTrue(queue.isEmpty());
    }

    @org.junit.jupiter.api.Test
    public void sizeTest() {
        CharQueue queue = new CharQueue();
        Assertions.assertEquals(0, queue.size());
        queue.enqueue('a');
        Assertions.assertEquals(1, queue.size());
        queue.enqueue('b');
        Assertions.assertEquals(2, queue.size());
    }

    @org.junit.jupiter.api.Test
    public void clearTest() {
        CharQueue queue = new CharQueue();
        queue.enqueue('a');
        queue.clear();
        Assertions.assertEquals(0, queue.size());
        queue.enqueue('a');
        queue.enqueue('b');
        queue.clear();
        Assertions.assertEquals(0, queue.size());
        queue.enqueue('a');
        queue.enqueue('b');
        queue.enqueue('c');
        queue.clear();
        Assertions.assertEquals(0, queue.size());
    }

    @org.junit.jupiter.api.Test
    public void enqueueTest() {
        CharQueue queue = new CharQueue(3);
        queue.enqueue('a');
        queue.enqueue('b');
        queue.enqueue('c');
        queue.dequeue();
        queue.enqueue('z');
        Assertions.assertEquals("CharQueue{circularArray=[z, b, c]}", queue.toString());

        CharQueue queueResize = new CharQueue(2);
        queueResize.enqueue('a');
        queueResize.enqueue('b');
        queueResize.enqueue('c');
        Assertions.assertEquals(4, queueResize.getQueueCapacity());
        Assertions.assertEquals("CharQueue{circularArray=[a, b, c, \u0000]}",
                queueResize.toString());
        queueResize.enqueue('d');
        Assertions.assertEquals("CharQueue{circularArray=[a, b, c, d]}",
                queueResize.toString());
        Assertions.assertEquals(4, queueResize.size());

    }

    @org.junit.jupiter.api.Test
    public void peekTest() {
        CharQueue queue = new CharQueue();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            queue.peek();
        });
        queue.enqueue('a');
        Assertions.assertEquals('a', queue.peek());
        queue.enqueue('b');
        Assertions.assertEquals('a', queue.peek());
    }

    @org.junit.jupiter.api.Test
    public void dequeueTest() {
        CharQueue queue = new CharQueue();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            queue.dequeue();
        });

        queue.enqueue('a');
        Assertions.assertEquals(1, queue.size());
        queue.dequeue();
        Assertions.assertEquals(0, queue.size());

        queue.enqueue('a');
        queue.enqueue('b');
        Assertions.assertEquals(2, queue.size());
        queue.dequeue();
        Assertions.assertEquals(1, queue.size());
    }

    @Test
    public void testToString() {
        CharQueue queue = new CharQueue();
        Assertions.assertEquals("CharQueue{circularArray=[\u0000, " +
                "\u0000, \u0000, \u0000, \u0000]}", queue.toString());
        queue.enqueue('a');
        Assertions.assertEquals("CharQueue{circularArray=[a, " +
                "\u0000, \u0000, \u0000, \u0000]}", queue.toString());
        queue.enqueue('b');
        Assertions.assertEquals("CharQueue{circularArray=[a, " +
                "b, \u0000, \u0000, \u0000]}", queue.toString());
    }

    @Test
    public void getQueueCapacityTest() {
        CharQueue queue = new CharQueue(3);
        Assertions.assertEquals(3, queue.getQueueCapacity());
        CharQueue queueTwo = new CharQueue(4);
        Assertions.assertEquals(4, queueTwo.getQueueCapacity());
        CharQueue queueThree = new CharQueue();
        Assertions.assertEquals(5, queueThree.getQueueCapacity());
    }
}