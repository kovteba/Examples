# Method Replacement

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
    xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
    xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="methodReplacer" class="com.apress.prospring4.ch3.FormatMessageReplacer"/>
    <bean id="replacementTarget" class="com.apress.prospring4.ch3.ReplacementTarget">
        <replaced-method name="formatMessage" replacer="methodReplacer">
            <arg-type>String</arg-type>
        </replaced-method>
    </bean>
    <bean id="standardTarget" class="com.apress.prospring4.ch3.ReplacementTarget"/>
</beans>
```
```java
package com.apress.prospring4.ch3;
public class ReplacementTarget {
    public String formatMessage(String msg) {
        return "<h1>" + msg + "</h1>"; 
    }
    public String formatMessage(Object msg) {
        return "<h1>" + msg + "</h1>"; 
    }
}
```
```java
package com.apress.prospring4.ch3;
import java.lang.reflect.Method;
import org.springframework.beans.factory.support.MethodReplacer;
public class FormatMessageReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object arg0, Method method, Object[] args)
            throws Throwable {
        if (isFormatMessageMethod(method)) {
            String msg = (String) args[0];
            return "<h2>" + msg + "</h2>";
        } else {
            throw new IllegalArgumentException("Unable to reimplement method "
                    + method.getName());
        }
    }
    private boolean isFormatMessageMethod(Method method) {
        if (method.getParameterTypes().length != 1) {
            return false;
        }
        if (!("formatMessage".equals(method.getName()))) {
            return false;
        }
        if (method.getReturnType() != String.class) {
            return false;
        }
        if (method.getParameterTypes()[0] != String.class) {
            return false;
        }
        return true;
    } 
}
```
```java
package com.apress.prospring4.ch3;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.util.StopWatch; 
public class MethodReplacementExample {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:META-INF/spring/app-context-xml.xml");
        ctx.refresh();
        ReplacementTarget replacementTarget = (ReplacementTarget) ctx
                .getBean("replacementTarget");
        ReplacementTarget standardTarget = (ReplacementTarget) ctx
                .getBean("standardTarget");
        displayInfo(replacementTarget);
        displayInfo(standardTarget);
    }
    private static void displayInfo(ReplacementTarget target) {
        System.out.println(target.formatMessage("Hello World!"));
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("perfTest");
        for (int x = 0; x < 1000000; x++) {
            String out = target.formatMessage("foo");
        }
        stopWatch.stop();
        System.out.println("1000000 invocations took: "
                + stopWatch.getTotalTimeMillis() + " ms");
    } 
}
```