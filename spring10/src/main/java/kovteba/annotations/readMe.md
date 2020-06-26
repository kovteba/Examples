# Init Method Annotations

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd"
       default-lazy-init="true">
    <context:annotation-config/>
    <context:component-scan base-package="kovteba"/>
</beans>
```
```java
package kovteba.annotations;
import kovteba.xml.SimpleBeanXML;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
@Component("simpleBeanAnnotations")
public class SimpleBeanAnnotations {
   private static final String DEFAULT_NAME = "DEFAULT_NAME";
   private String name;
   private int age = Integer.MIN_VALUE;
   public void setName(String name) {
      this.name = name;
   }
   public void setAge(int age) {
      this.age = age;
   }
   @PostConstruct
   public void init() {
      System.out.println("Initializing bean");
      if (name == null) {
         System.out.println("Using default name");
         name = DEFAULT_NAME;
      }
      if (age == Integer.MIN_VALUE) {
         throw new IllegalArgumentException(
             "You must set the age property of any beans of type " + SimpleBeanXML.class);
      }
   }
   public String toString() {
      return "Name: " + name + "\nAge: " + age;
   }
}
```
```java
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
```