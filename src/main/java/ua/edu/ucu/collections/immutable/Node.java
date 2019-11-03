package ua.edu.ucu.collections.immutable;

class Node {
    Node next;
    Object value;


    Node(Object e) {
        this.value = e;
        this.next = null;
    }
}
