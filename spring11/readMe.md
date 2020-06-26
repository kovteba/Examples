# Interface InitializingBean

```xml
<?xml version="1.0" encoding="UTF-8"?>

<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xmlns:p="http://www.springframework.org/schema/p"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd"
    default-lazy-init="true">

    <bean id="simpleBean1"
        class="kovteba.SimpleBeanWithInterface"
        p:name="Chris Schaefer" p:age="32"/>

    <bean id="simpleBean2"
        class="kovteba.SimpleBeanWithInterface"
        p:age="32"/>

    <bean id="simpleBean3" 
        class="kovteba.SimpleBeanWithInterface"
        p:name="Chris Schaefer"/>
</beans>
```
```java
package kovteba;
import org.springframework.beans.factory.InitializingBean;
public class SimpleBeanWithInterface implements InitializingBean {
    private static final String DEFAULT_NAME = "Luke Skywalker";
    private String name;
    private int age = Integer.MIN_VALUE;
    public void setName(String name) {
        this.name = name;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void myInit() {
        System.out.println("My Init");
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializing bean");
        if (name == null) {
            System.out.println("Using default name");
            name = DEFAULT_NAME;
        }
        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException(
                    "You must set the age property of any beans of type " 
                    + SimpleBeanWithInterface.class);
        }
    }
    public String toString() {
        return "Name: " + name + "\nAge: " + age;
    }
}
```
```java
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
```