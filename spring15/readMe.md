# AOP (Logger example)

pom.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>Examples</artifactId>
        <groupId>kovteba</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>
    <packaging>war</packaging>
    <artifactId>spring15</artifactId>
    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>1.8</maven.compiler.source>
        <maven.compiler.target>1.8</maven.compiler.target>
        <spring.version>5.0.8.RELEASE</spring.version>
        <aspectjweaver.version>1.9.2</aspectjweaver.version>
    </properties>
    <dependencies>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>${aspectjweaver.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-web</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>3.0.1</version>
            <scope>provided</scope>
        </dependency>
    </dependencies>
    <build>
        <finalName>SpringMVC</finalName>
        <pluginManagement><!-- lock down plugins versions to avoid using Maven defaults (may be moved to parent pom) -->
            <plugins>
                <plugin>
                    <artifactId>maven-clean-plugin</artifactId>
                    <version>3.0.0</version>
                </plugin>
                <!-- see http://maven.apache.org/ref/current/maven-core/default-bindings.html#Plugin_bindings_for_war_packaging -->
                <plugin>
                    <artifactId>maven-resources-plugin</artifactId>
                    <version>3.0.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.7.0</version>
                </plugin>
                <plugin>
                    <artifactId>maven-surefire-plugin</artifactId>
                    <version>2.20.1</version>
                </plugin>
                <plugin>
                    <artifactId>maven-war-plugin</artifactId>
                    <version>3.2.0</version>
                </plugin>
                <plugin>
                    <artifactId>maven-install-plugin</artifactId>
                    <version>2.5.2</version>
                </plugin>
                <plugin>
                    <artifactId>maven-deploy-plugin</artifactId>
                    <version>2.8.2</version>
                </plugin>
            </plugins>
        </pluginManagement>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <configuration>
                    <source>${maven.compiler.source}}</source>
                    <target>${maven.compiler.source}}</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```
applicationContext.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns="http://www.springframework.org/schema/beans"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd
       http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
    <context:component-scan base-package="kovteba.*"/>
    <bean id="myLogger" class="kovteba.utils.Logger"/>
    <!--AOP-->
    <aop:config>
        <aop:aspect ref="myLogger" id="myAspect">
            <aop:before method="beforeMethodInvocation" pointcut="execution(* kovteba.controllers.MyController.before(..))"/>
            <aop:after-returning method="afterReturn" pointcut="execution(* kovteba.controllers.MyController.afterReturn(..)) and args(result)"/>
            <aop:after-throwing method="afterThrowing" throwing="ex" pointcut="execution(* kovteba.controllers.MyController.afterThrow(..))"/>
        </aop:aspect>
    </aop:config>
    <!--AOP-->
</beans>
```
web.xml
```xml
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         id="WebApp_ID" version="3.1">
    <display-name>Archetype Created Web Application</display-name>
    <servlet>
        <servlet-name>spring-servlet</servlet-name>
        <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
        <init-param>
            <param-name>contextConfigLocation</param-name>
            <param-value>/WEB-INF/dispatcher-servlet.xml</param-value>
        </init-param>
        <load-on-startup>0</load-on-startup>
    </servlet>
    <servlet-mapping>
        <servlet-name>spring-servlet</servlet-name>
        <url-pattern>/</url-pattern>
    </servlet-mapping>
    <context-param>
        <param-name>contextConfigLocation</param-name>
        <param-value>/WEB-INF/dispatcher-servlet.xml</param-value>
    </context-param>
</web-app>
```
dispatcherServlet.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:mvc="http://www.springframework.org/schema/mvc"
       xmlns="http://www.springframework.org/schema/beans"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
       http://www.springframework.org/schema/mvc
       http://www.springframework.org/schema/mvc/spring-mvc.xsd">
    <mvc:annotation-driven/>
    <import resource="applicationContext.xml"/>
</beans>
```
```java
package kovteba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
@Controller
public class MyController {
   @GetMapping(value = "/before")
   public ModelAndView before(ModelAndView modelAndView){
      modelAndView.setViewName("before.jsp");
      return modelAndView;
   }
   @GetMapping(value = "/afterReturn")
   public ModelAndView afterReturn(ModelAndView modelAndView){
      modelAndView.setViewName("afterReturn.jsp");
      return modelAndView;
   }
   @GetMapping(value = "/afterThrow")
   public ModelAndView afterThrow(ModelAndView modelAndView) throws IllegalAccessException {
      modelAndView.setViewName("afterThrow.jsp");
      throw new IllegalAccessException();
   }
}
```
```java
package kovteba.utils;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import static java.lang.System.out;
@Component
public class Logger {
   ApplicationContext applicationContext;
   @Autowired
   public void setApplicationContext(ApplicationContext applicationContext) {
      this.applicationContext = applicationContext;
   }
   public void beforeMethodInvocation(JoinPoint joinPoint) {
      out.println("--> LOG " + joinPoint.getSignature().toShortString() + " method");
      out.println();
   }
   public void afterReturn(JoinPoint joinPoint, Object result) {
      out.println("--> LOG " + joinPoint.getSignature().toShortString() + " method");
      out.println("           Result of method execution : " + result);
      out.println();
   }
   public void afterThrowing(Exception ex) {
      out.println("--> Log : error");
      out.println("        Exception : " + ex.getMessage());
      out.println();
   }
}
```