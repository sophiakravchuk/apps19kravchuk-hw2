package ua.edu.ucu.collections.immutable;



public final class ImmutableLinkedList implements ImmutableList {
    private Node head;
    private Node tail;
    private int size;

    public ImmutableLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public ImmutableLinkedList(Object[] els) {
        if (els == null) {
            throw new IndexOutOfBoundsException();
        }
        if (els.length == 0) {
            this.head = null;
            this.tail = null;
            this.size = 0;
        } else {
            this.head = new Node(els[0]);
            Node local = head;
            for (int i = 1; i < els.length; i++) {
                local.next = new Node(els[i]);
                local = local.next;
            }
            this.size = els.length;
            this.tail = local;
        }
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public int getSize() {
        return size;
    }

    private ImmutableLinkedList createCopy() {
        ImmutableLinkedList newImmLinkedList = new ImmutableLinkedList();
        if (size == 0) {
            return newImmLinkedList;
        }
        newImmLinkedList.head = new Node(this.head.value);
        Node localNode = this.head;
        Node newlocalNode = newImmLinkedList.head;
        for (int i = 1; i < size; i++) {
            newlocalNode.next = new Node(localNode.next.value);
            newlocalNode = newlocalNode.next;
            localNode = localNode.next;
        }
        newImmLinkedList.tail = newlocalNode;
        newImmLinkedList.size = size;
        return newImmLinkedList;
    }

    private Node getElementByIndex(int index) {
        int indL = index;
        checkIndexForAdd(index);
        Node elementByInd = this.head;
        while (indL > 0) {
            elementByInd = elementByInd.next;
            if (elementByInd == null) {
                throw new IndexOutOfBoundsException();
            }
            indL--;

        }
        return elementByInd;
    }

    public ImmutableLinkedList add(Object e) {
        return addAll(size, new Object[]{e});
    }

    public ImmutableLinkedList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }

    public ImmutableLinkedList addAll(Object[] c) {
        return addAll(size, c);
    }

    public ImmutableLinkedList addAll(int index, Object[] c) {
        checkIndexForAdd(index);
        ImmutableLinkedList newImmLinkedList = createCopy();
        ImmutableLinkedList cImmLinkedList = new ImmutableLinkedList(c);
        Node previousElement;
        if (index == 0) {
            cImmLinkedList.tail.next = newImmLinkedList.head;
            newImmLinkedList.head = cImmLinkedList.head;
            if (size == 0) {
                newImmLinkedList.tail = cImmLinkedList.tail;
            }
        } else {
            if (index > size) {
                throw new IndexOutOfBoundsException();
            }
            previousElement = newImmLinkedList.getElementByIndex(index - 1);
            cImmLinkedList.tail.next = previousElement.next;
            previousElement.next = cImmLinkedList.head;
            if (index == size) {
                newImmLinkedList.tail = cImmLinkedList.tail;
            }
        }
        newImmLinkedList.size += cImmLinkedList.size;
        return newImmLinkedList;
    }

    public Object get(int index) {
        return getElementByIndex(index).value;
    }

    public ImmutableLinkedList remove(int index) {
        if (size == 0) {
            throw new IndexOutOfBoundsException();
        }
        if (size == 1) {
            return new ImmutableLinkedList();
        }

        ImmutableLinkedList newImmLinkedList = createCopy();
        Node previousElement;
        if (index == 0) {
            newImmLinkedList.head = newImmLinkedList.head.next;
        } else {
            previousElement = newImmLinkedList.getElementByIndex(index - 1);
            if (index == size - 1) {
                previousElement.next = null;
                newImmLinkedList.tail = previousElement;
            } else {
                previousElement.next = previousElement.next.next;
            }
        }
        newImmLinkedList.size = this.size - 1;
        return newImmLinkedList;
    }

    public ImmutableLinkedList set(int index, Object e) {
        ImmutableLinkedList newImmLinkedList = createCopy();
        Node elementToChange = newImmLinkedList.getElementByIndex(index);
        elementToChange.value = e;
        return newImmLinkedList;
    }

    public int indexOf(Object e) {
        int resIndex = 0;
        Node localNode = head;
        while (!localNode.value.equals(e)) {
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
        return new ImmutableLinkedList();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        Object[] arr = new Object[size];
        Node localNode = head;
        int ind = 0;
        while (localNode != null) {
            arr[ind] = localNode.value;
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
            sb.append(localNode.value.toString());
            sb.append(", ");
            localNode = localNode.next;
        }
        int len = sb.length();
        if (len >= 1) {
            sb.delete(len - 2, len);
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
        return getHead().value;
    }

    public Object getLast() {
        if (tail == null) {
            return null;
        }
        return getTail().value;
    }

    public ImmutableLinkedList removeFirst() {
        return remove(0);
    }

    public ImmutableLinkedList removeLast() {
        return remove(size - 1);
    }

    public boolean equals(ImmutableLinkedList lst) {
        if (this.size != lst.size) {
            return false;
        }
        Node th = this.head;
        Node lh = lst.head;
        for (int i = 0; i < size; i++) {
            if (!th.value.equals(lh.value)) {
                return false;
            }
            th = th.next;
            lh = lh.next;
        }
        return true;
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }
}
