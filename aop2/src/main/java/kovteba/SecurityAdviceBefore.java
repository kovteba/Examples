package kovteba;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;

public class SecurityAdviceBefore implements MethodBeforeAdvice {
   private SecurityManager securityManager;

   public SecurityAdviceBefore() {
      this.securityManager = new SecurityManager();
   }

   @Override
   public void before(Method method, Object[] args, Object target) throws Throwable {
      UserInfo user = securityManager.getLoggedOnUser();

      if (user == null) {
         System.out.println("No user authenticated");
         throw new SecurityException("You must login before attempting to invoke the method: " + method.getName());
      } else if ("chris".equals(user.getUserName())) {
         System.out.println("Logged in user is chris - OKAY!");
      } else {
         System.out.println("Logged in user is " + user.getUserName() + " NOT GOOD :(");
         throw new SecurityException("User " + user.getUserName() + " is not allowed access to method " + method.getName());
      }
   }
}
