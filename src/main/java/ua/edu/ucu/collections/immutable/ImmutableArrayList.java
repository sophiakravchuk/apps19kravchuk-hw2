package ua.edu.ucu.collections.immutable;

public final class ImmutableArrayList implements ImmutableList {
    private int size;
    private int bufSize;
    private Object[] arr;

    public ImmutableArrayList() {
        this.init(0);
    }

    public ImmutableArrayList(int bufSize) {
        this.init(bufSize);
    }

    public ImmutableArrayList(Object[] els) {
        this.arr = els;
        this.size = els.length;
        this.bufSize = els.length;
    }

    public int getBufSize() {
        return bufSize;
    }

    private void init(int bufSize) {
        this.size = 0;
        this.arr = new Object[bufSize];
        this.bufSize = bufSize;
    }

    private ImmutableArrayList createCopy(int addBufferSize) {
        ImmutableArrayList newArr =
                new ImmutableArrayList(size + addBufferSize);

        for (int i = 0; i < size; i++) {
            newArr.arr[i] = this.arr[i];
        }
        newArr.bufSize = this.size + addBufferSize;
        newArr.size = this.size;
        return newArr;
    }

    private ImmutableArrayList createCopy() {
        return createCopy(0);
    }

    public ImmutableArrayList add(Object e) {
        return addAll(size, new Object[]{e});
    }

    public ImmutableArrayList add(int index, Object e) {
        return addAll(index, new Object[]{e});
    }

    public ImmutableArrayList addAll(Object[] c) {
        return addAll(size, c);
    }

    public ImmutableArrayList addAll(int index, Object[] c) {
        int n = c.length;
        ImmutableArrayList newArr = createCopy(n);
        if (size != 0) {
            checkIndexForAdd(index);
            for (int i = size - 1; i >= index; i--) {
                newArr.arr[i + n] = newArr.arr[i];
            }
        }
        for (int j = 0; j < n; j++) {
            newArr.arr[index + j] = c[j];
        }
        newArr.size += n;
        return newArr;
    }

    public Object get(int index) {
        checkIndexForAdd(index);
        if (index == size) {
            throw new IndexOutOfBoundsException();
        }
        return arr[index];
    }

    public ImmutableArrayList remove(int index) {
        checkIndexForAdd(index);
        if (index == size) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableArrayList newArr = new ImmutableArrayList(size - 1);
        for (int i = 0; i < index; i++) {
            newArr.arr[i] = arr[i];
        }
        for (int i = index; i < size - 1; i++) {
            newArr.arr[i] = arr[i + 1];
        }
        newArr.size = this.size - 1;
        return newArr;
    }

    public ImmutableArrayList set(int index, Object e) {
        checkIndexForAdd(index);
        if (index == size) {
            throw new IndexOutOfBoundsException();
        }
        ImmutableArrayList newArr = createCopy();
        newArr.arr[index] = e;
        return newArr;
    }

    public int indexOf(Object e) {
        for (int i = 0; i < size; i++) {
            if (arr[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    public int size() {
        return size;
    }

    public ImmutableArrayList clear() {
        return new ImmutableArrayList();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public Object[] toArray() {
        return arr;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(arr[i]);
            sb.append(", ");
        }
        int len = sb.length();
        if (len >= 1) {
            sb.delete(len - 2, len);
        }
        return sb.toString();
    }

    public boolean equals(ImmutableArrayList lst) {
        if (this.size != lst.size) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!lst.arr[i].equals(this.arr[i])) {
                return false;
            }
        }
        return true;
    }

    private void checkIndexForAdd(int index) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }
    }
}
