package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImmutableLinkedListTest {
    private ImmutableLinkedList newLinkedList1;
    private ImmutableLinkedList newLinkedList2;

    @Before
    public void SetUp() {
        newLinkedList1 = new ImmutableLinkedList();
        String[] newIntLst = {"1", "2", "3"};
        newLinkedList2 = new ImmutableLinkedList(newIntLst);
    }

    @Test
    public void testImmutableLinkedList() {
        assertNull(newLinkedList1.getHead());
        assertNull(newLinkedList1.getTail());
        assertEquals(0, newLinkedList1.getSize());
    }

    @Test
    public void testImmutableLinkedListWithEls() {
        assertEquals("1", newLinkedList2.getHead().node);
        assertEquals("3", newLinkedList2.getTail().node);
        assertEquals(3, newLinkedList2.getSize());
    }

    @Test
    public void testAddatLastPosition() {
        String[] newIntLst1 = {"1", "2", "3", "4"};
        ImmutableLinkedList newLList1 = new ImmutableLinkedList(newIntLst1);
        ImmutableLinkedList newImLL = newLinkedList2.add("4");
        assertEquals(newLList1.toString(), newImLL.toString());

        String[] newIntLst2 = {"1"};
        ImmutableLinkedList newLList2 = new ImmutableLinkedList(newIntLst2);
        ImmutableLinkedList newImLL2 = newLinkedList1.add("1");
        assertEquals(newLList2.toString(), newImLL2.toString());

        String[] newIntLst3 = {"1", "2", "3", "4"};
        ImmutableLinkedList newLList3 = new ImmutableLinkedList(newIntLst3);
        ImmutableLinkedList newImLL3 = newLinkedList2.addLast("4");
        assertEquals(newLList3.toString(), newImLL3.toString());

        String[] newIntLst4 = {"1"};
        ImmutableLinkedList newLList4 = new ImmutableLinkedList(newIntLst4);
        ImmutableLinkedList newImLL4 = newLinkedList1.addLast("1");
        assertEquals(newLList4.toString(), newImLL4.toString());
    }

    @Test
    public void testAddatFirstPosition() {
        String[] newIntLst1 = {"0", "1", "2", "3"};
        ImmutableLinkedList newLList1 = new ImmutableLinkedList(newIntLst1);
        ImmutableLinkedList newImLL = newLinkedList2.add(0, "0");
        assertEquals(newLList1.toString(), newImLL.toString());

        String[] newIntLst2 = {"1"};
        ImmutableLinkedList newLList2 = new ImmutableLinkedList(newIntLst2);
        ImmutableLinkedList newImLL2 = newLinkedList1.add(0, "1");
        assertEquals(newLList2.toString(), newImLL2.toString());

        String[] newIntLst3 = {"0", "1", "2", "3"};
        ImmutableLinkedList newLList3 = new ImmutableLinkedList(newIntLst3);
        ImmutableLinkedList newImLL3 = newLinkedList2.addFirst("0");
        assertEquals(newLList3.toString(), newImLL3.toString());

        String[] newIntLst4 = {"1"};
        ImmutableLinkedList newLList4 = new ImmutableLinkedList(newIntLst4);
        ImmutableLinkedList newImLL4 = newLinkedList1.addFirst("1");
        assertEquals(newLList4.toString(), newImLL4.toString());
    }

    @Test
    public void testAddatAnyPosition() {
        String[] newIntLst1 = {"1", "2", "2.1", "3"};
        ImmutableLinkedList newLList1 = new ImmutableLinkedList(newIntLst1);
        ImmutableLinkedList newImLL = newLinkedList2.add(2, "2.1");
        assertEquals(newLList1.toString(), newImLL.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidIndextooBig() {
        ImmutableLinkedList newImLL = newLinkedList2.add(5, "2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidIndexNegative() {
        ImmutableLinkedList newImLL1 = newLinkedList2.add(-5, "2");
    }

    @Test
    public void testAddAllatAnyPosition() {
        String[] toAdd = {"a", "b", "c"};
        String[] newatFirstPos = {"a", "b", "c", "1", "2", "3"};
        ImmutableLinkedList ILLatFirstPos = new ImmutableLinkedList(newatFirstPos);

        String[] newatLastPos = {"1", "2", "3", "a", "b", "c"};
        ImmutableLinkedList ILLnewatLastPos = new ImmutableLinkedList(newatLastPos);

        String[] newatPreLastPos = {"1", "2", "a", "b", "c", "3"};
        ImmutableLinkedList ILLnewatPreLastPos = new ImmutableLinkedList(newatPreLastPos);

        String[] newatAnyPos = {"1", "a", "b", "c", "2", "3"};
        ImmutableLinkedList ILLatAnyPos = new ImmutableLinkedList(newatAnyPos);

        String[] newEmptyadd = {"a", "b", "c"};
        ImmutableLinkedList ILLnewEmptyadd = new ImmutableLinkedList(newEmptyadd);

        ImmutableLinkedList newImLLFirstPos = newLinkedList2.addAll(0, toAdd);
        ImmutableLinkedList newImLLLastPos = newLinkedList2.addAll(2, toAdd);
        ImmutableLinkedList newImLLAnyPos = newLinkedList2.addAll(1, toAdd);
        ImmutableLinkedList newImLLEmptyadd = newLinkedList1.addAll(0, toAdd);
        ImmutableLinkedList newImLLLastPosWithoutIndex = newLinkedList2.addAll(toAdd);
        ImmutableLinkedList newImLLLastPosWithoutIndexEmpty = newLinkedList1.addAll(toAdd);


        assertEquals(ILLatFirstPos.toString(), newImLLFirstPos.toString());
        assertEquals(ILLnewatPreLastPos.toString(), newImLLLastPos.toString());
        assertEquals(ILLatAnyPos.toString(), newImLLAnyPos.toString());
        assertEquals(ILLnewEmptyadd.toString(), newImLLEmptyadd.toString());
        assertEquals(ILLnewatLastPos.toString(), newImLLLastPosWithoutIndex.toString());
        assertEquals("a, b, c", newImLLLastPosWithoutIndexEmpty.toString());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testAddAllInvalidIndextooBig() {
        String[] toAdd = {"a", "b", "c"};
        ImmutableLinkedList newImLL = newLinkedList2.addAll(5, toAdd);
    }
    @Test
    public void testGet() {
        assertEquals("1", newLinkedList2.get(0));
    }

    @Test
    public void testRemove() {
        String[] newatFirstPos = {"2", "3"};
        ImmutableLinkedList ILLatFirstPos = new ImmutableLinkedList(newatFirstPos);
        String[] newatAnyPos = {"1", "3"};
        ImmutableLinkedList ILLatAnyPos = new ImmutableLinkedList(newatAnyPos);
        String[] newatLastPos = {"1", "2"};
        ImmutableLinkedList ILLatLastPos = new ImmutableLinkedList(newatLastPos);

        assertEquals(ILLatFirstPos.toString(), newLinkedList2.remove(0).toString());
        assertEquals(ILLatAnyPos.toString(), newLinkedList2.remove(1).toString());
        assertEquals(ILLatFirstPos.toString(), newLinkedList2.removeFirst().toString());
        assertEquals(ILLatLastPos.toString(), newLinkedList2.removeLast().toString());

    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveInvalidIndex() {
        newLinkedList2.remove(6);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testRemoveInvalidIndex2() {
        newLinkedList1.remove(6);
    }

    @Test
    public void testSet() {
        String[] newatFirstPos = {"s", "2", "3"};
        ImmutableLinkedList ILLatFirstPos = new ImmutableLinkedList(newatFirstPos);
        String[] newatAnyPos = {"1", "s", "3"};
        ImmutableLinkedList ILLatAnyPos = new ImmutableLinkedList(newatAnyPos);

        assertEquals(ILLatFirstPos.toString(), newLinkedList2.set(0, "s").toString());
        assertEquals(ILLatAnyPos.toString(), newLinkedList2.set(1, "s").toString());
    }
    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidIndex() {
        newLinkedList2.set(6, "s");
    }
    @Test
    public void testindexOf() {
        assertEquals(0, newLinkedList2.indexOf("1"));
        assertEquals(-1, newLinkedList2.indexOf("s"));
    }
    @Test
    public void testSize() {
        assertEquals(3, newLinkedList2.size());
        assertEquals(0, newLinkedList1.size());
    }
    @Test
    public void testClear() {
        ImmutableLinkedList cleared = newLinkedList2.clear();
        assertEquals(newLinkedList1.toString(), cleared.toString());
        assertEquals(0, cleared.getSize());
        assertNull(cleared.getHead());
        assertNull(cleared.getTail());
    }
    @Test
    public void testIsEmpty() {
        assertTrue(newLinkedList1.isEmpty());
        assertFalse(newLinkedList2.isEmpty());
    }
    @Test
    public void testToArray() {
        String[] newArr = {"1", "2", "3"};
        assertArrayEquals(newArr, newLinkedList2.toArray());
    }
    @Test
    public void testGetFirst() {
        assertEquals("1", newLinkedList2.getFirst());
        assertNull(newLinkedList1.getFirst());
    }
    @Test
    public void testGetLast() {
        assertEquals("3", newLinkedList2.getLast());
        assertNull(newLinkedList1.getLast());
    }
}
