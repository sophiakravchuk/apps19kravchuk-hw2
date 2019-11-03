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
        assertEquals("1", newLinkedList2.getHead().value);
        assertEquals("3", newLinkedList2.getTail().value);
        assertEquals(3, newLinkedList2.getSize());
    }

    private void compareArrayWithList(String[] arr, ImmutableLinkedList lst){
        ImmutableLinkedList newList = new ImmutableLinkedList(arr);
        assertEquals(newList.toString(), lst.toString());
        assertEquals(arr.length, lst.getSize());
        if(arr.length == 0){
            assertNull(lst.getTail());
            assertNull(lst.getHead());
        }else{
            assertEquals(arr[arr.length - 1], lst.getTail().value);
            assertEquals(arr[0], lst.getHead().value);
        }
    }
    @Test
    public void testAddAtLastPosition() {
        this.compareArrayWithList( new String[]{"1", "2", "3", "4"}, newLinkedList2.add("4"));
        this.compareArrayWithList( new String[]{"1"}, newLinkedList1.add("1"));
        this.compareArrayWithList( new String[]{"1", "2", "3", "4"}, newLinkedList2.addLast("4"));
        this.compareArrayWithList( new String[]{"1"}, newLinkedList1.addLast("1"));
        this.compareArrayWithList( new String[]{}, newLinkedList1.addLast("1").removeFirst());
    }

    @Test
    public void testAddatFirstPosition() {
        this.compareArrayWithList( new String[]{"0", "1", "2", "3"}, newLinkedList2.add(0, "0"));
        this.compareArrayWithList( new String[]{"1"}, newLinkedList1.add(0, "1"));
        this.compareArrayWithList( new String[]{"0", "1", "2", "3"}, newLinkedList2.addFirst("0"));
        this.compareArrayWithList( new String[]{"1"}, newLinkedList1.addFirst("1"));
    }

    @Test
    public void testAddatAnyPosition() {
        this.compareArrayWithList( new String[]{"1", "2", "2.1", "3"}, newLinkedList2.add(2,"2.1"));
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
        this.compareArrayWithList( new String[]{"a", "b", "c", "1", "2", "3"},
                newLinkedList2.addAll(0, toAdd));
        this.compareArrayWithList( new String[]{"1", "2", "3", "a", "b", "c"},
                newLinkedList2.addAll(toAdd));
        this.compareArrayWithList( new String[]{"1", "2", "a", "b", "c", "3"},
                newLinkedList2.addAll(2, toAdd));
        this.compareArrayWithList( new String[]{"1", "a", "b", "c", "2", "3"},
                newLinkedList2.addAll(1, toAdd));
        this.compareArrayWithList( new String[]{"a", "b", "c"},
                newLinkedList1.addAll(0, toAdd));
        this.compareArrayWithList( new String[]{"a", "b", "c"},
                newLinkedList1.addAll(toAdd));
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
        this.compareArrayWithList( new String[]{"2", "3"},
                newLinkedList2.remove(0));
        this.compareArrayWithList( new String[]{"2", "3"},
                newLinkedList2.removeFirst());
        this.compareArrayWithList( new String[]{"1", "3"},
                newLinkedList2.remove(1));
        this.compareArrayWithList( new String[]{"1", "2"},
                newLinkedList2.removeLast());
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
        this.compareArrayWithList( new String[]{"s", "2", "3"},
                newLinkedList2.set(0, "s"));
        this.compareArrayWithList( new String[]{"1", "s", "3"},
                newLinkedList2.set(1, "s"));
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
