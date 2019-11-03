package ua.edu.ucu.collections.immutable;

class Node {
    Node next;
    Object node;

    Node(Object e) {
        this.node = e;
        this.next = null;
    }
}


public final class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private Node tail;
    private int size;

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    public ImmutableLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public ImmutableLinkedList(Object[] els) {
        this.head = new Node(els[0]);
        Node local = head;
        for (int i = 1; i < els.length; i++) {
            local.next = new Node(els[i]);
            local = local.next;
        }
        this.size = els.length;
        this.tail = local;
    }

    private ImmutableLinkedList createCopy() {
        ImmutableLinkedList newImmLinkedList = new ImmutableLinkedList();
        if (size == 0) {
            return newImmLinkedList;
        }
        newImmLinkedList.head = new Node(this.head.node);
        Node localNode = this.head;
        Node newlocalNode = newImmLinkedList.head;
        for (int i = 1; i < size; i++) {
            newlocalNode.next = new Node(localNode.next.node);
            newlocalNode  = newlocalNode.next;
            localNode  = localNode.next;
            if (i == size-1) {
                newImmLinkedList.tail = newlocalNode;
            }
        }
        newImmLinkedList.size++;
        return newImmLinkedList;
    }

    private Node getElementByInd(int index) {
        if (index < 0) {
            throw new IllegalArgumentException();
        }
        Node elementByInd = this.head;
        while (index > 0) {
            elementByInd = elementByInd.next;
            if (elementByInd == null) {
                throw new IllegalArgumentException();
            }
            index--;

        }
        return elementByInd;
    }

    public ImmutableLinkedList add(Object e) {
        ImmutableLinkedList newImmLinkedList = createCopy();
        Node eNode = new Node(e);
        if (newImmLinkedList.size == 0) {
            newImmLinkedList.head = eNode;
            newImmLinkedList.tail = eNode;
        }else {
            newImmLinkedList.tail.next = eNode;
            newImmLinkedList.tail = eNode;
        }
        newImmLinkedList.size = this.size + 1;
        return newImmLinkedList;
    }

    public ImmutableLinkedList add(int index, Object e) {
        ImmutableLinkedList newImmLinkedList = createCopy();
        Node previousElement;
        Node newElement = new Node(e);
        if (index == 0) {
            newElement.next = newImmLinkedList.head;
            newImmLinkedList.head = newElement;
        }else {
            previousElement = newImmLinkedList.getElementByInd(index-1);
            newElement.next = previousElement.next;
            previousElement.next = newElement;
        }
        newImmLinkedList.size = this.size + 1;
        return newImmLinkedList;
    }

    public ImmutableLinkedList addAll(Object[] c) {
        ImmutableLinkedList newImmLinkedList = createCopy();
        ImmutableLinkedList cImmLinkedList = new ImmutableLinkedList(c);
        if (newImmLinkedList.size == 0) {
            newImmLinkedList.head = cImmLinkedList.head;
            newImmLinkedList.tail = cImmLinkedList.tail;
        }else{
            newImmLinkedList.tail.next = cImmLinkedList.head;
            newImmLinkedList.tail = cImmLinkedList.tail;
        }
        newImmLinkedList.size += cImmLinkedList.size;
        return newImmLinkedList;
    }

    public ImmutableLinkedList addAll(int index, Object[] c) {
        ImmutableLinkedList newImmLinkedList = createCopy();
        ImmutableLinkedList cImmLinkedList = new ImmutableLinkedList(c);
        Node previousElement;
        if (index == 0) {
            cImmLinkedList.tail.next = newImmLinkedList.head;
            newImmLinkedList.head = cImmLinkedList.head;
        }else{
            if (index >= size) {
                throw new IllegalArgumentException(); }
            previousElement = newImmLinkedList.getElementByInd(index-1);
            cImmLinkedList.tail.next = previousElement.next;
            previousElement.next = cImmLinkedList.head;
        }
        newImmLinkedList.size += cImmLinkedList.size;
        return newImmLinkedList;
    }

    public Object get(int index) {
        return getElementByInd(index).node;
    }

    public ImmutableLinkedList remove(int index) {
        if (size == 0) {
            throw new IllegalArgumentException();
        }
        ImmutableLinkedList newImmLinkedList = createCopy();
        Node previousElement;
        if (index == 0) {
            newImmLinkedList.head = newImmLinkedList.head.next;
        }else{
            previousElement = newImmLinkedList.getElementByInd(index-1);
            previousElement.next = previousElement.next.next;
        }
        newImmLinkedList.size = this.size - 1;
        return newImmLinkedList;
    }

    public ImmutableLinkedList set(int index, Object e) {
        ImmutableLinkedList newImmLinkedList = createCopy();
        Node elementToChange = newImmLinkedList.getElementByInd(index);
        elementToChange.node = e;
        return newImmLinkedList;
    }

    public int indexOf(Object e) {
        int resIndex = 0;
        Node localNode = head;
        while (localNode.node != e) {
            resIndex++;
            localNode = localNode.next;
            if (localNode == null) {
                return -1;
            }
        }
        return resIndex;
    }

    public int size() {
        return size;
    }
    public ImmutableLinkedList clear() {
        ImmutableLinkedList newImmLinkedList = createCopy();
        newImmLinkedList.head = null;
        newImmLinkedList.tail = null;
        newImmLinkedList.size = 0;
        return newImmLinkedList;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node localNode = head;
        int ind = 0;
        while (localNode != null) {
            arr[ind] = localNode.node;
            localNode = localNode.next;
            ind += 1;
        }
        return arr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node localNode = head;
        while (localNode != null) {
            sb.append(localNode.node.toString());
            sb.append(", ");
            localNode = localNode.next;
        }
        int len = sb.length();
        if (len >= 1){
            sb.delete(len-2, len);
        }
        return sb.toString();
    }

    public ImmutableLinkedList addFirst(Object e) {
        return add(0, e);
    }

    public ImmutableLinkedList addLast(Object e) {
        return add(e);
    }

    public Object getFirst() {
        if (head == null) {
            return null;
        }
        return getHead().node;
    }

    public Object getLast() {
        if (tail == null) {
            return null;
        }
        return getTail().node;
    }
    public ImmutableLinkedList removeFirst() {
        return remove(0);
    }
    public ImmutableLinkedList removeLast() {
        return remove(size-1);
    }
}
