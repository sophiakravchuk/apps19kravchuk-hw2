package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public final class Queue {
    private ImmutableLinkedList list;

    public Queue(){
        this.list = new ImmutableLinkedList();
    }
    public Queue(Object[] els){
        this.list = new ImmutableLinkedList(els);
    }
    //Returns the object at the beginning of the Queue without removing it
    public Object peek() {
        return list.getFirst();
    }
    //Removes and returns the object at the beginning of the Queue.
    public Object dequeue() {
        Object elem = list.getFirst();
        list = list.removeFirst();
        return elem;
    }
    //Adds an object to the end of the Queue.
    public void enqueue(Object e) {
        list = list.add(e);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
