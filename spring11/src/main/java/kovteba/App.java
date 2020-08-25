package kovteba;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class App {
   public static void main(String[] args) {
      GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
      ctx.load("classpath:META-INF/spring/app-context-xml.xml");
      ctx.refresh();

      SimpleBeanWithInterface simpleBean1 = getBean("simpleBean1", ctx);
      SimpleBeanWithInterface simpleBean2 = getBean("simpleBean2", ctx);
      SimpleBeanWithInterface simpleBean3 = getBean("simpleBean3", ctx);
   }

   private static SimpleBeanWithInterface getBean(String beanName,
                                                  ApplicationContext ctx) {
      try {
         SimpleBeanWithInterface bean = (SimpleBeanWithInterface) ctx.getBean(beanName);
         System.out.println(bean);
         return bean;
      } catch (BeanCreationException ex) {
         System.out.println("An error occured in bean configuration: "
             + ex.getMessage());
         return null;
      }
   }
}
