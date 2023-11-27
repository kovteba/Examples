package kovteba.one;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class App {
   public static void main(String[] args) {

      Array array = new ArrayImpl();

      Iterator it = array.iterator();

      array.add("A");
      array.add("B");
      array.add("C");

      for (Object el : array){
         System.out.println(el);
      }
      System.out.println("----------------------------");

      System.out.println(it.next());
      it.remove();
      System.out.println(it.next());
      it.remove();
      System.out.println(it.next());
      it.remove();
      System.out.println(array);


      List a = new ArrayList();

   }
}
