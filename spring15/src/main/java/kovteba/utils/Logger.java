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
