package kovteba.one;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

   private Object[] arr;

   private int size;

   public ArrayImpl() {
      arr = new Object[1];
   }

   public int arrLengt() {
      return this.arr.length;
   }

   @Override
   public void add(Object element) {
      Object[] tmpArray = new Object[size + 1];
      System.arraycopy(arr, 0, tmpArray, 0, size);
      arr = tmpArray;
      arr[size] = element;
      size++;

   }

   @Override
   public void set(int index, Object element) {
      arr[index] = element;
   }

   @Override
   public Object get(int index) {
      return arr[index];
   }

   @Override
   public int indexOf(Object element) {
      int flag = -1;
      for (int i = 0; i < size; i++) {
         if (arr[i].equals(element)) {
            flag = i;
            break;
         }
      }
      return flag;
   }

   @Override
   public int remove(int index) {
      Object[] tmpArray = new Object[size - 1];
      if (index == 0) {
         System.arraycopy(arr, 1, tmpArray, 0, size - 1);
         arr = tmpArray;
         size--;
         return 1;
      } else if (index == size - 1) {
         System.arraycopy(arr, 0, tmpArray, 0, size - 1);
         arr = tmpArray;
         size--;
         return 2;
      } else {
         System.arraycopy(arr, 0, tmpArray, 0, index);
         System.arraycopy(arr, index + 1, tmpArray, index, index);
         arr = tmpArray;
         size--;
         return 3;
      }
   }

   @Override
   public void clear() {
      size = 0;
   }

   @Override
   public String toString() {
      if (size == 0) {
         return "[]";
      } else {
         StringBuilder result = new StringBuilder();
         result.append("[");
         for (int i = 0; i < size; i++) {
            result.append(arr[i]);
            if (i + 1 < size) {
               result.append(", ");
            }
         }
         result.append("]");
         return result.toString();
      }
   }

   @Override
   public int size() {
      return this.size;
   }

   @Override
   public Iterator<Object> iterator() {
      return new IteratorImpl();
   }

   private class IteratorImpl implements Iterator<Object> {

      private int currentIndex = -1;

      @Override
      public boolean hasNext() {
         return currentIndex < ArrayImpl.this.size() - 1;
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
         if (0 <= currentIndex && currentIndex <= ArrayImpl.this.size()) {
            ArrayImpl.this.remove(currentIndex);
            this.currentIndex -= 1;
         } else {
            throw new IllegalStateException("Exeption");
         }
      }

   }

}





































