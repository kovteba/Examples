package kovteba.xml;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DemoXML {

   public static void main(String[] args) {
      GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
      ctx.load("classpath:META-INF/spring/app-context-xml.xml");
      ctx.refresh();

      SimpleBeanXML simpleBean1 = getBean("simpleBeanXML1", ctx);
      SimpleBeanXML simpleBean2 = getBean("simpleBeanXML2", ctx);
      SimpleBeanXML simpleBean3 = getBean("simpleBeanXML3", ctx);
   }

   private static SimpleBeanXML getBean(String beanName, ApplicationContext ctx) {
      try {
         SimpleBeanXML bean = (SimpleBeanXML) ctx.getBean(beanName);
         System.out.println(bean);
         return bean;
      } catch (BeanCreationException ex) {
         System.out.println("An error occured in bean configuration: "
             + ex.getMessage());
         return null;
      }
   }

}
