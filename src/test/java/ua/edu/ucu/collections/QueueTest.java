package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {
    private Queue newQueue1;
    private Queue newQueue2;

    @Before
    public void SetUp() {
        String[] newLst = {"1", "2", "3"};
        newQueue1 = new Queue(newLst);
        newQueue2 = new Queue();
    }

    @Test
    public void testPeek() {
        assertEquals("1", newQueue1.peek());
        assertNull(newQueue2.peek());
    }

    @Test
    public void testDequeue() {
        String[] newLst = {"2", "3"};
        Queue newQueue3 = new Queue(newLst);
        assertEquals("1", newQueue1.dequeue());
        assertEquals(newQueue3.toString(), newQueue1.toString());
    }

    @Test
    public void testEnqueue() {
        String[] newLst = {"1", "2", "3", "4"};
        Queue newQueue4 = new Queue(newLst);
        newQueue1.enqueue("4");
        assertEquals(newQueue4.toString(), newQueue1.toString());
    }

}
