package ua.edu.ucu.collections.immutable;

public final class ImmutableArrayList implements ImmutableList{
    private int size;
    private int buf_size;
    private Object[] arr;

    public int getBuf_size() {
        return buf_size;
    }

    public ImmutableArrayList(int buf_size) {
        this.size = 0;
        this.arr = new Object[buf_size];
        this.buf_size = buf_size;
    }
    public ImmutableArrayList() {
        this.arr = new Object[0];
        this.size = 0;
        this.buf_size = 0;
    }
    public ImmutableArrayList(Object[] els) {
        this.arr = els;
        this.size = els.length;
        this.buf_size = els.length;
    }
    private ImmutableArrayList createCopy(int addBufferSize) {
        ImmutableArrayList newArr = new ImmutableArrayList(size+addBufferSize);

        for (int i = 0; i < size; i++) {
            newArr.arr[i] = this.arr[i];
        }
        newArr.buf_size = this.size+addBufferSize;
        newArr.size = this.size;
        return newArr;
    }
    private ImmutableArrayList createCopy() {
        return createCopy(0);
    }
    public ImmutableArrayList add(Object e) {
        ImmutableArrayList newArr = createCopy(1);
        newArr.arr[newArr.size] = e;
        newArr.size += 1;
        return newArr;
    }
    public ImmutableArrayList add(int index, Object e) {
        ImmutableArrayList newArr = createCopy(1);
        if (size != 0) {
            if (index >= size || index < 0) {
                throw new IllegalArgumentException();
            }
            for (int i = size; i > index; i--) {
                newArr.arr[i] = newArr.arr[i-1];
            }
        }
        newArr.arr[index] = e;
        newArr.size += 1;
        return newArr;
    }
    public ImmutableArrayList addAll(Object[] c) {
        int n = c.length;
        ImmutableArrayList newArr = createCopy(n);
        for (int i = 0; i < n; i++) {
            newArr.arr[this.size+i] = c[i];
        }
        newArr.size += n;
        return newArr;
    }
    public ImmutableArrayList addAll(int index, Object[] c) {
        int n = c.length;
        ImmutableArrayList newArr = createCopy(n);
        if (size!=0) {
            if (index >= size || index < 0) {
                throw new IllegalArgumentException();
            }
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
        if (index >= size || index < 0) {
            throw new IllegalArgumentException();
        }
        return arr[index];
    }
    public ImmutableArrayList remove(int index) {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException();
        }
        ImmutableArrayList newArr = new ImmutableArrayList(size-1);
        int j = 0;
        while (j != index) {
            newArr.arr[j] = arr[j];
            j++;
        }
        for (int i = index; i < size-1; i++) {
            newArr.arr[i] = arr[i+1];
        }
        newArr.size = this.size - 1;
        return newArr;
    }
    public ImmutableArrayList set(int index, Object e) {
        ImmutableArrayList newArr = createCopy();
        if (index >= size || index < 0) {
            throw new IllegalArgumentException();
        }
        newArr.arr[index] = e;
        return newArr;
    }
    public int indexOf(Object e) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == e) {
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
        if (len >= 1){
            sb.delete(len-2, len);
        }
        return sb.toString();
    }
}
