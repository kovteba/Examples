# Example @Value

```properties
file.storage="files"
```
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p" xmlns:util="http://www.springframework.org/schema/util"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
          http://www.springframework.org/schema/beans/spring-beans.xsd http://www.springframework.org/schema/util https://www.springframework.org/schema/util/spring-util.xsd
          http://www.springframework.org/schema/context https://www.springframework.org/schema/context/spring-context.xsd">

    <util:properties location="classpath:application.properties"/>

</beans>
```
```java
package kovteba;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
@Configuration
@PropertySource("classpath:application.properties")
public class AppContext {
   @Bean
   public TestBean getTestBean(){
      return new TestBean(getString());
   }
   @Bean
   public String getString(){
      return "MyTestBean";
   }
}
```
```java
package kovteba;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Configuration
public class TestBean {
   @Value("${file.storage}")
   private String test;
   private String name;
   public TestBean(String name) {
      this.name = name;
   }
   public String getTest() {
      return test;
   }
   public void setTest(String test) {
      this.test = test;
   }
   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
}
```
```java
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
```