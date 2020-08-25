# Internationalization Spring

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
         http://www.springframework.org/schema/beans/spring-beans.xsd
         http://www.springframework.org/schema/util
         http://www.springframework.org/schema/util/spring-util.xsd">
    <bean id="messageSource"
          class="org.springframework.context.support.ResourceBundleMessageSource"
          p:basenames-ref="basenames"/>
    <util:list id="basenames">
        <value>buttons</value>
        <value>labels</value>
    </util:list>
</beans>
```
```properties
msg=The quick brown fox jumped over the lazy dog
nameMsg=My name is {0} {1}
```
```properties
msg=Príšerne žlutoucký kun úpel dábelské ódy
nameMsg=My name is {0} {1}
```
```java
package kovteba;
import java.util.Locale;
import org.springframework.context.support.GenericXmlApplicationContext;
public class MessageSourceDemo {
   public static void main(String[] args) {
      GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
      ctx.load("classpath:META-INF/spring/app-context-xml.xml");
      ctx.refresh();
      Locale english = Locale.ENGLISH;
      Locale czech = new Locale("cs", "CZ");
      System.out.println(ctx.getMessage("msg", null, english));
      System.out.println(ctx.getMessage("msg", null, czech));
      System.out.println(ctx.getMessage("nameMsg", new Object[]{"Chris", "Schaefer"}, english));
      System.out.println(ctx.getMessage("nameMsg", new Object[]{"Chris1", "Schaefer1"}, czech));
   }
}
```

Главная причина использования ApplicationContext вместо ручного определения бина MessageSource заключается в том, 
что Spring, где возможно, открывает ApplicationContext как MessageSource для уровня презентаций. Это означает, 
что когда применя­ ется библиотека дескрипторов JSP (JSTL), поддерживаемая Spring, дескриптор <spring :message> 
автоматически читает сообщения из ApplicationContext, и в случае использования JSTL дескриптор <fmt: message> 
делает то же самое.