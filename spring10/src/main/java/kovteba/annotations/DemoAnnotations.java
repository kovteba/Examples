package kovteba.annotations;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DemoAnnotations {

   public static void main(String[] args) {
      GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
      ctx.load("classpath:META-INF/spring/app-context-xml.xml");
      ctx.refresh();
      SimpleBeanAnnotations simpleBeanAnnotations = getBean("simpleBeanAnnotations", ctx);
   }

   private static SimpleBeanAnnotations getBean(String beanName, ApplicationContext context) {
      try {
         SimpleBeanAnnotations bean = context.getBean(beanName, SimpleBeanAnnotations.class);
         System.out.println(bean);
         return bean;
      } catch (BeanCreationException ex) {
         System.out.println("An error occured in bean configuration: "
             + ex.getMessage());
         return null;
      }
   }

}
