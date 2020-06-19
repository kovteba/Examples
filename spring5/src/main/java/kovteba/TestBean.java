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
