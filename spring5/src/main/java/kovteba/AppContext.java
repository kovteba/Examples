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
