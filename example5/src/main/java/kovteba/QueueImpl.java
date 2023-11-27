package kovteba;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {

    private Node head;

    private int size;

    public static void main(String[] args) {

        QueueImpl queue = new QueueImpl();

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");

        Iterator it = queue.iterator();

        for (Object el : queue) {
            System.out.print(el);
        }

        System.out.println();

        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());
        it.remove();
        System.out.println(it.next());
        it.remove();
        System.out.println(queue);


    }

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

    }


    @Override
    public void enqueue(Object element) {
        if (isEmpty()) {
            head = new Node(element);
        } else {
            tail().link(new Node(element));
        }
        size++;
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

    @Override
    public Object dequeue() {
        Object result = head.getValue();
        head = head.next();
        return result;
    }

    @Override
    public Object top() {
        return head.getValue();
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

        private Node previous;

        private Node current;

        IteratorImpl() {
            head = QueueImpl.this.head;
            this.current = new Node(null, head);
            this.previous = new Node(null, current);
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
            Node next = current.next();
            previous = current;
            current = next;
            return next.getValue();
        }

        @Override
        public void remove() {
            Node next = current.next;
            if (next == head) {
                throw new IllegalStateException();
            }
            if (current == head) {
                QueueImpl.this.head = next;
                head = next;
            }
            size--;
            previous.link(next);

        }

    }

}
