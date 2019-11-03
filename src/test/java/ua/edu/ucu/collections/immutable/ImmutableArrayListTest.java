package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImmutableArrayListTest {
    private ImmutableArrayList newArrayList1;
    private ImmutableArrayList newArrayList2;
    private ImmutableArrayList newArrayList3;

    @Before
    public void SetUp() {
        newArrayList1 = new ImmutableArrayList();
        String[] newIntLst = {"1", "2", "3"};
        newArrayList2 = new ImmutableArrayList(newIntLst);
        newArrayList3 = new ImmutableArrayList(10);
    }

    @Test
    public void testImmutableArrayListBufSize() {
        Object[] newObj = new Object[0];
        assertEquals(0, newArrayList1.getBufSize());
        assertEquals(0, newArrayList1.size());
        assertArrayEquals(newObj, newArrayList1.toArray());
    }

    @Test
    public void testImmutableArrayListWithEls() {
        String[] newIntLst = {"1", "2", "3"};
        assertEquals(3, newArrayList2.getBufSize());
        assertEquals(3, newArrayList2.size());
        assertArrayEquals(newIntLst, newArrayList2.toArray());
    }

    @Test
    public void testImmutableArrayListEmpty() {
        Object[] newObj = new Object[10];
        assertEquals(10, newArrayList3.getBufSize());
        assertEquals(0, newArrayList3.size());
        assertArrayEquals(newObj, newArrayList3.toArray());
    }

    @Test
    public void testAddatLastPosition() {
        String[] newArrLst1 = {"1"};
        ImmutableArrayList newAL1 = newArrayList1.add("1");
        assertArrayEquals(newArrLst1, newAL1.toArray());

        String[] newArrLst2 = {"1", "2", "3", "4"};
        ImmutableArrayList newAL2 = newArrayList2.add("4");
        assertArrayEquals(newArrLst2, newAL2.toArray());

        String[] newArrLst3 = {"1"};
        ImmutableArrayList newAL3 = newArrayList1.add("1");
        assertArrayEquals(newArrLst3, newAL3.toArray());
    }

    @Test
    public void testAddatFirstPosition() {
        String[] newArrLst1 = {"1"};
        ImmutableArrayList newAL1 = newArrayList1.add(0, "1");
        assertArrayEquals(newArrLst1, newAL1.toArray());

        String[] newArrLst2 = {"0", "1", "2", "3"};
        ImmutableArrayList newAL2 = newArrayList2.add(0, "0");
        assertArrayEquals(newArrLst2, newAL2.toArray());

        String[] newArrLst3 = {"1"};
        ImmutableArrayList newAL3 = newArrayList1.add(0, "1");
        assertArrayEquals(newArrLst3, newAL3.toArray());
    }

    @Test
    public void testAddatAnyPosition() {
        String[] newIntLst2 = {"0", "1", "2", "3"};
        ImmutableArrayList newAL2 = newArrayList2.add(0, "0");
        assertArrayEquals(newIntLst2, newAL2.toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidIndextooBig() {
        newArrayList2.add(5, "2");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidIndexNegative() {
        newArrayList2.add(-5, "2");
    }

    @Test
    public void testAddAllatAnyPosition() {
        String[] toAdd = {"a", "b", "c"};
        ImmutableArrayList newALAny1 = newArrayList1.addAll(0, toAdd);

        String[] newatAnyPos2 = {"1", "a", "b", "c", "2", "3"};
        ImmutableArrayList newALAny2 = newArrayList2.addAll(1, toAdd);
        String[] newatLastPos2 = {"1", "2", "3", "a", "b", "c"};
        ImmutableArrayList newALFirst2 = newArrayList2.addAll(toAdd);

        String[] newatAnyPos3 = {"a", "b", "c"};
        ImmutableArrayList newALAny3 = newArrayList3.addAll(0, toAdd);

        assertArrayEquals(toAdd, newALAny1.toArray());
        assertArrayEquals(newatAnyPos2, newALAny2.toArray());
        assertArrayEquals(newatLastPos2, newALFirst2.toArray());
        assertArrayEquals(newatAnyPos3, newALAny3.toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAllInvalidIndextooBig() {
        String[] toAdd = {"a", "b", "c"};
        newArrayList2.addAll(5, toAdd);
    }

    @Test
    public void testGet() {
        assertEquals("1", newArrayList2.get(0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInvalidIndextooBig() {
        Object a = newArrayList2.get(5);
    }

    @Test
    public void testRemove() {
        String[] newatFirstPos = {"2", "3"};
        String[] newatAnyPos = {"1", "3"};
        String[] newatLastPos = {"1", "2"};

        assertArrayEquals(newatFirstPos, newArrayList2.remove(0).toArray());
        assertArrayEquals(newatAnyPos, newArrayList2.remove(1).toArray());
        assertArrayEquals(newatLastPos,
                newArrayList2.remove(newArrayList2.size() - 1).toArray());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveInvalidIndex() {
        newArrayList2.remove(6);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveInvalidIndex2() {
        newArrayList2.remove(-6);
    }

    @Test
    public void testSet() {
        String[] newatFirstPos = {"s", "2", "3"};
        String[] newatAnyPos = {"1", "s", "3"};

        assertArrayEquals(newatFirstPos, newArrayList2.set(0, "s").toArray());
        assertArrayEquals(newatAnyPos, newArrayList2.set(1, "s").toArray());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidIndex() {
        newArrayList2.set(6, "s");
    }

    @Test
    public void testindexOf() {
        assertEquals(0, newArrayList2.indexOf("1"));
        assertEquals(-1, newArrayList2.indexOf("s"));
    }

    @Test
    public void testSize() {
        assertEquals(3, newArrayList2.size());
        assertEquals(0, newArrayList1.size());
    }

    @Test
    public void testClear() {
        ImmutableArrayList cleared = newArrayList2.clear();
        assertArrayEquals(newArrayList1.toArray(), cleared.toArray());
        assertEquals(0, cleared.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(newArrayList1.isEmpty());
        assertFalse(newArrayList2.isEmpty());
    }

    @Test
    public void testToSyring() {
        String newArr = "1, 2, 3";
        assertEquals(newArr, newArrayList2.toString());
    }

}
