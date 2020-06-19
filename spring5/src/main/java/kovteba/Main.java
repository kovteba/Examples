package kovteba;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
   public static void main(String[] args) {

      ApplicationContext context = new AnnotationConfigApplicationContext(AppContext.class);
      TestBean testBean = context.getBean(TestBean.class);
      System.out.println("Test from file properties : " + testBean.getTest());
      System.out.println("From XML filew : " + testBean.getName());

   }
}
