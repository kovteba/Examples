package kovteba;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    private Object[] arr;

    private int size;

    public StackImpl() {
        arr = new Object[5];
    }

    public static void main(String[] args) {

        StackImpl stack = new StackImpl();

        stack.push("A");
        stack.push("B");
        stack.push("C");

        Iterator iterator = stack.iterator();

        System.out.println(iterator.next());
        iterator.remove();
        System.out.println(iterator.next());
        iterator.remove();
        System.out.println(iterator.next());
        iterator.remove();
        System.out.println(stack);

    }


    @Override
    public void push(Object element) {
        if (size == 0) {
            arr[0] = element;
        } else {
            Object[] tmpArray = new Object[(arr.length * 3) / 2 + 1];
            tmpArray[0] = element;
            System.arraycopy(arr, 0, tmpArray, 1, size);
            arr = tmpArray;
        }
        this.size++;
    }

    @Override
    public Object pop() {
        Object[] tmpArray = new Object[(arr.length * 3) / 2 + 1];
        Object result = arr[0];
        System.arraycopy(arr, 1, tmpArray, 0, arr.length - 1);
        arr = tmpArray;
        this.size--;
        return result;
    }

    @Override
    public Object top() {
        return arr[0];
    }

    @Override
    public void clear() {
        size = 0;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        } else {
            StringBuilder result = new StringBuilder();
            result.append("[");
            for (int i = size - 1; i >= 0; i--) {
                result.append(arr[i]);
                if (i - 1 >= 0) {
                    result.append(", ");
                }
            }
            result.append("]");
            return result.toString();
        }
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {
        private int currentIndex = -1;

        @Override
        public boolean hasNext() {
            return currentIndex < StackImpl.this.size() - 1;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                this.currentIndex += 1;
                return arr[currentIndex];
            } else {
                throw new NoSuchElementException("no such element");
            }
        }

        @Override
        public void remove() {
            if (0 <= currentIndex && currentIndex <= StackImpl.this.size()) {
                StackImpl.this.remove(currentIndex);
                this.currentIndex -= 1;
            } else {
                throw new IllegalStateException("Exeption");
            }
        }
    }


    public void remove(int index) {
        Object[] tmpArray = new Object[(arr.length * 3) / 2 + 1];
        if (index == 0) {
            System.arraycopy(arr, 1, tmpArray, 0, size - 1);
        } else if (index == size) {
            System.arraycopy(arr, 0, tmpArray, 0, size - 1);
        } else {
            System.arraycopy(arr, 0, tmpArray, 0, index);
            System.arraycopy(arr, index + 1, tmpArray, index, size - index + 1);
        }
        arr = tmpArray;
        this.size--;
    }
}
