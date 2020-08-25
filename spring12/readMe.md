# Interface BeanNameAware

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="beanNamePrinter" class="kovteba.BeanNamePrinter"/>
</beans>
```
```java
package kovteba;
import org.springframework.beans.factory.BeanNameAware;
public class BeanNamePrinter implements BeanNameAware {
    private String beanName;
    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
    public void someOperation() {
        System.out.println("Bean [" + beanName + "] - someOperation()");
    }
}
```
```java
package kovteba;
import org.springframework.context.support.GenericXmlApplicationContext;
public class BeanNamePrinterExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/app-context-xml.xml");
        ctx.refresh();
        BeanNamePrinter bean = (BeanNamePrinter) ctx.getBean("beanNamePrinter");
        bean.someOperation();
    }
}
```