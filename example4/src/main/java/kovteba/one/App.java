package kovteba.one;

import java.util.Iterator;

public class App {
   public static void main(String[] args) {

      ListImpl list = new ListImpl();

      list.addFirst(3);
      list.addFirst(2);
      list.addFirst(1);

      Iterator iterator = list.iterator();

      System.out.println(iterator.next());
      iterator.remove();
      System.out.println(iterator.next());
      iterator.remove();
      System.out.println(iterator.next());
      iterator.remove();
      System.out.println(list);

   }
}
