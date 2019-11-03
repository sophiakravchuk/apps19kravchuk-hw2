package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList list;

    public Stack() {
        this.list = new ImmutableLinkedList();
    }

    public Stack(Object[] els) {
        this.list = new ImmutableLinkedList(els);
    }

    //Returns the object from the top of the Stack without removing it
    public Object peek() {
        return list.getLast();
    }

    //Removes and returns the object from the top of the Stack
    public Object pop() {
        Object elem = list.getLast();
        list = list.removeLast();
        return elem;
    }

    //Adds an object to the the top of the Stack
    public void push(Object e) {
        list = list.add(e);
    }

    @Override
    public String toString() {
        return list.toString();
    }
}

