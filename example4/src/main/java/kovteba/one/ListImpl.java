package kovteba.one;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    private Node head;

    private int size;

    private static final class Node {

        private Object value;

        private Node next;

        public Node(Object value) {
            this.value = value;
        }

        public Node(Object value, Node next) {
            this.value = value;
            this.next = next;
        }

        public void link(Node next) {
            this.next = next;
        }

        public void unlink() {
            this.next = null;
        }

        public Node next() {
            return next;
        }

        public boolean hasNext() {
            return next != null;
        }

        public Object getValue() {
            return value;
        }

        public Object replaceValue(Object value) {
            Object old = this.value;
            this.value = value;
            return old;
        }
    }

    @Override
    public void addFirst(Object element) {
        Node node = new Node(element, this.head);
        this.head = node;
        this.size++;
    }

    @Override
    public void addLast(Object element) {
        if (isEmpty()) {
            head = new Node(element);
        } else {
            tail().link(new Node(element));
        }
        this.size++;
    }

    @Override
    public void removeFirst() {
        this.head = navigator(1);
        this.size--;
    }

    @Override
    public void removeLast() {
        navigator(size - 2).link(null);
        this.size--;
    }

    @Override
    public Object getFirst() {
        return head.getValue();
    }

    @Override
    public Object getLast() {
        return tail().getValue();
    }

    @Override
    public Object search(Object element) {
        Node n = new Node(null, head);
        while (n.hasNext()) {
            n = n.next();
            if (n.getValue().equals(element)) {
                return n.getValue();
            }
        }
        return null;
    }

    @Override
    public boolean remove(Object element) {
        if (head.getValue().equals(element)) {
            head = navigator(1);
        } else {
            int index = 0;
            Node n = head;
            while (n.hasNext()) {
                n = n.next();
                index++;
                if (n.getValue().equals(element)) {
                    removeByIndex(index);
                    this.size--;
                    return true;
                }
            }
        }
        return false;
    }

    private void removeByIndex(int index) {
        if (index == 0) {
            this.head = navigator(1);
        } else if (index == size - 1) {
            navigator(size - 2).link(null);
        } else {
            navigator(index - 1).link(navigator(index + 1));
        }
    }

    @Override
    public void clear() {
        this.head = null;
        this.size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        sb.append(head.getValue());
        Node n = head;
        while (n.hasNext()) {
            n = n.next();
            sb.append(", ").append(n.getValue());
        }
        return sb.append("]").toString();
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private Node head;


        private Node current;

        private Node next;


        IteratorImpl() {
            head = ListImpl.this.head;
            this.current = new Node(null, head);
        }

        @Override
        public boolean hasNext() {
            return current.hasNext();
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            next = current.next();
            current = next;
            return next.getValue();
        }

        @Override
        public void remove() {
            if (next == null) {
                throw new IllegalStateException("Exeption");
            } else {
                ListImpl.this.remove(next.getValue());
                next = null;
            }

        }
    }


    public boolean isEmpty() {
        return head == null;
    }

    private Node tail() {
        Node n = head;
        while (n.hasNext()) {
            n = n.next();
        }
        return n;
    }

    private Node navigator(int index) {
        Node n = head;
        for (int i = 0; i < index; i++) {
            n = n.next();
        }
        return n;
    }

}