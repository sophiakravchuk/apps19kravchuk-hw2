package ua.edu.ucu.collections;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class StackTest {
    private Stack newStack1;
    private Stack newStack2;

    @Before
    public void SetUp() {
        String[] newLst = {"1", "2", "3"};
        newStack1 = new Stack(newLst);
        newStack2 = new Stack();
    }

    @Test
    public void testPeek() {
        assertEquals("3", newStack1.peek());
        assertNull(newStack2.peek());
    }

    @Test
    public void testDequeue() {
        String[] newLst = {"1", "2"};
        Queue newQueue3 = new Queue(newLst);
        assertEquals("3", newStack1.pop());
        assertEquals(newQueue3.toString(), newStack1.toString());
    }

    @Test
    public void testEnqueue() {
        String[] newLst = {"1", "2", "3", "4"};
        Queue newQueue4 = new Queue(newLst);
        newStack1.push("4");
        assertEquals(newQueue4.toString(), newStack1.toString());
    }

}
